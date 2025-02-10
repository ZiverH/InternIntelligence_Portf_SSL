package org.app.portfolio.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;


@Data
@Builder
public class EducationRequestDto {

    @NotNull
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    String institutionName;

    @NotNull
    @Size(min = 2, max = 50, message = "Degree must be between 2 and 50 characters")
    String degree;

    @NotNull
    @Size(min = 2, max = 50, message = "Study must be between 2 and 50 characters")
    String fieldOfStudy;

    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate graduationDate;

}
