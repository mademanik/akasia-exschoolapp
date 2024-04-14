package com.exschoolapp.registrationservice.service;

import com.exschoolapp.registrationservice.constant.AppConstants;
import com.exschoolapp.registrationservice.dto.*;
import com.exschoolapp.registrationservice.helper.ValidationErrors;
import com.exschoolapp.registrationservice.helper.Validations;
import com.exschoolapp.registrationservice.mapper.RegistrationMapper;
import com.exschoolapp.registrationservice.model.Registration;
import com.exschoolapp.registrationservice.repository.RegistrationRepository;
import com.exschoolapp.registrationservice.repository.outbound.ExtracurricularRepository;
import com.exschoolapp.registrationservice.repository.outbound.StudentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private KafkaTemplate<String, NotificationRequest> kafkaTemplate;

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ExtracurricularRepository extracurricularRepository;

    @Autowired
    private RegistrationMapper registrationMapper;

    @Override
    public RegistrationResponse createRegistration(RegistrationRequest createRegistrationRequest) {
        //get student data
        StudentResponse studentResponse = findStudentData(createRegistrationRequest.getStudentId());
        //get extracurricular data
        ExtracurricularResponse extracurricularResponse = findExtracurricularData(createRegistrationRequest.getExtracurricularId());
        //validate registration request
        this.validateRegistration(createRegistrationRequest, extracurricularResponse);
        //save registration
        Registration registration = registrationMapper.mapToRegistration(createRegistrationRequest);
        registrationRepository.save(registration);

        RegistrationResponse registrationResponse = registrationMapper.mapToRegistrationResponse(registration);
        registrationResponse.setStudent(studentResponse);
        registrationResponse.setExtracurricular(extracurricularResponse);

        //send kafka request
        this.sendKafkaRequest(studentResponse, extracurricularResponse);
        return registrationResponse;
    }

    private void sendKafkaRequest(StudentResponse studentResponse, ExtracurricularResponse extracurricularResponse) {
        NotificationRequest notificationRequest = NotificationRequest.builder()
                .message("Student name : " + studentResponse.getName() + " successfully registered in: "
                        + extracurricularResponse.getName().toString() + " Extracurricular")
                .serviceName("registration-service")
                .toEmail(studentResponse.getEmail())
                .createdAt(LocalDateTime.now(ZoneId.of("Asia/Jakarta")))
                .build();

        //send notification via kafka producers
        log.info("Send request to kafka: " + notificationRequest.toString());
        kafkaTemplate.send(AppConstants.TOPIC_NAME, notificationRequest);
    }

    @Override
    public List<RegistrationResponse> findAllRegistrations() {
        List<RegistrationResponse> registrationResponses = registrationRepository.findAll().stream()
                .map(registration -> {
                    RegistrationResponse registrationResponse = registrationMapper.mapToRegistrationResponse(registration);
                    //get student data
                    StudentResponse studentResponse = findStudentData(registrationResponse.getStudentId());
                    //get extracurricular data
                    ExtracurricularResponse extracurricularResponse = findExtracurricularData(registrationResponse.getExtracurricularId());
                    registrationResponse.setStudent(studentResponse);
                    registrationResponse.setExtracurricular(extracurricularResponse);
                    return registrationResponse;
                })
                .collect(Collectors.toList());

        return registrationResponses;
    }

    @Override
    public RegistrationResponse findRegistrationById(Long id) {
        Registration registration = registrationRepository.findById(id).orElse(null);
        if (registration != null) {
            RegistrationResponse registrationResponse = registrationMapper.mapToRegistrationResponse(registration);
            //get student data
            StudentResponse studentResponse = findStudentData(registrationResponse.getStudentId());
            //get extracurricular data
            ExtracurricularResponse extracurricularResponse = findExtracurricularData(registrationResponse.getExtracurricularId());
            registrationResponse.setStudent(studentResponse);
            registrationResponse.setExtracurricular(extracurricularResponse);
            return registrationResponse;
        } else {
            return null;
        }
    }

    @Override
    public void deleteRegistrationById(Long id) {
        Optional<Registration> optOfRegistration = registrationRepository.findById(id);
        Validations.checkArgument(
                optOfRegistration.isPresent(), ValidationErrors.REGISTRATION_NOT_FOUND);
        Registration registration = optOfRegistration.get();
        registrationRepository.deleteById(registration.getId());
    }

    @Override
    public RegistrationResponse updateRegistrationById(Long id, RegistrationRequest updateRegistrationRequest) {
        Optional<Registration> optOfRegistration = registrationRepository.findById(id);
        Validations.checkArgument(
                optOfRegistration.isPresent(), ValidationErrors.REGISTRATION_NOT_FOUND);
        Registration registration = optOfRegistration.get();
        //get student data
        StudentResponse studentResponse = findStudentData(updateRegistrationRequest.getStudentId());
        //get extracurricular data
        ExtracurricularResponse extracurricularResponse = findExtracurricularData(updateRegistrationRequest.getExtracurricularId());
        //validate registration request
        this.validateRegistration(updateRegistrationRequest, extracurricularResponse);
        //save registration
        registrationMapper.mapUpdateRegistration(updateRegistrationRequest, registration);
        registrationRepository.save(registration);
        RegistrationResponse registrationResponse = registrationMapper.mapToRegistrationResponse(registration);
        registrationResponse.setStudent(studentResponse);
        registrationResponse.setExtracurricular(extracurricularResponse);

        //send kafka request
        this.sendKafkaRequest(studentResponse, extracurricularResponse);
        return registrationResponse;
    }

    private StudentResponse findStudentData(Long studentId) {
        //get student data
        ResponseEntity<RestResponse> restResponseStudent = studentRepository.findStudentById(studentId);
        return convertDataToObject(restResponseStudent.getBody().getData(), StudentResponse.class);
    }

    private ExtracurricularResponse findExtracurricularData(Long extracurricularId) {
        //get extracurricular data
        ResponseEntity<RestResponse> restResponseExtracurricular = extracurricularRepository.findExtracurricularById(extracurricularId);
        return convertDataToObject(restResponseExtracurricular.getBody().getData(), ExtracurricularResponse.class);
    }

    private void validateRegistration(
            RegistrationRequest registrationRequest, ExtracurricularResponse extracurricularResponse) {
        Optional<Registration> optOfRegistration = registrationRepository
                .findRegistrationByStudentIdAndExtracurricularId(registrationRequest.getStudentId()
                        , registrationRequest.getExtracurricularId());
        Validations.checkArgument(
                !optOfRegistration.isPresent(), ValidationErrors.REGISTRATION_ALREADY_EXIST);
        Integer countCurrentRegistration = registrationRepository.countRegistrationByExtracurricularId(extracurricularResponse.getId());
        Validations.checkArgument(
                countCurrentRegistration < extracurricularResponse.getQuota(), ValidationErrors.REGISTRATION_QUOTA_FULL);

        boolean isEligibleRegistrationStartDate = LocalDateTime.now(ZoneId.of("Asia/Jakarta"))
                .isAfter(extracurricularResponse.getRegistrationStartDate());
        Validations.checkArgument(
                isEligibleRegistrationStartDate, ValidationErrors.REGISTRATION_NOT_STARTED);

        boolean isEligibleRegistrationEndDate = LocalDateTime.now(ZoneId.of("Asia/Jakarta"))
                .isBefore(extracurricularResponse.getRegistrationEndDate());
        Validations.checkArgument(
                isEligibleRegistrationEndDate, ValidationErrors.REGISTRATION_CLOSED);
    }

    private <T> T convertDataToObject(Object data, Class<T> targetType) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        return objectMapper.convertValue(data, targetType);
    }

}
