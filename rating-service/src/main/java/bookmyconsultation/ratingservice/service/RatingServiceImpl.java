package bookmyconsultation.ratingservice.service;

import bookmyconsultation.ratingservice.entity.RatingEntity;
import bookmyconsultation.ratingservice.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.kafka.clients.producer.Producer;


@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    RatingRepository ratingserviceRepository;

    @Autowired
    Producer<String, String> producer;

    @Override
    public RatingEntity addRating(RatingEntity rating) {
        RatingEntity savedRating = ratingserviceRepository.save(rating);
        String message = savedRating.toString();
//        producer.send(new ProducerRecord<>("message","message", message));
        return savedRating;
    }
}