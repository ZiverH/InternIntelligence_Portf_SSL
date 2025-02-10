package org.app.portfolio.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ConstraintsViolationError {
    private String field;
    private String message;
}
