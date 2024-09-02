package com.gym.anno;


import com.gym.validation.CardTypeValidation;
import com.gym.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(
        validatedBy = {CardTypeValidation.class}
)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CardType {
    String message() default "只能是月卡、季卡或年卡";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
