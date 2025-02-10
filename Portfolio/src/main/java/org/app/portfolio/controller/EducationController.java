package org.app.portfolio.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.app.portfolio.dto.request.EducationRequestDto;
import org.app.portfolio.dto.response.EducationResponseDto;
import org.app.portfolio.dto.update.EducationUpdateDto;
import org.app.portfolio.service.EducationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/education")
@RequiredArgsConstructor
@Tag(name="Education", description = "Education API. Contains all operations that can be performed with education")
public class EducationController {
    private final EducationService educationService;

    @GetMapping("/all")
    public ResponseEntity<List<EducationResponseDto>> getAllEducations() {
        List<EducationResponseDto> educationResponsetDtoList = educationService.getEducations();
        return ResponseEntity.ok().body(educationResponsetDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EducationResponseDto> getEducation(@PathVariable Long id) {
        EducationResponseDto Education = educationService.getEducation(id);
        return ResponseEntity.ok().body(Education);
    }
    @PostMapping()
    public ResponseEntity<Void> addEducation(@RequestBody @Valid EducationRequestDto EducationRequestDto) {
        Long id = educationService.addEducation(EducationRequestDto);
        return ResponseEntity.created(URI.create("Education/" + id)).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EducationResponseDto> updateEducation(@PathVariable Long id, @RequestBody @Valid EducationUpdateDto EducationUpdateDto) {
        return  ResponseEntity.ok(educationService.updateEducation(id, EducationUpdateDto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<EducationResponseDto> deleteEducation(@PathVariable Long id) {
        return  ResponseEntity.ok(educationService.deleteEducation(id));
    }


}