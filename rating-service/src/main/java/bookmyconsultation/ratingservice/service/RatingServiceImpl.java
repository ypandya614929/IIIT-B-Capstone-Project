package bookmyconsultation.ratingservice.service;

import bookmyconsultation.ratingservice.entity.RatingServiceEntity;
import bookmyconsultation.ratingservice.repository.RatingServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;


@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    RatingServiceRepository ratingserviceRepository;

    @Autowired
    Producer<String, String> producer;

    @Override
    public RatingServiceEntity addRating(RatingServiceEntity rating) {
        RatingServiceEntity savedRating = ratingserviceRepository.save(rating);
        String message = savedRating.toString();
//        producer.send(new ProducerRecord<>("message","message", message));
        return savedRating;
    }
}