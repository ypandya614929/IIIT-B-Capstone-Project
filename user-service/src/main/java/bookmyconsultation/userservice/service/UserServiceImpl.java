package bookmyconsultation.userservice.service;

import bookmyconsultation.userservice.dto.UserDTo;
import bookmyconsultation.userservice.entity.UserEntity;
import bookmyconsultation.userservice.exception.UserNotFoundException;
import bookmyconsultation.userservice.mapper.UserMapper;
import bookmyconsultation.userservice.repository.AWSRepository;
import bookmyconsultation.userservice.repository.UserRepository;
import freemarker.template.TemplateException;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.kafka.clients.producer.Producer;
import javax.mail.MessagingException;
import java.io.IOException;


// This class implements defined methods in UserService interface
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AWSRepository awsRepository;

    @Autowired
    Producer<String, String> producer;

    @Override
    public UserDTo createUser(UserDTo userDTO) throws TemplateException, IOException, MessagingException {
        UserEntity userEntity= UserMapper.DTOToEntity(userDTO);
        userRepository.save(userEntity);
        UserDTo savedUserDTO = UserMapper.EntityToDTO(userEntity);
        String message = savedUserDTO.toString();
        producer.send(new ProducerRecord<>("message","USER_SERVICE", message));
        awsRepository.sendEmail(savedUserDTO);
        return savedUserDTO;
    }

    @Override
    public UserEntity getUser(String userId) {
        if (userRepository.existsById(userId)) {
            UserEntity userEntity = userRepository.findById(userId).get();
            return userEntity;
        }
        throw new UserNotFoundException();
    }
}