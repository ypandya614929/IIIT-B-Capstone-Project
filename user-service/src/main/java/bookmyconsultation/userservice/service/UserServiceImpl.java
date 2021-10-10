package bookmyconsultation.userservice.service;

import bookmyconsultation.userservice.dto.UserServiceDTO;
import bookmyconsultation.userservice.entity.UserServiceEntity;
import bookmyconsultation.userservice.mapper.UserServiceMapper;
import bookmyconsultation.userservice.repository.AWSRepository;
import bookmyconsultation.userservice.repository.UserServiceRepository;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import javax.mail.MessagingException;
import java.io.IOException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserServiceRepository userServiceRepository;

    @Autowired
    AWSRepository awsRepository;

    @Autowired
    Producer<String, String> producer;

    @Override
    public UserServiceDTO createUser(UserServiceDTO userDTO) throws TemplateException, IOException, MessagingException {
        UserServiceEntity userEntity= UserServiceMapper.DTOToEntity(userDTO);
        userServiceRepository.save(userEntity);
        UserServiceDTO savedUserDTO = UserServiceMapper.EntityToDTO(userEntity);
        String message = savedUserDTO.toString();
//        producer.send(new ProducerRecord<>("message","message", message));
        awsRepository.sendEmail(savedUserDTO);
        return savedUserDTO;
    }

    @Override
    public UserServiceEntity getUser(String userId) {
        if (userServiceRepository.existsById(userId)) {
            UserServiceEntity userEntity = userServiceRepository.findById(userId).get();
            return userEntity;
        }
        return null;
    }
}