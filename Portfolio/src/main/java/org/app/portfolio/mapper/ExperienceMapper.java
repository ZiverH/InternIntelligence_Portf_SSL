package org.app.portfolio.mapper;

import org.app.portfolio.dto.request.ExperienceRequestDto;
import org.app.portfolio.dto.response.ExperienceResponseDto;
import org.app.portfolio.dto.update.ExperienceUpdateDto;
import org.app.portfolio.model.Experience;
import org.mapstruct.Mapper;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface ExperienceMapper {

    Experience toEntity(ExperienceRequestDto requestDto);
    ExperienceResponseDto toDto(Experience experience);

    default void updateExperience(Experience experience, ExperienceUpdateDto dto){

        if(dto.getCompanyName() != null){
            experience.setCompanyName(dto.getCompanyName());
        }
        if (dto.getDescription()!=null){
            experience.setDescription(dto.getDescription());
        }
        if (dto.getPosition()!=null){
            experience.setPosition(dto.getPosition());
        }
        if (dto.getStartDate()!=null){
            experience.setStartDate(dto.getStartDate());
        }
        if (dto.getEndDate()!=null){
            experience.setEndDate(dto.getEndDate());
        }
    }
}
