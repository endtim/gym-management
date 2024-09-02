package com.gym.validation;

import com.gym.anno.EStatus;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EStatusValidation implements ConstraintValidator<EStatus,String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if(value == null){
            return false;
        }
        if(value.equals("正常") || value.equals("维修") || value.equals("已损坏")){
            return true;
        }
        return false;
    }
}
