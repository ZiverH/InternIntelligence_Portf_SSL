package org.app.portfolio.service;

import lombok.RequiredArgsConstructor;
import org.app.portfolio.config.JwtCredentials;
import org.app.portfolio.dto.request.ProjectRequestDto;
import org.app.portfolio.dto.response.ProjectResponseDto;
import org.app.portfolio.dto.update.ProjectUpdateDto;
import org.app.portfolio.exception.ForbiddenException;
import org.app.portfolio.exception.NotFoundException;
import org.app.portfolio.mapper.ProjectMapper;
import org.app.portfolio.model.Project;
import org.app.portfolio.model.User;
import org.app.portfolio.repository.ProjectRepository;
import org.app.portfolio.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final ProjectMapper projectMapper;
    private final JwtCredentials jwtCredentials;



    public Long addProject(ProjectRequestDto projectDto) {
        Project project = projectMapper.toEntity(projectDto);

        User user = userRepository.findById(jwtCredentials.getUserId()).orElseThrow(()->new NotFoundException(User.class.getSimpleName()));
        project.setUser(user);
        projectRepository.save(project);
        return project.getId();
    }

    public ProjectResponseDto deleteProject(Long id) {
        Project project = projectRepository.findById(id).orElseThrow(() -> new NotFoundException(Project.class.getSimpleName()));
        if(!checkUser(project.getUser().getId())) throw new ForbiddenException("You do not have permission to access");

        ProjectResponseDto dto = projectMapper.toDto(project);
        projectRepository.delete(project);
        return dto;
    }

    public ProjectResponseDto updateProject(Long id, ProjectUpdateDto requestDto) {

        Project project = projectRepository.findById(id).orElseThrow(() -> new NotFoundException(Project.class.getSimpleName()));
        if(!checkUser(project.getUser().getId())) throw new ForbiddenException("You do not have permission to access");

        projectMapper.updateProject(project,requestDto);
        projectRepository.save(project);

        return projectMapper.toDto(project);
    }

    public ProjectResponseDto getProject(Long id) {
        Project project = projectRepository.findById(id).orElseThrow(() -> new NotFoundException(Project.class.getSimpleName()));
        if(!checkUser(project.getUser().getId())) throw new ForbiddenException("You do not have permission to access");

        return projectMapper.toDto(project);
    }

    public List<ProjectResponseDto> getProjects() {
        List<Project> Projects = projectRepository.findAllByUser(jwtCredentials.getUserId());
        return Projects.stream().map(projectMapper::toDto).collect(Collectors.toList());
    }


    public boolean checkUser(String userId){
        return userId.equals(jwtCredentials.getUserId());
    }
}
