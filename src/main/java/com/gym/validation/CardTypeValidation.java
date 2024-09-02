package com.gym.validation;

import com.gym.anno.CardType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CardTypeValidation implements ConstraintValidator<CardType,String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if(value == null){
            return false;
        }
        if(value.equals("月卡") || value.equals("季卡") || value.equals("年卡")){
            return true;
        }
        return false;
    }
}
