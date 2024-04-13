package com.exschoolapp.registrationservice.service;

import com.exschoolapp.registrationservice.dto.RegistrationRequest;
import com.exschoolapp.registrationservice.dto.RegistrationResponse;

import java.util.List;

public interface RegistrationService {

    public RegistrationResponse createRegistration(RegistrationRequest createRegistrationRequest);
    public List<RegistrationResponse> findAllRegistrations();
    public RegistrationResponse findRegistrationById(Long id);
    public void deleteRegistrationById(Long id);
    public RegistrationResponse updateRegistrationById(Long id, RegistrationRequest updateRegistrationRequest);
}
