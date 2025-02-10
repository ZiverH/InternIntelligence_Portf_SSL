package org.app.portfolio.mapper;

import org.app.portfolio.dto.request.EducationRequestDto;
import org.app.portfolio.dto.response.EducationResponseDto;
import org.app.portfolio.dto.update.EducationUpdateDto;
import org.app.portfolio.model.Education;
import org.app.portfolio.model.enums.Level;
import org.mapstruct.Mapper;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface EducationMapper {

    Education toEntity(EducationRequestDto requestDto);
    EducationResponseDto toDto(Education education);

    default void updateEducation(Education education, EducationUpdateDto dto){

        if(dto.getInstitutionName() != null){
            education.setInstitutionName(dto.getInstitutionName());
        }
        if (dto.getDegree()!=null){
            education.setDegree(dto.getDegree());
        }
        if (dto.getFieldOfStudy()!=null){
            education.setFieldOfStudy(dto.getFieldOfStudy());
        }
        if (dto.getGraduationDate()!=null){
            education.setGraduationDate(dto.getGraduationDate());
        }
        if (dto.getStartDate()!=null){
            education.setStartDate(dto.getStartDate());
        }

    }
}
