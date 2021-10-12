package bookmyconsultation.notificationservice.service;

import java.io.IOException;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import javax.mail.MessagingException;

@Component
public class Consumer {

    @Autowired
    NotificationService notificationService;

    @KafkaListener(topics = "DOCTOR_SERVICE_UPDATE")
    public void processMessage(String content) throws TemplateException, IOException, MessagingException {
        notificationService.fetchMessage("DOCTOR_SERVICE_UPDATE", content);
    }

    @KafkaListener(topics = "APPOINTMENT_SERVICE")
    public void processMessage2(String content) throws TemplateException, IOException, MessagingException {
        notificationService.fetchMessage("APPOINTMENT_SERVICE", content);
    }

    @KafkaListener(topics = "PRESCRIPTION")
    public void processMessage3(String content) throws TemplateException, IOException, MessagingException {
        notificationService.fetchMessage("PRESCRIPTION", content);
    }

    @KafkaListener(topics = "message")
    public void processMessage4(String content) {
        System.out.println("Message : " + content);
    }

}