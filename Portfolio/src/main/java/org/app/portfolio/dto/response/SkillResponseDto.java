package org.app.portfolio.dto.response;

import lombok.Data;
import org.app.portfolio.model.enums.Level;

@Data
public class SkillResponseDto {

    Long id;
    String name;
    String level;
    String description;
}
