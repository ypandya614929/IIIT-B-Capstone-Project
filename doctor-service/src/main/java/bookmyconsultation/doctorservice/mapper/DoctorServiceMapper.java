package bookmyconsultation.doctorservice.mapper;

import bookmyconsultation.doctorservice.dto.DetailDoctorServiceDTO;
import bookmyconsultation.doctorservice.dto.DoctorServiceDTO;
import bookmyconsultation.doctorservice.dto.UpdateDoctorServiceDTO;
import bookmyconsultation.doctorservice.entity.DoctorServiceEntity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DoctorServiceMapper {

    public static DoctorServiceEntity DTOToEntity(DoctorServiceDTO doctorDTO){
        DoctorServiceEntity doctorEntity = new DoctorServiceEntity();
        doctorEntity.setFirstName(doctorDTO.getFirstName());
        doctorEntity.setLastName(doctorDTO.getLastName());
        doctorEntity.setDob(doctorDTO.getDob());
        doctorEntity.setEmailId(doctorDTO.getEmailId());
        doctorEntity.setMobile(doctorDTO.getMobile());
        doctorEntity.setPan(doctorDTO.getPan());
        doctorEntity.setRegistrationDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        return doctorEntity;
    }

    public static DoctorServiceDTO EntityToDTO(DoctorServiceEntity doctorEntity){
        DoctorServiceDTO doctorDTO = new DoctorServiceDTO();
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

    public static DoctorServiceEntity UpdateEntity(DoctorServiceEntity doctorEntity, UpdateDoctorServiceDTO updateDoctorServiceDTO, String status){
        doctorEntity.setStatus(status);
        doctorEntity.setApprovedBy(updateDoctorServiceDTO.getApprovedBy());
        doctorEntity.setApproverComments(updateDoctorServiceDTO.getApproverComments());
        doctorEntity.setVerificationDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        return doctorEntity;
    }

    public static DetailDoctorServiceDTO EntityToDetailDTO(DoctorServiceEntity doctorEntity){
        DetailDoctorServiceDTO detailDoctorDTO = new DetailDoctorServiceDTO();
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
