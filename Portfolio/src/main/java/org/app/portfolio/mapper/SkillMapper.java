package org.app.portfolio.mapper;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.app.portfolio.dto.request.SkillRequestDto;
import org.app.portfolio.dto.response.SkillResponseDto;
import org.app.portfolio.dto.update.SkillUpdateDto;
import org.app.portfolio.model.Skill;
import org.app.portfolio.model.enums.Level;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface SkillMapper {

    @Mapping(target = "level", ignore = true)
    Skill toEntity(SkillRequestDto requestDto);

    SkillResponseDto toDto(Skill skill);


    default void updateSkill(Skill skill, SkillUpdateDto dto) {

        if(dto.getDescription() != null){
            skill.setDescription(dto.getDescription());
        }
        if (dto.getName()!=null){
            skill.setName(dto.getName());
        }
        if(dto.getLevel()!=null){
            skill.setLevel(Level.valueOf(dto.getLevel().toUpperCase()));
        }

    }
}
