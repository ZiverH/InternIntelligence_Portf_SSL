package org.app.portfolio.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ForbiddenException extends RuntimeException {
    private String detail;
}
