package org.app.portfolio.dto.update;


import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import org.app.portfolio.annotaion.ValidEnum;
import org.app.portfolio.model.enums.Level;

@Data
@Builder
public class SkillUpdateDto {

    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    String name;

    @ValidEnum(enumClass = Level.class, message = "Level must be one of:  BEGINNER,INTERMEDIATE,EXPERT")
    String level;

    @Size(min=10,max = 200, message = "Description must be between 10 and 200 characters")
    String description;

}
