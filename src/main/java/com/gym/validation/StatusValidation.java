package com.gym.validation;

import com.gym.anno.Status;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StatusValidation implements ConstraintValidator<Status,String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if(value == null){
            return false;
        }
        if(value.equals("有效") || value.equals("无效")){
            return true;
        }
        return false;
    }
}
