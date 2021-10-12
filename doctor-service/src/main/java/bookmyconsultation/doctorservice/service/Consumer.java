package bookmyconsultation.doctorservice.service;

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
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.MessagingException;


@Configuration
public class Consumer {

    @Autowired
    DoctorServiceImpl doctorServiceImpl;

    @EventListener(ApplicationReadyEvent.class)
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
        consumer.subscribe(Arrays.asList("doctor"));
        //Prints the topic subscription list
        Set<String> subscribedTopics = consumer.subscription();
        for(String topic : subscribedTopics) {
            System.out.println(topic);
        }
        try {
            while(true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                for(ConsumerRecord<String, String> record : records) {
                    if(record.key().equalsIgnoreCase("DOCTOR_RATINGS")){
                        String[] values = record.value().split(",", 2);
                        String doctorId = values[0];
                        float rating = Float.parseFloat(values[1]);
                        doctorServiceImpl.updateDoctorRatings(doctorId, rating);
                    }
                }
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally { }
    }

}