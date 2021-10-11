package bookmyconsultation.doctorservice.service;

import bookmyconsultation.doctorservice.dto.DetailDoctorDTO;
import bookmyconsultation.doctorservice.dto.DoctorDTO;
import bookmyconsultation.doctorservice.dto.UpdateDoctorDTO;
import bookmyconsultation.doctorservice.entity.DoctorEntity;
import freemarker.template.TemplateException;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public interface DoctorService {

    public DoctorDTO createDoctor(DoctorDTO doctorServiceDTO) throws TemplateException, IOException, MessagingException;

    public DoctorEntity getDoctor(String doctorId);

    public DetailDoctorDTO updateDoctor(String doctorId, UpdateDoctorDTO updateDoctorServiceDTO, String status) throws TemplateException, IOException, MessagingException;

    public List<DoctorEntity> getDoctorByStatusAndSpeciality(String status, String speciality);
}
