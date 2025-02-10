package org.app.portfolio.annotaion;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

@Constraint(validatedBy = EnumValidator.class)
@Target({ FIELD, METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidEnum {

    Class<? extends Enum<?>> enumClass();

    String message() default "Value must be a valid enum constant";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
