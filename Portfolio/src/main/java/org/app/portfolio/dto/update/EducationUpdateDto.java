package org.app.portfolio.dto.update;


import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class EducationUpdateDto {


    @Size(min = 2, max = 100, message = "Name must be between 2 and 50 characters")
    String institutionName;

    @Size(min = 2, max = 50, message = "Degree must be between 2 and 50 characters")
    String degree;

    @Size(min = 2, max = 50, message = "Study must be between 2 and 50 characters")
    String fieldOfStudy;

    private LocalDate startDate;
    private LocalDate graduationDate;

}
