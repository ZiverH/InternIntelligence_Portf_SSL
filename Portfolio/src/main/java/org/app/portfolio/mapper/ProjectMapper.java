package org.app.portfolio.mapper;

import org.app.portfolio.dto.request.ProjectRequestDto;
import org.app.portfolio.dto.response.ProjectResponseDto;
import org.app.portfolio.dto.update.ProjectUpdateDto;
import org.app.portfolio.model.Project;
import org.app.portfolio.model.enums.Level;
import org.mapstruct.Mapper;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface ProjectMapper {

    Project toEntity(ProjectRequestDto requestDto);
    ProjectResponseDto toDto(Project project);

    default void updateProject(Project project, ProjectUpdateDto dto) {

        if(dto.getDescription() != null){
            project.setDescription(dto.getDescription());
        }
        if (dto.getName()!=null){
            project.setName(dto.getName());
        }
        if(dto.getLink()!=null){
            project.setLink(dto.getLink());
        }

    }
}
