package org.app.portfolio.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
public class ProjectResponseDto {

    Long id;
    String name;
    String description;
    String link;

}