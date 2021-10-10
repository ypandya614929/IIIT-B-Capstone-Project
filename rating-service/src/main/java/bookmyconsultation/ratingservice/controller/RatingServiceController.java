package bookmyconsultation.ratingservice.controller;

import bookmyconsultation.ratingservice.dto.RatingServiceDTO;
import bookmyconsultation.ratingservice.entity.RatingServiceEntity;
import bookmyconsultation.ratingservice.service.RatingService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.beans.factory.annotation.Autowired;

//@FeignClient(name = "BookMyConsultation-GATEWAY")
@RequestMapping
@RestController
public class RatingServiceController {

    @Autowired
    RatingService ratingService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping(value = "/ratings", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addRatings(@RequestBody RatingServiceDTO ratingDTO){

        RatingServiceEntity rating = modelMapper.map(ratingDTO, RatingServiceEntity.class);
        RatingServiceEntity savedRating = ratingService.addRating(rating);
        RatingServiceDTO savedRatingDTO = modelMapper.map(savedRating, RatingServiceDTO.class);
        return new ResponseEntity(null, HttpStatus.OK);

    }
}
