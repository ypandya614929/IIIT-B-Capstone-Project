package bookmyconsultation.appointmentservice.mapper;

import bookmyconsultation.appointmentservice.dto.AppointmentRequestDTO;
import bookmyconsultation.appointmentservice.dto.AppointmentResponseDTO;
import bookmyconsultation.appointmentservice.entity.AppointmentEntity;

import java.sql.Date;


public class AppointmentServiceMapper {

    public static AppointmentEntity DTOToEntity(AppointmentRequestDTO appointmentServiceRequestDTO){
        AppointmentEntity appointmentServiceEntity = new AppointmentEntity();
        appointmentServiceEntity.setDoctorId(appointmentServiceRequestDTO.getDoctorId());
        appointmentServiceEntity.setDoctorName(appointmentServiceRequestDTO.getDoctorName());
        appointmentServiceEntity.setUserId(appointmentServiceRequestDTO.getUserId());
        appointmentServiceEntity.setAppointmentDate(Date.valueOf(appointmentServiceRequestDTO.getAppointmentDate()));
        appointmentServiceEntity.setTimeSlot(appointmentServiceRequestDTO.getTimeSlot());
        appointmentServiceEntity.setStatus("PendingPayment");
        return appointmentServiceEntity;
    }

    public static AppointmentResponseDTO EntityToDTO(AppointmentEntity appointmentServiceEntity){
        AppointmentResponseDTO appointmentServiceResponseDTO = new AppointmentResponseDTO();
        appointmentServiceResponseDTO.setAppointmentId(appointmentServiceEntity.getId());
        appointmentServiceResponseDTO.setAppointmentDate(String.valueOf(appointmentServiceEntity.getAppointmentDate()));
        appointmentServiceResponseDTO.setDoctorId(appointmentServiceEntity.getDoctorId());
        appointmentServiceResponseDTO.setUserId(appointmentServiceEntity.getUserId());
        appointmentServiceResponseDTO.setTimeSlot(appointmentServiceEntity.getTimeSlot());
        appointmentServiceResponseDTO.setStatus(appointmentServiceEntity.getStatus());
        appointmentServiceResponseDTO.setDoctorName(appointmentServiceEntity.getDoctorName());
        return appointmentServiceResponseDTO;
    }

}
