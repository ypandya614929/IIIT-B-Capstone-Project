package bookmyconsultation.appointmentservice.controller;

import bookmyconsultation.appointmentservice.dto.*;
import bookmyconsultation.appointmentservice.repository.AWSRepository;
import bookmyconsultation.appointmentservice.service.AppointmentService;
import bookmyconsultation.appointmentservice.service.AvailabilityService;
import freemarker.template.TemplateException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.MessagingException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RequestMapping
@RestController
public class AppointmentServiceController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    AvailabilityService availabilityService;

    @Autowired
    AppointmentService appointmentService;

    @Autowired
    AWSRepository awsRepository;

    @PostMapping(value = "/doctor/{doctorId}/availability", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity saveAvailaibility(@PathVariable(name = "doctorId") String doctorId, @RequestBody AvailabilityServiceRequestDTO availabilityRequestDTO){
        availabilityService.saveAvailability(doctorId, availabilityRequestDTO);
        return ResponseEntity.ok().build();
    }


    @GetMapping(value = "/doctor/{doctorId}/availability", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAvailabilityByDoctor(@PathVariable(name = "doctorId") String doctorId){
        AvailabilityServiceResponseDTO availabilityServiceResponseDTO = availabilityService.getAvailabilityByDoctor(doctorId);
        return new ResponseEntity(availabilityServiceResponseDTO, HttpStatus.OK);
    }


    @PostMapping(value = "/appointments", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity bookAppointment(@RequestBody AppointmentServiceRequestDTO appointmentDTO) throws TemplateException, MessagingException, IOException {
        AppointmentServiceResponseDTO appointmentServiceResponseDTO = appointmentService.bookAppointment(appointmentDTO);
        return ResponseEntity.ok().body(appointmentServiceResponseDTO.getAppointmentId());
    }


    @GetMapping(value = "/appointments/{appointmentId}")
    public ResponseEntity retrieveAppointment(@PathVariable(name = "appointmentId") String appointmentId){
        AppointmentServiceResponseDTO appointmentServiceResponseDTO = appointmentService.retrieveAppointment(appointmentId);
        return new ResponseEntity(appointmentServiceResponseDTO, HttpStatus.OK);
    }


    @GetMapping(value = "/users/{userId}/appointments")
    public ResponseEntity retrieveAppointmentByUserId(@PathVariable(name = "userId") String userId){
        List<AppointmentServiceResponseDTO> appointmentServiceResponseDTOList = appointmentService.retrieveAppointmentByUserId(userId);
        return new ResponseEntity(appointmentServiceResponseDTOList, HttpStatus.OK);
    }

    @PostMapping(value = "/prescriptions", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity savePrescription(@RequestBody PrescriptionserviceRequestDTO prescriptionserviceRequestDTO){
        appointmentService.savePrescription(prescriptionserviceRequestDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/verify")
    public ResponseEntity verifyEmail(@RequestParam("email") String emailId){
        awsRepository.verifyEmail(emailId);
        return ResponseEntity.ok().build();
    }

}
