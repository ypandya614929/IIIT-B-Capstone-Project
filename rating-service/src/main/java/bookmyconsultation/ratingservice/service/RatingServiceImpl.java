package bookmyconsultation.ratingservice.service;

import bookmyconsultation.ratingservice.entity.RatingEntity;
import bookmyconsultation.ratingservice.repository.RatingRepository;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.kafka.clients.producer.Producer;

import java.util.List;


@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    RatingRepository ratingServiceRepository;

    @Autowired
    Producer<String, String> producer;

    @Override
    public RatingEntity addRating(RatingEntity rating) {
        RatingEntity savedRating = ratingServiceRepository.save(rating);
        List<RatingEntity> doctorRatings = ratingServiceRepository.findByDoctorId(savedRating.getDoctorId());
        float averageRating = 0;
        float totalRating = 0;
        int count = 0;
        for(RatingEntity doctorRating : doctorRatings){
            count++;
            totalRating+=doctorRating.getRating();
        }
        try{
            averageRating = totalRating/count;
        } catch (Exception e){ }
        String message = savedRating.getDoctorId() + "," + averageRating;
        producer.send(new ProducerRecord<>("doctor","DOCTOR_RATINGS", message));
        return savedRating;
    }
}