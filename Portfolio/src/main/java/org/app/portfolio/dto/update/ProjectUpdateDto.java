package org.app.portfolio.dto.update;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectUpdateDto {

    @NotNull
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @Size(min=10,max = 200, message = "Description must be between 10 and 200 characters")
    private String description;

    private String link;


}
