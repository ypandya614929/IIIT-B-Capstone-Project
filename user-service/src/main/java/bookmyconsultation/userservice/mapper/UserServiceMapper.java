package bookmyconsultation.userservice.mapper;

import bookmyconsultation.userservice.dto.UserServiceDTO;
import bookmyconsultation.userservice.entity.UserServiceEntity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UserServiceMapper {

    public static UserServiceEntity DTOToEntity(UserServiceDTO userDTO){
        UserServiceEntity userEntity = new UserServiceEntity();
        userEntity.setFirstName(userDTO.getFirstName());
        userEntity.setLastName(userDTO.getLastName());
        userEntity.setDob(userDTO.getDob());
        userEntity.setEmailId(userDTO.getEmailId());
        userEntity.setMobile(userDTO.getMobile());
        userEntity.setCreatedDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        return userEntity;
    }

    public static UserServiceDTO EntityToDTO(UserServiceEntity userEntity){
        UserServiceDTO userDTO = new UserServiceDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setFirstName(userEntity.getFirstName());
        userDTO.setLastName(userEntity.getLastName());
        userDTO.setDob(userEntity.getDob());
        userDTO.setEmailId(userEntity.getEmailId());
        userDTO.setMobile(userEntity.getMobile());
        userDTO.setCreatedDate(userEntity.getCreatedDate());
        return userDTO;
    }
}
