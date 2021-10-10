package bookmyconsultation.doctorservice.controller;

import bookmyconsultation.doctorservice.dto.DetailDoctorServiceDTO;
import bookmyconsultation.doctorservice.dto.DoctorServiceDTO;
import bookmyconsultation.doctorservice.dto.UpdateDoctorServiceDTO;
import bookmyconsultation.doctorservice.entity.DoctorServiceEntity;
import bookmyconsultation.doctorservice.service.DoctorService;
import bookmyconsultation.doctorservice.repository.AWSRepository;
import freemarker.template.TemplateException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.MessagingException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@RequestMapping
@RestController
public class DoctorServiceController {

    @Autowired
    DoctorService doctorService;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    AWSRepository awsRepository;

    @PostMapping(value = "/doctors", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addDoctor(@RequestBody DoctorServiceDTO doctorDTO) throws MessagingException, IOException, TemplateException, javax.mail.MessagingException {
        DoctorServiceDTO savedDoctorDTO = doctorService.createDoctor(doctorDTO);
        return new ResponseEntity(savedDoctorDTO, HttpStatus.CREATED);

    }

    @PostMapping(value = "/doctors/{doctorId}/documents")
    public ResponseEntity<String> uploadFiles(@PathVariable String doctorId, @RequestParam MultipartFile[] files) throws IOException {
        for (MultipartFile file: files){
            awsRepository.uploadFiles(doctorId, file);
        }
        return ResponseEntity.ok().body("File(s) uploaded Successfully");
    }

    @PutMapping(value = "/doctors/{doctorId}/approve")
    public ResponseEntity approveDoctorRegistration(@PathVariable("doctorId") String doctorId
            , @RequestBody UpdateDoctorServiceDTO updateDoctorDTO) throws Exception {
        DetailDoctorServiceDTO detailDoctorServiceDTO = doctorService.updateDoctor(doctorId, updateDoctorDTO, "Active");
        return new ResponseEntity(detailDoctorServiceDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/doctors/{doctorId}/reject")
    public ResponseEntity rejectDoctorRegistration(@PathVariable("doctorId") String doctorId
            , @RequestBody UpdateDoctorServiceDTO updateDoctorDTO) throws Exception {
        DetailDoctorServiceDTO detailDoctorServiceDTO = doctorService.updateDoctor(doctorId, updateDoctorDTO, "Rejected");
        return new ResponseEntity(detailDoctorServiceDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/doctors")
    public ResponseEntity<List<DoctorServiceEntity>> getDoctorByStatusAndSpeciality(String status, String speciality){
        List<DoctorServiceEntity> doctorList = doctorService.getDoctorByStatusAndSpeciality(status, speciality);
        return new ResponseEntity(doctorList, HttpStatus.OK);
    }

    @GetMapping(value = "/doctors/{doctorId}")
    public ResponseEntity findDoctor(@PathVariable(name="doctorId") String doctorId){
        DoctorServiceEntity doctorEntity = doctorService.getDoctor(doctorId);
        DoctorServiceDTO savedDoctorDTO = modelMapper.map(doctorEntity, DoctorServiceDTO.class);
        return new ResponseEntity(savedDoctorDTO, HttpStatus.CREATED);
    }


    @GetMapping(value = "/doctors/{doctorId}/documents/metadata")
    public ResponseEntity<List<String>> getMetaData(@PathVariable String doctorId) {
        List<String> metadata = awsRepository.listFiles(doctorId);
        return new ResponseEntity<>(metadata, HttpStatus.OK);
    }

    @GetMapping(value = "/doctors/{doctorId}/documents/{documentName}")
    public ResponseEntity<byte[]> getFile(@PathVariable String doctorId, @PathVariable(name="documentName") String documentName){
        ByteArrayOutputStream downloadBytesData = awsRepository.downloadFile(doctorId, documentName);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + documentName + "\"")
                .body(downloadBytesData.toByteArray());
    }

    @GetMapping("/verify")
    public ResponseEntity verifyEmail(@RequestParam("email") String emailId){
        awsRepository.verifyEmail(emailId);
        return ResponseEntity.ok().build();
    }

}
