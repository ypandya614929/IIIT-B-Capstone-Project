package bookmyconsultation.doctorservice.service;

import bookmyconsultation.doctorservice.dto.DetailDoctorDTO;
import bookmyconsultation.doctorservice.dto.DoctorDTO;
import bookmyconsultation.doctorservice.dto.UpdateDoctorDTO;
import bookmyconsultation.doctorservice.entity.DoctorEntity;
import bookmyconsultation.doctorservice.exception.DoctorNotFoundException;
import bookmyconsultation.doctorservice.mapper.DoctorMapper;
import bookmyconsultation.doctorservice.repository.AWSRepository;
import bookmyconsultation.doctorservice.repository.DoctorRepository;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.kafka.clients.producer.Producer;
import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    DoctorRepository doctorServiceRepository;

    @Autowired
    AWSRepository awsRepository;

    @Autowired
    Producer<String, String> producer;

    @Override
    public DoctorDTO createDoctor(DoctorDTO doctorServiceDTO) throws TemplateException, IOException, MessagingException {
        DoctorEntity doctorEntity = DoctorMapper.DTOToEntity(doctorServiceDTO);
        doctorServiceRepository.save(doctorEntity);
        DoctorDTO savedDoctorDTO = DoctorMapper.EntityToDTO(doctorEntity);
        String message = savedDoctorDTO.toString();
//        producer.send(new ProducerRecord<>("message","message", message));
        awsRepository.sendEmail(savedDoctorDTO);
        return savedDoctorDTO;
    }

    @Override
    public DoctorEntity getDoctor(String doctorId) {
        if (doctorServiceRepository.existsById(doctorId)) {
            DoctorEntity doctorEntity = doctorServiceRepository.findById(doctorId).get();
            return doctorEntity;
        }
        throw new DoctorNotFoundException();
    }

    @Override
    public DetailDoctorDTO updateDoctor(String doctorId, UpdateDoctorDTO updateDoctorServiceDTO, String status) throws TemplateException, IOException, MessagingException {
        DoctorEntity doctorEntity = getDoctor(doctorId);
        DoctorMapper.UpdateEntity(doctorEntity, updateDoctorServiceDTO, status);
        doctorServiceRepository.save(doctorEntity);
        DetailDoctorDTO detailDoctorServiceDTO = DoctorMapper.EntityToDetailDTO(doctorEntity);
        String message = detailDoctorServiceDTO.toString();
//        producer.send(new ProducerRecord<String, String>("message", "DOCTOR_SERVICE", message));
        awsRepository.sendVerificationEmail(detailDoctorServiceDTO);
        return detailDoctorServiceDTO;
    }

    @Override
    public List<DoctorEntity> getDoctorByStatusAndSpeciality(String status, String speciality){
        List<DoctorEntity> doctorList = new ArrayList<>();
        if ((status != null && !status.isEmpty() && !status.equalsIgnoreCase("")) && (speciality != null && !speciality.isEmpty() && !speciality.equalsIgnoreCase(""))){
            doctorList = doctorServiceRepository.findByStatusAndSpeciality(status, speciality);
        }
        else if ((status!=null && !status.isEmpty() && !status.equalsIgnoreCase("")) && speciality==null) {
            for(DoctorEntity doctorEntity : doctorServiceRepository.findByStatus(status)){
                doctorList.add(doctorEntity);
            }
        }
        else if ((speciality!=null && !speciality.isEmpty() && !speciality.equalsIgnoreCase("")) && status == null) {
            for (DoctorEntity doctorEntity : doctorServiceRepository.findBySpeciality(speciality)) {
                doctorList.add(doctorEntity);
            }
        }
        else {
            doctorList = doctorServiceRepository.findAll();
        }
        return doctorList;
    }
}