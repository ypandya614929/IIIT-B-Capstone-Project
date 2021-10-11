package bookmyconsultation.notificationservice.service;

import bookmyconsultation.notificationservice.repository.AWSRepository;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class NotificationService {

    @Autowired
    AWSRepository awsRepository;

    public static Map<String,String> parseMap(String key, String text) {
        if (key.equalsIgnoreCase("DOCTOR_SERVICE_UPDATE")){
            text = text.replace("DetailDoctorDTO", "");
        }
        if (key.equalsIgnoreCase("APPOINTMENT_SERVICE")){
            text = text.replace("AppointmentEntity", "");
        }
        if (key.equalsIgnoreCase("PRESCRIPTION")){
            text = text.replace("PrescriptionServiceEntity", "");
        }
        text = text.replace("{", "").replace("}", "").replace("'", "");
        Map<String,String> map = new LinkedHashMap<String,String>();
        for(String keyValue: text.split(", ")) {
            String[] parts = keyValue.split("=", 2);
            map.put(parts[0], parts[1]);
        }
        return map;
    }

    public Map<String,String> fetchMessage(String key, String message) throws TemplateException, IOException, MessagingException {
        Map<String,String> parseMap = parseMap(key, message);
        if(key.equalsIgnoreCase("DOCTOR_SERVICE_UPDATE")){
            sendDoctorEmail(parseMap);
        }
        if(key.equalsIgnoreCase("APPOINTMENT_SERVICE")){
            sendAppointmentConfirmationEmail(parseMap);
        }
        if(key.equalsIgnoreCase("PRESCRIPTION")){
            sendPrescriptionEmail(parseMap);
        }
        return parseMap;
    }

    public void sendDoctorEmail(Map<String,String> parseMap) throws TemplateException, IOException, MessagingException {
        awsRepository.sendDoctorUpdateEmail(parseMap);
    }

    public void sendAppointmentConfirmationEmail(Map<String,String> parseMap) throws TemplateException, IOException, MessagingException {
        awsRepository.sendAppointmentConfirmationEmail(parseMap);
    }

    public void sendPrescriptionEmail(Map<String,String> parseMap) throws TemplateException, IOException, MessagingException {
        awsRepository.sendPrescriptionEmail(parseMap);
    }

}
