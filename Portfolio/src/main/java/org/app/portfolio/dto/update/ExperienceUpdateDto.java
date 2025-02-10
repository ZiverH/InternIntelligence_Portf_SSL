package org.app.portfolio.dto.update;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ExperienceUpdateDto {

    @Size(min = 2, max = 50, message = "Company name must be between 2 and 50 characters")
    String companyName;
    LocalDate startDate;
    LocalDate endDate;
    @Size(min = 10, max = 100, message = "Description must be between 2 and 50 characters")
    String description;
    @Size(min = 2, max = 50, message = "Position name must be between 2 and 50 characters")
    String position;
}
