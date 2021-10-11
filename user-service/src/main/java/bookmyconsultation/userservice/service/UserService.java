package bookmyconsultation.userservice.service;

import bookmyconsultation.userservice.dto.UserDTo;
import bookmyconsultation.userservice.entity.UserEntity;
import freemarker.template.TemplateException;
import javax.mail.MessagingException;
import java.io.IOException;


// this interface is used to define functions which helps
// to return API responses.
public interface UserService {

    public UserDTo createUser(UserDTo userDTO) throws TemplateException, IOException, MessagingException;

    public UserEntity getUser(String userId);
    
}
