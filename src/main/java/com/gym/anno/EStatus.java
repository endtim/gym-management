package com.gym.anno;


import com.gym.validation.EStatusValidation;
import com.gym.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(
        validatedBy = {EStatusValidation.class}
)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EStatus {
    String message() default "status的值只能是正常、维修或已损坏";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
