package bookmyconsultation.doctorservice.mapper;

import bookmyconsultation.doctorservice.dto.DetailDoctorDTO;
import bookmyconsultation.doctorservice.dto.DoctorDTO;
import bookmyconsultation.doctorservice.dto.UpdateDoctorDTO;
import bookmyconsultation.doctorservice.entity.DoctorEntity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DoctorMapper {

    public static DoctorEntity DTOToEntity(DoctorDTO doctorDTO){
        DoctorEntity doctorEntity = new DoctorEntity();
        doctorEntity.setFirstName(doctorDTO.getFirstName());
        doctorEntity.setLastName(doctorDTO.getLastName());
        doctorEntity.setDob(doctorDTO.getDob());
        doctorEntity.setEmailId(doctorDTO.getEmailId());
        doctorEntity.setMobile(doctorDTO.getMobile());
        doctorEntity.setPan(doctorDTO.getPan());
        doctorEntity.setRegistrationDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        return doctorEntity;
    }

    public static DoctorDTO EntityToDTO(DoctorEntity doctorEntity){
        DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO.setId(doctorEntity.getId());
        doctorDTO.setFirstName(doctorEntity.getFirstName());
        doctorDTO.setLastName(doctorEntity.getLastName());
        doctorDTO.setDob(doctorEntity.getDob());
        doctorDTO.setEmailId(doctorEntity.getEmailId());
        doctorDTO.setMobile(doctorEntity.getMobile());
        doctorDTO.setPan(doctorEntity.getPan());
        doctorDTO.setSpeciality(doctorEntity.getSpeciality());
        doctorDTO.setStatus(doctorEntity.getStatus());
        doctorDTO.setRegistrationDate(doctorEntity.getRegistrationDate());
        return doctorDTO;
    }

    public static DoctorEntity UpdateEntity(DoctorEntity doctorEntity, UpdateDoctorDTO updateDoctorServiceDTO, String status){
        doctorEntity.setStatus(status);
        doctorEntity.setApprovedBy(updateDoctorServiceDTO.getApprovedBy());
        doctorEntity.setApproverComments(updateDoctorServiceDTO.getApproverComments());
        doctorEntity.setVerificationDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        return doctorEntity;
    }

    public static DetailDoctorDTO EntityToDetailDTO(DoctorEntity doctorEntity){
        DetailDoctorDTO detailDoctorDTO = new DetailDoctorDTO();
        detailDoctorDTO.setId(doctorEntity.getId());
        detailDoctorDTO.setFirstName(doctorEntity.getFirstName());
        detailDoctorDTO.setLastName(doctorEntity.getLastName());
        detailDoctorDTO.setDob(doctorEntity.getDob());
        detailDoctorDTO.setEmailId(doctorEntity.getEmailId());
        detailDoctorDTO.setMobile(doctorEntity.getMobile());
        detailDoctorDTO.setPan(doctorEntity.getPan());
        detailDoctorDTO.setSpeciality(doctorEntity.getSpeciality());
        detailDoctorDTO.setStatus(doctorEntity.getStatus());
        detailDoctorDTO.setRegistrationDate(doctorEntity.getRegistrationDate());
        detailDoctorDTO.setApprovedBy(doctorEntity.getApprovedBy());
        detailDoctorDTO.setApproverComments(doctorEntity.getApproverComments());
        detailDoctorDTO.setVerificationDate(doctorEntity.getVerificationDate());
        return detailDoctorDTO;
    }
}
