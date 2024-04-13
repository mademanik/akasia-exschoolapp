package com.exschoolapp.extracurricularservice.mapper;

import com.exschoolapp.extracurricularservice.dto.ExtracurricularRequest;
import com.exschoolapp.extracurricularservice.dto.ExtracurricularResponse;
import com.exschoolapp.extracurricularservice.model.Extracurricular;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface ExtracurricularMapper {
    Extracurricular mapToExtracurricular(ExtracurricularRequest extracurricularRequest);
    ExtracurricularResponse mapToExtracurricularResponse(Extracurricular extracurricular);
    void mapUpdateExtracurricular(ExtracurricularRequest extracurricularRequest, @MappingTarget Extracurricular extracurricular);
}
