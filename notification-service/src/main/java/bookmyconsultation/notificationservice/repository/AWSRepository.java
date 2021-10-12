package bookmyconsultation.notificationservice.repository;

import com.amazonaws.services.s3.model.*;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.services.ses.SesClient;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import javax.annotation.PostConstruct;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


@Component
public class AWSRepository {

    private String ACCESS_KEY = "";
    private String ACCESS_SECRET = "";
    private String SMTP_ACCESS_KEY = "";
    private String SMTP_ACCESS_SECRET = "";
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

    public void sendDoctorUpdateEmail(Map<String,String> parseMap) throws IOException, TemplateException, MessagingException, javax.mail.MessagingException {
        Map<String,Object> templateModel = new HashMap<>();
        templateModel.put("firstName", parseMap.get("firstName"));
        templateModel.put("approvedBy", parseMap.get("approvedBy"));
        templateModel.put("status", parseMap.get("status"));
        templateModel.put("approverComments", parseMap.get("approverComments"));
        Template freeMarkerTemplate = freeMarkerConfig.getConfiguration().getTemplate("doctorupdate.ftl");
        String htmlBody = FreeMarkerTemplateUtils.processTemplateIntoString(freeMarkerTemplate,templateModel);
        sendSimpleMessage(parseMap.get("emailId"),"Verification Email",htmlBody);
    }

    public void sendAppointmentConfirmationEmail(Map<String,String> parseMap) throws IOException, TemplateException, MessagingException, javax.mail.MessagingException {
        Map<String,Object> templateModel = new HashMap<>();
        templateModel.put("appointmentDate", parseMap.get("appointmentDate"));
        templateModel.put("doctorName", parseMap.get("doctorName"));
        templateModel.put("timeSlot", parseMap.get("timeSlot"));
        templateModel.put("userName", parseMap.get("userName"));
        templateModel.put("status", parseMap.get("status"));
        Template freeMarkerTemplate = freeMarkerConfig.getConfiguration().getTemplate("appointment.ftl");
        String htmlBody = FreeMarkerTemplateUtils.processTemplateIntoString(freeMarkerTemplate,templateModel);
        sendSimpleMessage(parseMap.get("userEmailId"),"Appointment Email",htmlBody);
    }

    public void sendPrescriptionEmail(Map<String,String> parseMap) throws IOException, TemplateException, MessagingException, javax.mail.MessagingException {
        Map<String,Object> templateModel = new HashMap<>();
        templateModel.put("doctorId", parseMap.get("doctorId"));
        templateModel.put("doctorName", parseMap.get("doctorName"));
        templateModel.put("appointmentId", parseMap.get("appointmentId"));
        templateModel.put("diagnosis", parseMap.get("diagnosis"));
        templateModel.put("medicineList", parseMap.get("medicineList"));
        Template freeMarkerTemplate = freeMarkerConfig.getConfiguration().getTemplate("prescription.ftl");
        String htmlBody = FreeMarkerTemplateUtils.processTemplateIntoString(freeMarkerTemplate,templateModel);
        sendSimpleMessage(parseMap.get("userEmailId"),"Your Prescription is Ready",htmlBody);
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
