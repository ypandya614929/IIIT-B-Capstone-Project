package bookmyconsultation.doctorservice.service;

import bookmyconsultation.doctorservice.dto.DetailDoctorServiceDTO;
import bookmyconsultation.doctorservice.dto.DoctorServiceDTO;
import bookmyconsultation.doctorservice.dto.UpdateDoctorServiceDTO;
import bookmyconsultation.doctorservice.entity.DoctorServiceEntity;
import freemarker.template.TemplateException;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public interface DoctorService {

    public DoctorServiceDTO createDoctor(DoctorServiceDTO doctorServiceDTO) throws TemplateException, IOException, MessagingException;

    public DoctorServiceEntity getDoctor(String doctorId);

    public DetailDoctorServiceDTO updateDoctor(String doctorId, UpdateDoctorServiceDTO updateDoctorServiceDTO, String status) throws TemplateException, IOException, MessagingException;

    public List<DoctorServiceEntity> getDoctorByStatusAndSpeciality(String status, String speciality);
}
