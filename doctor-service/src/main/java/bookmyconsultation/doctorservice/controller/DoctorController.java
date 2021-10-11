package bookmyconsultation.doctorservice.controller;

import bookmyconsultation.doctorservice.dto.DetailDoctorDTO;
import bookmyconsultation.doctorservice.dto.DoctorDTO;
import bookmyconsultation.doctorservice.dto.UpdateDoctorDTO;
import bookmyconsultation.doctorservice.entity.DoctorEntity;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;


@Validated
@RequestMapping
@RestController
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    AWSRepository awsRepository;

    @PostMapping(value = "/doctors", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
    public ResponseEntity addDoctor(@Valid @RequestBody DoctorDTO doctorDTO) throws MessagingException, IOException, TemplateException, javax.mail.MessagingException {
        DoctorDTO savedDoctorDTO = doctorService.createDoctor(doctorDTO);
        return new ResponseEntity(savedDoctorDTO, HttpStatus.CREATED);

    }

    @PostMapping(value = "/doctors/{doctorId}/documents")
    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
    public ResponseEntity<String> uploadFiles(@PathVariable String doctorId, @RequestParam MultipartFile[] files) throws IOException {
        for (MultipartFile file: files){
            awsRepository.uploadFiles(doctorId, file);
        }
        return ResponseEntity.ok().body("File(s) uploaded Successfully");
    }

    @PutMapping(value = "/doctors/{doctorId}/approve")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity approveDoctorRegistration(@PathVariable("doctorId") String doctorId
            , @RequestBody UpdateDoctorDTO updateDoctorDTO) throws Exception {
        DetailDoctorDTO detailDoctorServiceDTO = doctorService.updateDoctor(doctorId, updateDoctorDTO, "Active");
        return new ResponseEntity(detailDoctorServiceDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/doctors/{doctorId}/reject")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity rejectDoctorRegistration(@PathVariable("doctorId") String doctorId
            , @RequestBody UpdateDoctorDTO updateDoctorDTO) throws Exception {
        DetailDoctorDTO detailDoctorServiceDTO = doctorService.updateDoctor(doctorId, updateDoctorDTO, "Rejected");
        return new ResponseEntity(detailDoctorServiceDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/doctors")
    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
    public ResponseEntity<List<DoctorEntity>> getDoctorByStatusAndSpeciality(String status, String speciality){
        List<DoctorEntity> doctorList = doctorService.getDoctorByStatusAndSpeciality(status, speciality);
        return new ResponseEntity(doctorList, HttpStatus.OK);
    }

    @GetMapping(value = "/doctors/{doctorId}")
    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
    public ResponseEntity findDoctor(@PathVariable(name="doctorId") String doctorId){
        DoctorEntity doctorEntity = doctorService.getDoctor(doctorId);
        DoctorDTO savedDoctorDTO = modelMapper.map(doctorEntity, DoctorDTO.class);
        return new ResponseEntity(savedDoctorDTO, HttpStatus.CREATED);
    }


    @GetMapping(value = "/doctors/{doctorId}/documents/metadata")
    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
    public ResponseEntity<List<String>> getMetaData(@PathVariable String doctorId) {
        List<String> metadata = awsRepository.listFiles(doctorId);
        return new ResponseEntity<>(metadata, HttpStatus.OK);
    }

    @GetMapping(value = "/doctors/{doctorId}/documents/{documentName}")
    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
    public ResponseEntity<byte[]> getFile(@PathVariable String doctorId, @PathVariable(name="documentName") String documentName){
        ByteArrayOutputStream downloadBytesData = awsRepository.downloadFile(doctorId, documentName);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + documentName + "\"")
                .body(downloadBytesData.toByteArray());
    }

}
