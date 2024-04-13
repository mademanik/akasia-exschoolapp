package com.exschoolapp.extracurricularservice.mapper;

import com.exschoolapp.extracurricularservice.dto.MentorRequest;
import com.exschoolapp.extracurricularservice.dto.MentorResponse;
import com.exschoolapp.extracurricularservice.model.Mentor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface MentorMapper {
    Mentor mapToMentor(MentorRequest mentorRequest);
    MentorResponse mapToMentorResponse(Mentor mentor);
    void mapUpdateMentor(MentorRequest mentorRequest, @MappingTarget Mentor mentor);
}
