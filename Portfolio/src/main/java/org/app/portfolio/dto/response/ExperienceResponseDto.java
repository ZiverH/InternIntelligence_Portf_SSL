package org.app.portfolio.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ExperienceResponseDto {

    Long id;
    String companyName;
    LocalDate startDate;
    LocalDate endDate;
    String description;
    String position;

}
