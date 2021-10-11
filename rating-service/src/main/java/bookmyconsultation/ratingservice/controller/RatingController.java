package bookmyconsultation.ratingservice.controller;

import bookmyconsultation.ratingservice.dto.RatingDTO;
import bookmyconsultation.ratingservice.entity.RatingEntity;
import bookmyconsultation.ratingservice.service.RatingService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;


@RequestMapping
@RestController
public class RatingController {

    @Autowired
    RatingService ratingService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping(value = "/ratings", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addRatings(@RequestBody RatingDTO ratingDTO){

        RatingEntity rating = modelMapper.map(ratingDTO, RatingEntity.class);
        RatingEntity savedRating = ratingService.addRating(rating);
        RatingDTO savedRatingDTO = modelMapper.map(savedRating, RatingDTO.class);
        return new ResponseEntity(null, HttpStatus.OK);

    }
}
