package bookmyconsultation.userservice.repository;


import bookmyconsultation.userservice.dto.UserDTo;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.services.ses.SesClient;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import javax.annotation.PostConstruct;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


// this class is used to communicate with AWS
// for S3 and SES
@Component
public class AWSRepository {

    private String BUCKET_NAME="bmc.doctorservice.yash.users.docs";
    private String ACCESS_KEY = "AKIAX6XIW2S4JCQQTSVX";
    private String ACCESS_SECRET = "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";
    private String SMTP_ACCESS_KEY = "AKIAX6XIW2S4FB2QL4GT";
    private String SMTP_ACCESS_SECRET = "BF+n4UbWIryXqW0OQNZOc467dneFAFq4i4g3RQS/EQs5";
    private AmazonS3 s3Client;
    private SesClient sesClient;
    private final FreeMarkerConfigurer freeMarkerConfig;
    private String FROM_EMAIL = "yashp6149@gmail.com";

    @Autowired
    ObjectMetadata metadata;

    public AWSRepository(FreeMarkerConfigurer freeMarkerConfig) {
        this.freeMarkerConfig = freeMarkerConfig;
    }

    @PostConstruct
    public AmazonS3 createS3Client() {
        AWSCredentials credentials = new BasicAWSCredentials(ACCESS_KEY, ACCESS_SECRET);
        s3Client=AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.US_EAST_1)
                .build();
        return s3Client;
    }

    @PostConstruct
    public SesClient createSESClient() {
        StaticCredentialsProvider staticCredentialsProvider = StaticCredentialsProvider
                .create(AwsBasicCredentials.create(ACCESS_KEY,ACCESS_SECRET));
        sesClient = SesClient.builder()
                .credentialsProvider(staticCredentialsProvider)
                .region(software.amazon.awssdk.regions.Region.US_EAST_1)
                .build();
        return sesClient;
    }

    public void uploadFiles(String userId, MultipartFile file) throws IOException {
        String key = userId + "/" + file.getOriginalFilename();
        if(!s3Client.doesBucketExistV2(BUCKET_NAME)){
            s3Client.createBucket(BUCKET_NAME);
        }
        s3Client.putObject(BUCKET_NAME, key, file.getInputStream(), metadata);
    }

    public ByteArrayOutputStream downloadFile(String userId, String fileName) {
        String key = userId + "/" + fileName;
        try {
            S3Object s3object = s3Client.getObject(new GetObjectRequest(BUCKET_NAME, key));

            InputStream is = s3object.getObjectContent();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            int len;
            byte[] buffer = new byte[4096];
            while ((len = is.read(buffer, 0, buffer.length)) != -1) {
                outputStream.write(buffer, 0, len);
            }
            return outputStream;
        } catch (IOException ioException) {
            System.out.println("IOException: " + ioException.getMessage());
        } catch (AmazonServiceException serviceException) {
            System.out.println("AmazonServiceException Message:    " + serviceException.getMessage());
            throw serviceException;
        } catch (AmazonClientException clientException) {
            System.out.println("AmazonClientException Message: " + clientException.getMessage());
            throw clientException;
        }
        return null;
    }

    public List<String> listFiles(String userId) {
        ListObjectsRequest listObjectsRequest =
                new ListObjectsRequest()
                        .withBucketName(BUCKET_NAME);
        List<String> keys = new ArrayList<>();
        ObjectListing objects = s3Client.listObjects(listObjectsRequest);
        while (true) {
            List<S3ObjectSummary> objectSummaries = objects.getObjectSummaries();
            if (objectSummaries.size() < 1) {
                break;
            }
            for (S3ObjectSummary item : objectSummaries) {
                if (!item.getKey().endsWith("/") && item.getKey().startsWith(userId))
                    keys.add(item.getKey().split("/")[1]);
            }
            objects = s3Client.listNextBatchOfObjects(objects);
        }
        return keys;
    }

    public void verifyEmail(String emailId){
        sesClient.verifyEmailAddress(req->req.emailAddress(emailId));
    }

    public void sendEmail(UserDTo user) throws IOException, TemplateException, MessagingException, javax.mail.MessagingException {
        Map<String,Object> templateModel = new HashMap<>();
        templateModel.put("user", user);
        Template freeMarkerTemplate = freeMarkerConfig.getConfiguration().getTemplate("userwelcome.ftl");
        String htmlBody = FreeMarkerTemplateUtils.processTemplateIntoString(freeMarkerTemplate,templateModel);
        sendSimpleMessage(user.getEmailId(),"Welcome Email",htmlBody);
    }

    private void sendSimpleMessage(String toEmail, String subject, String body) throws MessagingException, javax.mail.MessagingException {
        Properties props = System.getProperties();
        props.put("mail.transport.protocol","smtp");
        props.put("mail.smtp.port",587);
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.auth","true");
        javax.mail.Session session = javax.mail.Session.getDefaultInstance(props);
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(FROM_EMAIL);
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
        msg.setSubject(subject);
        msg.setContent(body,"text/html");
        Transport transport = session.getTransport();
        try {
            transport.connect("email-smtp.us-east-1.amazonaws.com", SMTP_ACCESS_KEY, SMTP_ACCESS_SECRET);
            transport.sendMessage(msg, msg.getAllRecipients());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally {
            transport.close();
        }
    }
}
