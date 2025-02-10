package org.app.portfolio.service;

import lombok.RequiredArgsConstructor;
import org.app.portfolio.config.JwtCredentials;
import org.app.portfolio.dto.request.ExperienceRequestDto;
import org.app.portfolio.dto.response.ExperienceResponseDto;
import org.app.portfolio.dto.update.ExperienceUpdateDto;
import org.app.portfolio.exception.ForbiddenException;
import org.app.portfolio.exception.NotFoundException;
import org.app.portfolio.mapper.ExperienceMapper;
import org.app.portfolio.model.Experience;
import org.app.portfolio.model.User;
import org.app.portfolio.repository.ExperienceRepository;
import org.app.portfolio.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExperienceService {

    private final ExperienceRepository experienceRepository;
    private final UserRepository userRepository;
    private final JwtCredentials jwtCredentials;
    private final ExperienceMapper experienceMapper;


    public Long addExperience(ExperienceRequestDto experienceDto) {

        if(experienceDto.getEndDate()!=null && !checkDate(experienceDto.getStartDate(), experienceDto.getEndDate())){
            throw new IllegalArgumentException("Start date must be before end date!");
        }
        Experience experience = experienceMapper.toEntity(experienceDto);

        User user = userRepository.findById(jwtCredentials.getUserId()).orElseThrow(()->new NotFoundException(User.class.getSimpleName()));
        experience.setUser(user);

        experienceRepository.save(experience);
        return experience.getId();
    }

    public ExperienceResponseDto deleteExperience(Long id) {
        Experience experience = experienceRepository.findById(id).orElseThrow(() -> new NotFoundException(Experience.class.getSimpleName()));

        if(!checkUser(experience.getUser().getId())) throw new ForbiddenException("You do not have permission to access");

        ExperienceResponseDto dto = experienceMapper.toDto(experience);
        experienceRepository.delete(experience);
        return dto;
    }

    public ExperienceResponseDto updateExperience(Long id, ExperienceUpdateDto requestDto) {

        Experience experience = experienceRepository.findById(id).orElseThrow(() -> new NotFoundException(Experience.class.getSimpleName()));


        if((requestDto.getEndDate()!=null && requestDto.getStartDate()!=null
                && !checkDate(requestDto.getStartDate(),requestDto.getEndDate()))
         || (requestDto.getEndDate()!=null && !checkDate(experience.getStartDate(),requestDto.getEndDate()))
                || (requestDto.getStartDate()!=null && !checkDate(requestDto.getStartDate(),experience.getEndDate()))
        ){
            throw new IllegalArgumentException("Start date must be before end date!");
        }

        if(!checkUser(experience.getUser().getId())) throw new ForbiddenException("You do not have permission to access");

        experienceMapper.updateExperience(experience,requestDto);
        experienceRepository.save(experience);

        return experienceMapper.toDto(experience);
    }

    public ExperienceResponseDto getExperience(Long id) {
        Experience experience = experienceRepository.findById(id).orElseThrow(() -> new NotFoundException(Experience.class.getSimpleName()));
        if(!checkUser(experience.getUser().getId())) throw new ForbiddenException("You do not have permission to access");

        return experienceMapper.toDto(experience);
    }

    public List<ExperienceResponseDto> getExperiences() {
        List<Experience> experiences = experienceRepository.findAllByUser(jwtCredentials.getUserId());
        return experiences.stream().map(experienceMapper::toDto).collect(Collectors.toList());
    }

    public boolean checkDate(LocalDate startDate, LocalDate endDate) {

        return startDate.isBefore(endDate);
    }


    public boolean checkUser(String userId){
        return userId.equals(jwtCredentials.getUserId());
    }
}
