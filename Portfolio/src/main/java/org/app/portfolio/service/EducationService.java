package org.app.portfolio.service;

import lombok.RequiredArgsConstructor;
import org.app.portfolio.config.JwtCredentials;
import org.app.portfolio.dto.request.EducationRequestDto;
import org.app.portfolio.dto.response.EducationResponseDto;
import org.app.portfolio.dto.update.EducationUpdateDto;
import org.app.portfolio.exception.ForbiddenException;
import org.app.portfolio.exception.NotFoundException;
import org.app.portfolio.mapper.EducationMapper;
import org.app.portfolio.model.Education;
import org.app.portfolio.model.User;
import org.app.portfolio.repository.EducationRepository;
import org.app.portfolio.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EducationService {

    private final EducationRepository educationRepository;
    private final UserRepository userRepository;
    private final JwtCredentials jwtCredentials;
    private final EducationMapper educationMapper;


    public Long addEducation(EducationRequestDto educationDto) {

        if(!checkDate(educationDto.getStartDate(),educationDto.getGraduationDate())){
            throw new IllegalArgumentException("Start date must be before graduation date!");
        }
        Education education = educationMapper.toEntity(educationDto);
        User user = userRepository.findById(jwtCredentials.getUserId()).orElseThrow(() -> new NotFoundException(User.class.getSimpleName()));
        education.setUser(user);
        educationRepository.save(education);
        return education.getId();
    }

    public EducationResponseDto deleteEducation(Long id) {
        Education education = educationRepository.findById(id).orElseThrow(() -> new NotFoundException(Education.class.getSimpleName()));
        if(!checkUser(education.getUser().getId())) throw new ForbiddenException("You do not have permission to access");

        EducationResponseDto dto = educationMapper.toDto(education);
        educationRepository.delete(education);
        return dto;
    }

    public EducationResponseDto updateEducation(Long id, EducationUpdateDto requestDto) {

        Education education = educationRepository.findById(id).orElseThrow(() -> new NotFoundException(Education.class.getSimpleName()));

        if((requestDto.getGraduationDate()!=null && requestDto.getStartDate()!=null
                && !checkDate(requestDto.getStartDate(),requestDto.getGraduationDate()))
                || (requestDto.getGraduationDate()!=null && !checkDate(education.getStartDate(),requestDto.getGraduationDate()))
                || (requestDto.getStartDate()!=null && !checkDate(requestDto.getStartDate(),education.getGraduationDate()))
        ){
            throw new IllegalArgumentException("Start date must be before end date!");
        }

        if(!checkUser(education.getUser().getId())) throw new ForbiddenException("You do not have permission to access");

        educationMapper.updateEducation(education,requestDto);
        educationRepository.save(education);

        return educationMapper.toDto(education);
    }

    public EducationResponseDto getEducation(Long id) {
        Education education = educationRepository.findById(id).orElseThrow(() -> new NotFoundException(Education.class.getSimpleName()));

        if(!checkUser(education.getUser().getId())) throw new ForbiddenException("You do not have permission to access");

        return educationMapper.toDto(education);
    }

    public List<EducationResponseDto> getEducations() {
        List<Education> Educations = educationRepository.findAllByUser(jwtCredentials.getUserId());
        return Educations.stream().map(educationMapper::toDto).collect(Collectors.toList());
    }

    public boolean checkDate(LocalDate startDate, LocalDate endDate) {
        return startDate.isBefore(endDate);
    }

    public boolean checkUser(String userId){
        return userId.equals(jwtCredentials.getUserId());
    }
}
