package bookmyconsultation.userservice.service;

import bookmyconsultation.userservice.dto.UserServiceDTO;
import bookmyconsultation.userservice.entity.UserServiceEntity;
import freemarker.template.TemplateException;

import javax.mail.MessagingException;
import java.io.IOException;

public interface UserService {

    public UserServiceDTO createUser(UserServiceDTO userDTO) throws TemplateException, IOException, MessagingException;

    public UserServiceEntity getUser(String userId);
    
}
