package bookmyconsultation.userservice.mapper;

import bookmyconsultation.userservice.dto.UserDTo;
import bookmyconsultation.userservice.entity.UserEntity;
import java.text.SimpleDateFormat;
import java.util.Date;


// this class is a mapper class which converts
// dto to entity and entity to dto
public class UserMapper {

    public static UserEntity DTOToEntity(UserDTo userDTO){
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(userDTO.getFirstName());
        userEntity.setLastName(userDTO.getLastName());
        userEntity.setDob(userDTO.getDob());
        userEntity.setEmailId(userDTO.getEmailId());
        userEntity.setMobile(userDTO.getMobile());
        userEntity.setCreatedDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        return userEntity;
    }

    public static UserDTo EntityToDTO(UserEntity userEntity){
        UserDTo userDTO = new UserDTo();
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
