package bookmyconsultation.appointmentservice.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.Set;


@Configuration
public class Consumer {

    @Autowired
    AppointmentServiceImpl appointmentServiceImpl;

    @EventListener(ApplicationReadyEvent.class)
    public void consume() {

        Properties props = new Properties();

        //Update the IP address of Kafka server here//

        props.put("bootstrap.servers", "23.20.158.142:9092");
        props.setProperty("group.id", "bmc");
        props.setProperty("enable.auto.commit", "true");
        props.setProperty("auto.commit.interval.ms", "1000");
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
        consumer.subscribe(Arrays.asList("payment"));
        //Prints the topic subscription list
        Set<String> subscribedTopics = consumer.subscription();
        for(String topic : subscribedTopics) {
            System.out.println(topic);
        }

        while(true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for(ConsumerRecord<String, String> record : records) {
                if(record.key().equalsIgnoreCase("PAYMENT_SERVICE")){
                    appointmentServiceImpl.updateAppointmentStatus(record.value());
                }
            }
        }
    }

}
