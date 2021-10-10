package bookmyconsultation.doctorservice.service;

import bookmyconsultation.doctorservice.dto.DetailDoctorServiceDTO;
import bookmyconsultation.doctorservice.dto.DoctorServiceDTO;
import bookmyconsultation.doctorservice.dto.UpdateDoctorServiceDTO;
import bookmyconsultation.doctorservice.entity.DoctorServiceEntity;
import bookmyconsultation.doctorservice.mapper.DoctorServiceMapper;
import bookmyconsultation.doctorservice.repository.AWSRepository;
import bookmyconsultation.doctorservice.repository.DoctorServiceRepository;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.kafka.clients.producer.Producer;

import javax.mail.MessagingException;
import javax.print.Doc;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    DoctorServiceRepository doctorServiceRepository;

    @Autowired
    AWSRepository awsRepository;

    @Autowired
    Producer<String, String> producer;

    @Override
    public DoctorServiceDTO createDoctor(DoctorServiceDTO doctorServiceDTO) throws TemplateException, IOException, MessagingException {
        DoctorServiceEntity doctorEntity = DoctorServiceMapper.DTOToEntity(doctorServiceDTO);
        doctorServiceRepository.save(doctorEntity);
        DoctorServiceDTO savedDoctorDTO = DoctorServiceMapper.EntityToDTO(doctorEntity);
        String message = savedDoctorDTO.toString();
//        producer.send(new ProducerRecord<>("message","message", message));
        awsRepository.sendEmail(savedDoctorDTO);
        return savedDoctorDTO;
    }

    @Override
    public DoctorServiceEntity getDoctor(String doctorId) {
        if (doctorServiceRepository.existsById(doctorId)) {
            DoctorServiceEntity doctorEntity = doctorServiceRepository.findById(doctorId).get();
            return doctorEntity;
        }
        return null;
    }

    @Override
    public DetailDoctorServiceDTO updateDoctor(String doctorId, UpdateDoctorServiceDTO updateDoctorServiceDTO, String status) throws TemplateException, IOException, MessagingException {
        DoctorServiceEntity doctorEntity = getDoctor(doctorId);
        if (doctorEntity == null){
            return null;
        }
        DoctorServiceMapper.UpdateEntity(doctorEntity, updateDoctorServiceDTO, status);
        doctorServiceRepository.save(doctorEntity);
        DetailDoctorServiceDTO detailDoctorServiceDTO = DoctorServiceMapper.EntityToDetailDTO(doctorEntity);
        String message = detailDoctorServiceDTO.toString();
//        producer.send(new ProducerRecord<String, String>("message", "message", message));
        awsRepository.sendVerificationEmail(detailDoctorServiceDTO);
        return detailDoctorServiceDTO;
    }

    @Override
    public List<DoctorServiceEntity> getDoctorByStatusAndSpeciality(String status, String speciality){
        List<DoctorServiceEntity> doctorList = new ArrayList<>();
        if ((status != null && !status.isEmpty() && !status.equalsIgnoreCase("")) && (speciality != null && !speciality.isEmpty() && !speciality.equalsIgnoreCase(""))){
            doctorList = doctorServiceRepository.findByStatusAndSpeciality(status, speciality);
        }
        else if ((status!=null && !status.isEmpty() && !status.equalsIgnoreCase("")) && speciality==null) {
            for(DoctorServiceEntity doctorEntity : doctorServiceRepository.findByStatus(status)){
                doctorList.add(doctorEntity);
            }
        }
        else if ((speciality!=null && !speciality.isEmpty() && !speciality.equalsIgnoreCase("")) && status == null) {
            for (DoctorServiceEntity doctorEntity : doctorServiceRepository.findBySpeciality(speciality)) {
                doctorList.add(doctorEntity);
            }
        }
        else {
            doctorList = doctorServiceRepository.findAll();
        }
        return doctorList;
    }
}