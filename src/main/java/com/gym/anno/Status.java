package com.gym.anno;

import com.gym.validation.StateValidation;
import com.gym.validation.StatusValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(
        validatedBy = {StatusValidation.class}
)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Status {
    String message() default "status的值只能是有效或无效";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
