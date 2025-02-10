package org.app.portfolio.dto.request;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import org.app.portfolio.model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class ExperienceRequestDto {

    @NotNull
    @Size(min = 2, max = 50, message = "Company name must be between 2 and 50 characters")
    String companyName;
    @NotNull
    LocalDate startDate;
    LocalDate endDate;
    @Size(min = 10, max = 100, message = "Description must be between 2 and 50 characters")
    String description;

    @NotNull
    @Size(min = 2, max = 50, message = "Position name must be between 2 and 50 characters")
    String position;

}
