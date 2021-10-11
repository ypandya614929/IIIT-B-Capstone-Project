package bookmyconsultation.userservice.controller;

import bookmyconsultation.userservice.dto.UserDTo;
import bookmyconsultation.userservice.entity.UserEntity;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;


@RequestMapping
@RestController
@Validated
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    AWSRepository awsRepository;

    @PostMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity addUser(@Valid @RequestBody UserDTo userDTO) throws MessagingException, IOException, TemplateException, javax.mail.MessagingException {
        UserDTo savedUserDTO = userService.createUser(userDTO);
        return new ResponseEntity(savedUserDTO, HttpStatus.CREATED);

    }


    @GetMapping(value = "/users/{userId}")
    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
    public ResponseEntity findUser(@PathVariable(name="userId") String userId){
        UserEntity userEntity = userService.getUser(userId);
        UserDTo savedUserDTO = modelMapper.map(userEntity, UserDTo.class);
        return new ResponseEntity(savedUserDTO, HttpStatus.CREATED);
    }


    @PostMapping(value = "/users/{id}/documents")
    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
    public ResponseEntity<String> uploadFiles(@PathVariable(name = "id") String userId, @RequestParam MultipartFile[] files) throws IOException {
        for (MultipartFile file: files){
            awsRepository.uploadFiles(userId, file);
        }
        return ResponseEntity.ok().body("File(s) uploaded Successfully");
    }


    @GetMapping(value = "/users/{id}/documents/{documentName}")
    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
    public ResponseEntity<byte[]> getFile(@PathVariable(name="id") String id, @PathVariable(name="documentName") String documentName){
        ByteArrayOutputStream downloadBytesData = awsRepository.downloadFile(id, documentName);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + documentName + "\"")
                .body(downloadBytesData.toByteArray());
    }


    @GetMapping(value = "/users/{id}/documents/metadata")
    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
    public ResponseEntity<List<String>> getMetaData(@PathVariable(name="id") String id) {
        List<String> metadata = awsRepository.listFiles(id);
        return new ResponseEntity<>(metadata, HttpStatus.OK);
    }

}
