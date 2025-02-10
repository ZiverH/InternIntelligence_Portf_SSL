package org.app.portfolio.service;

import lombok.RequiredArgsConstructor;
import org.app.portfolio.config.JwtCredentials;
import org.app.portfolio.dto.request.SkillRequestDto;
import org.app.portfolio.dto.response.SkillResponseDto;
import org.app.portfolio.dto.update.SkillUpdateDto;
import org.app.portfolio.exception.ForbiddenException;
import org.app.portfolio.exception.NotFoundException;
import org.app.portfolio.mapper.SkillMapper;
import org.app.portfolio.model.Skill;
import org.app.portfolio.model.User;
import org.app.portfolio.model.enums.Level;
import org.app.portfolio.repository.SkillRepository;
import org.app.portfolio.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SkillService {

    private final SkillRepository skillRepository;
    private final UserRepository userRepository;
    private final SkillMapper skillMapper;
    private final JwtCredentials jwtCredentials;


    public Long addSkill(SkillRequestDto skillDto) {

        Skill skill = skillMapper.toEntity(skillDto);
        skill.setLevel(Level.valueOf(skillDto.getLevel().toUpperCase()));

        User user = userRepository.findById(jwtCredentials.getUserId()).orElseThrow(()->new NotFoundException(User.class.getSimpleName()));
        skill.setUser(user);

        skillRepository.save(skill);
        return skill.getId();
    }

    public SkillResponseDto deleteSkill(Long id) {

        Skill skill = skillRepository.findById(id).orElseThrow(() -> new NotFoundException(Skill.class.getSimpleName()));

        if(!checkUser(skill.getUser().getId())) throw new ForbiddenException("You do not have permission to access");

        SkillResponseDto dto = skillMapper.toDto(skill);
        skillRepository.delete(skill);
        return dto;
    }

    public SkillResponseDto updateSkill(Long id, SkillUpdateDto requestDto) {

        Skill skill = skillRepository.findById(id).orElseThrow(() -> new NotFoundException(Skill.class.getSimpleName()));
        if(!checkUser(skill.getUser().getId())) throw new ForbiddenException("You do not have permission to access");

        skillMapper.updateSkill(skill,requestDto);
        skillRepository.save(skill);

        SkillResponseDto dto = skillMapper.toDto(skill);
        dto.setLevel(skill.getLevel().toString());

        return dto;
    }

    public SkillResponseDto getSkill(Long id) {

        Skill skill = skillRepository.findById(id).orElseThrow(() -> new NotFoundException(Skill.class.getSimpleName()));
        if(!checkUser(skill.getUser().getId())) throw new ForbiddenException("You do not have permission to access");

        return skillMapper.toDto(skill);
    }

    public List<SkillResponseDto> getSkills() {
        List<Skill> skills = skillRepository.findAllByUser(jwtCredentials.getUserId());
        return skills.stream().map(skillMapper::toDto).collect(Collectors.toList());
    }

    public boolean checkUser(String userId){
        return userId.equals(jwtCredentials.getUserId());
    }
}
