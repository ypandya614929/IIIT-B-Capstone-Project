package bookmyconsultation.notificationservice.service;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.Set;
import freemarker.template.TemplateException;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import javax.annotation.PostConstruct;
import javax.mail.MessagingException;


public class Consumer {

    @Autowired
    NotificationService notificationService;

    @PostConstruct
    public void consume() {

        Properties props = new Properties();

        //Update the IP adress of Kafka server here//

        props.put("bootstrap.servers", "23.20.158.142:9092");

        props.setProperty("group.id", "bmc");
        props.setProperty("enable.auto.commit", "true");
        props.setProperty("auto.commit.interval.ms", "1000");
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
        consumer.subscribe(Arrays.asList("message"));
        //Prints the topic subscription list
        Set<String> subscribedTopics = consumer.subscription();

       try {
           while(true) {
               ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
               for(ConsumerRecord<String, String> record : records) {
                   if(record.key().equalsIgnoreCase("DOCTOR_SERVICE_UPDATE") || record.key().equalsIgnoreCase("APPOINTMENT_SERVICE") || record.key().equalsIgnoreCase("PRESCRIPTION")){
                       notificationService.fetchMessage(record.key(), record.value());
                   }
               }
           }
       } catch (TemplateException e) {
           e.printStackTrace();
       } catch (IOException e) {
           e.printStackTrace();
       } catch (MessagingException e) {
           e.printStackTrace();
       } catch (Exception e) {
            e.printStackTrace();
       } finally { }

    }

}