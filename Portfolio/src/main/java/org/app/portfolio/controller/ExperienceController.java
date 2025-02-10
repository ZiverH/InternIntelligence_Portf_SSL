package org.app.portfolio.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.app.portfolio.dto.request.ExperienceRequestDto;
import org.app.portfolio.dto.response.ExperienceResponseDto;
import org.app.portfolio.dto.update.ExperienceUpdateDto;
import org.app.portfolio.service.ExperienceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/experience")
@RequiredArgsConstructor
@Tag(name="Experience", description = "Experience API. Contains all operations that can be performed with experience")
public class ExperienceController {
    private final ExperienceService experienceService;

    @GetMapping("/all")
    public ResponseEntity<List<ExperienceResponseDto>> getAllExperiences() {
        List<ExperienceResponseDto> ExperienceResponsetDtoList = experienceService.getExperiences();
        return ResponseEntity.ok().body(ExperienceResponsetDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExperienceResponseDto> getExperience(@PathVariable Long id) {
        ExperienceResponseDto Experience = experienceService.getExperience(id);
        return ResponseEntity.ok().body(Experience);
    }
    @PostMapping()
    public ResponseEntity<Void> addExperience(@RequestBody @Valid ExperienceRequestDto experienceRequestDto) {
        Long id = experienceService.addExperience(experienceRequestDto);
        return ResponseEntity.created(URI.create("experience/" + id)).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExperienceResponseDto> updateExperience(@PathVariable Long id, @RequestBody @Valid ExperienceUpdateDto experienceUpdateDto) {
        return  ResponseEntity.ok(experienceService.updateExperience(id, experienceUpdateDto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ExperienceResponseDto> deleteExperience(@PathVariable Long id) {
        return  ResponseEntity.ok(experienceService.deleteExperience(id));
    }


}