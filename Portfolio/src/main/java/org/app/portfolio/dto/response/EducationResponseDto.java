package org.app.portfolio.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class EducationResponseDto {

    Long id;
    String institutionName;
    String degree;
    String fieldOfStudy;
    LocalDate startDate;
    LocalDate graduationDate;


}

