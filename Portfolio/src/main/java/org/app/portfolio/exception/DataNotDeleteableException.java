package org.app.portfolio.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DataNotDeleteableException extends RuntimeException {
    private String message;
}
