package bookmyconsultation.appointmentservice.repository;


import bookmyconsultation.appointmentservice.dto.UserDetailDTO;
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

@Component
public class AWSRepository {

    private String ACCESS_KEY = "AKIAX6XIW2S4DDVF3CWU";
    private String ACCESS_SECRET = "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";
    private String SMTP_ACCESS_KEY = "AKIAX6XIW2S4LB2MVF56";
    private String SMTP_ACCESS_SECRET = "BOqVHGaIB+jasFAqSgiE6MVDB7CDlfNIFmpJkwamyjut";
    private SesClient sesClient;
    private final FreeMarkerConfigurer freeMarkerConfig;
    private String FROM_EMAIL = "yashp6149@gmail.com";

    @Autowired
    ObjectMetadata metadata;

    public AWSRepository(FreeMarkerConfigurer freeMarkerConfig) {
        this.freeMarkerConfig = freeMarkerConfig;
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

    public void verifyEmail(String emailId){
        sesClient.verifyEmailAddress(req->req.emailAddress(emailId));
    }

    public void sendEmail(UserDetailDTO userDetailDTO) throws IOException, TemplateException, MessagingException, javax.mail.MessagingException {
        Map<String,Object> templateModel = new HashMap<>();
        templateModel.put("user", userDetailDTO);
        Template freeMarkerTemplate = freeMarkerConfig.getConfiguration().getTemplate("appointment.ftl");
        String htmlBody = FreeMarkerTemplateUtils.processTemplateIntoString(freeMarkerTemplate,templateModel);
        sendSimpleMessage(userDetailDTO.getEmailId(),"Welcome Email",htmlBody);
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
