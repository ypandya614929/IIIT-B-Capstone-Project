package bookmyconsultation.appointmentservice.mapper;

import bookmyconsultation.appointmentservice.dto.AppointmentServiceRequestDTO;
import bookmyconsultation.appointmentservice.dto.AppointmentServiceResponseDTO;
import bookmyconsultation.appointmentservice.entity.AppointmentServiceEntity;


public class AppointmentServiceMapper {

    public static AppointmentServiceEntity DTOToEntity(AppointmentServiceRequestDTO appointmentServiceRequestDTO){
        AppointmentServiceEntity appointmentServiceEntity = new AppointmentServiceEntity();
        appointmentServiceEntity.setDoctorId(appointmentServiceRequestDTO.getDoctorId());
        appointmentServiceEntity.setUserId(appointmentServiceRequestDTO.getUserId());
        appointmentServiceEntity.setAppointmentDate(appointmentServiceRequestDTO.getAppointmentDate());
        appointmentServiceEntity.setTimeSlot(appointmentServiceRequestDTO.getTimeSlot());
        appointmentServiceEntity.setStatus("PendingPayment");
        return appointmentServiceEntity;
    }

    public static AppointmentServiceResponseDTO EntityToDTO(AppointmentServiceEntity appointmentServiceEntity){
        AppointmentServiceResponseDTO appointmentServiceResponseDTO = new AppointmentServiceResponseDTO();
        appointmentServiceResponseDTO.setAppointmentId(appointmentServiceEntity.getId());
        appointmentServiceResponseDTO.setAppointmentDate(appointmentServiceEntity.getAppointmentDate());
        appointmentServiceResponseDTO.setDoctorId(appointmentServiceEntity.getDoctorId());
        appointmentServiceResponseDTO.setUserId(appointmentServiceEntity.getUserId());
        appointmentServiceResponseDTO.setTimeSlot(appointmentServiceEntity.getTimeSlot());
        appointmentServiceResponseDTO.setStatus(appointmentServiceEntity.getStatus());
        return appointmentServiceResponseDTO;
    }

}
