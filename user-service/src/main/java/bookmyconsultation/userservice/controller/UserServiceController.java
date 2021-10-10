package bookmyconsultation.userservice.controller;

import bookmyconsultation.userservice.dto.UserServiceDTO;
import bookmyconsultation.userservice.entity.UserServiceEntity;
import bookmyconsultation.userservice.repository.AWSRepository;
import bookmyconsultation.userservice.service.UserService;
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
public class UserServiceController {

    @Autowired
    UserService userService;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    AWSRepository awsRepository;

    @PostMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addUser(@RequestBody UserServiceDTO userDTO) throws MessagingException, IOException, TemplateException, javax.mail.MessagingException {
        UserServiceDTO savedUserDTO = userService.createUser(userDTO);
        return new ResponseEntity(savedUserDTO, HttpStatus.CREATED);

    }


    @GetMapping(value = "/users/{userId}")
    public ResponseEntity findUser(@PathVariable(name="userId") String userId){
        UserServiceEntity userEntity = userService.getUser(userId);
        UserServiceDTO savedUserDTO = modelMapper.map(userEntity, UserServiceDTO.class);
        return new ResponseEntity(savedUserDTO, HttpStatus.CREATED);
    }


    @PostMapping(value = "/users/{id}/documents")
    public ResponseEntity<String> uploadFiles(@PathVariable(name = "id") String userId, @RequestParam MultipartFile[] files) throws IOException {
        for (MultipartFile file: files){
            awsRepository.uploadFiles(userId, file);
        }
        return ResponseEntity.ok().body("File(s) uploaded Successfully");
    }

    @GetMapping(value = "/users/{id}/documents/{documentName}")
    public ResponseEntity<byte[]> getFile(@PathVariable(name="id") String id, @PathVariable(name="documentName") String documentName){
        ByteArrayOutputStream downloadBytesData = awsRepository.downloadFile(id, documentName);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + documentName + "\"")
                .body(downloadBytesData.toByteArray());
    }

    @GetMapping(value = "/users/{id}/documents/metadata")
    public ResponseEntity<List<String>> getMetaData(@PathVariable(name="id") String id) {
        List<String> metadata = awsRepository.listFiles(id);
        return new ResponseEntity<>(metadata, HttpStatus.OK);
    }

    @GetMapping("/verify")
    public ResponseEntity verifyEmail(@RequestParam("email") String emailId){
        awsRepository.verifyEmail(emailId);
        return ResponseEntity.ok().build();
    }
}
