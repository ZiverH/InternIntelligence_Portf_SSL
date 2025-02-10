package org.app.portfolio.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.app.portfolio.dto.request.ProjectRequestDto;
import org.app.portfolio.dto.response.ProjectResponseDto;
import org.app.portfolio.dto.update.ProjectUpdateDto;
import org.app.portfolio.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
@Tag(name="Project ", description = "Project API. Contains all operations that can be performed with project")

public class ProjectController {
    private final ProjectService ProjectService;

    @GetMapping("/all")
    public ResponseEntity<List<ProjectResponseDto>> getAllProjects() {
        List<ProjectResponseDto> ProjectResponsetDtoList = ProjectService.getProjects();
        return ResponseEntity.ok().body(ProjectResponsetDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponseDto> getProject(@PathVariable Long id) {
        ProjectResponseDto Project = ProjectService.getProject(id);
        return ResponseEntity.ok().body(Project);
    }
    @PostMapping()
    public ResponseEntity<Void> addProject(@RequestBody @Valid ProjectRequestDto ProjectRequestDto) {
        Long id = ProjectService.addProject(ProjectRequestDto);
        return ResponseEntity.created(URI.create("Project/" + id)).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponseDto> updateProject(@PathVariable Long id, @RequestBody @Valid ProjectUpdateDto ProjectUpdateDto) {
        return  ResponseEntity.ok(ProjectService.updateProject(id, ProjectUpdateDto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ProjectResponseDto> deleteProject(@PathVariable Long id) {
        return  ResponseEntity.ok(ProjectService.deleteProject(id));
    }


}