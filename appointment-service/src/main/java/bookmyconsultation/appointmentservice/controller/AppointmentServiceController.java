package bookmyconsultation.appointmentservice.controller;

import bookmyconsultation.appointmentservice.dto.*;
import bookmyconsultation.appointmentservice.service.AppointmentService;
import bookmyconsultation.appointmentservice.service.AvailabilityService;
import freemarker.template.TemplateException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.MessagingException;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PostMapping(value = "/doctor/{doctorId}/availability", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
    public ResponseEntity saveAvailability(@PathVariable(name = "doctorId") String doctorId, @RequestBody AvailabilityRequestDTO availabilityRequestDTO){
        availabilityService.saveAvailability(doctorId, availabilityRequestDTO);
        return ResponseEntity.ok().build();
    }


    @GetMapping(value = "/doctor/{doctorId}/availability", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
    public ResponseEntity getAvailabilityByDoctor(@PathVariable(name = "doctorId") String doctorId){
        AvailabilityResponseDTO availabilityServiceResponseDTO = availabilityService.getAvailabilityByDoctor(doctorId);
        return new ResponseEntity(availabilityServiceResponseDTO, HttpStatus.OK);
    }


    @PostMapping(value = "/appointments", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
    public ResponseEntity bookAppointment(@RequestHeader HttpHeaders headers, @RequestBody AppointmentRequestDTO appointmentDTO) throws TemplateException, MessagingException, IOException, javax.mail.MessagingException {
        AppointmentResponseDTO appointmentServiceResponseDTO = appointmentService.bookAppointment(appointmentDTO, headers.getFirst(HttpHeaders.AUTHORIZATION));
        return ResponseEntity.ok().body(appointmentServiceResponseDTO.getAppointmentId());
    }


    @GetMapping(value = "/appointments/{appointmentId}")
    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
    public ResponseEntity retrieveAppointment(@PathVariable(name = "appointmentId") String appointmentId){
        AppointmentResponseDTO appointmentServiceResponseDTO = appointmentService.retrieveAppointment(appointmentId);
        return new ResponseEntity(appointmentServiceResponseDTO, HttpStatus.OK);
    }


    @GetMapping(value = "/users/{userId}/appointments")
    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
    public ResponseEntity retrieveAppointmentByUserId(@PathVariable(name = "userId") String userId){
        List<AppointmentResponseDTO> appointmentServiceResponseDTOList = appointmentService.retrieveAppointmentByUserId(userId);
        return new ResponseEntity(appointmentServiceResponseDTOList, HttpStatus.OK);
    }


    @PostMapping(value = "/prescriptions", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity savePrescription(@RequestHeader HttpHeaders headers, @RequestBody PrescriptionRequestDTO prescriptionserviceRequestDTO){
        appointmentService.savePrescription(prescriptionserviceRequestDTO, headers.getFirst(HttpHeaders.AUTHORIZATION));
        return ResponseEntity.ok().build();
    }

}
