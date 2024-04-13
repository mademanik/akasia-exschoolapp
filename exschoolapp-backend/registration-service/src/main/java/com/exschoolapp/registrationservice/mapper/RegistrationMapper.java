package com.exschoolapp.registrationservice.mapper;

import com.exschoolapp.registrationservice.dto.RegistrationRequest;
import com.exschoolapp.registrationservice.dto.RegistrationResponse;
import com.exschoolapp.registrationservice.model.Registration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface RegistrationMapper {
    Registration mapToRegistration(RegistrationRequest registrationRequest);
    RegistrationResponse mapToRegistrationResponse(Registration registration);
    void mapUpdateRegistration(RegistrationRequest registrationRequest, @MappingTarget Registration registration);
}
