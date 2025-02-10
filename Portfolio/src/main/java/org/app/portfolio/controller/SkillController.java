package org.app.portfolio.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.app.portfolio.dto.request.SkillRequestDto;
import org.app.portfolio.dto.response.SkillResponseDto;
import org.app.portfolio.dto.update.SkillUpdateDto;
import org.app.portfolio.service.SkillService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/skill")
@RequiredArgsConstructor
@Tag(name="Skill", description = "Skill API. Contains all operations that can be performed with skill")
public class SkillController {
    private final SkillService skillService;

    @GetMapping("/all")
    public ResponseEntity<List<SkillResponseDto>> getAllSkills() {
        List<SkillResponseDto> skillResponsetDtoList = skillService.getSkills();
        return ResponseEntity.ok().body(skillResponsetDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SkillResponseDto> getSkill(@PathVariable Long id) {
        SkillResponseDto skill = skillService.getSkill(id);
        return ResponseEntity.ok().body(skill);
    }
    @PostMapping()
    public ResponseEntity<Void> addSkill(@RequestBody @Valid SkillRequestDto skillRequestDto) {
        Long id = skillService.addSkill(skillRequestDto);
        return ResponseEntity.created(URI.create("skill/" + id)).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<SkillResponseDto> updateSkill(@PathVariable Long id, @RequestBody @Valid SkillUpdateDto skillUpdateDto) {
        return  ResponseEntity.ok(skillService.updateSkill(id, skillUpdateDto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<SkillResponseDto> deleteSkill(@PathVariable Long id) {
        return  ResponseEntity.ok(skillService.deleteSkill(id));
    }


}