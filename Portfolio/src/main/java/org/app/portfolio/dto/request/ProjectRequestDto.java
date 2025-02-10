package org.app.portfolio.dto.request;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import org.app.portfolio.model.User;

@Data
@Builder
public class ProjectRequestDto {

    @NotNull
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    String name;

    @Size(min=10,max = 200, message = "Description must be between 10 and 200 characters")
    private String description;

    @NotNull
    String link;

}
