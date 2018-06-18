package com.rabo.validation;

import java.math.BigDecimal;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.commons.validator.routines.BigDecimalValidator;

public class ValidEndBalanceValidator implements ConstraintValidator<ValidateEndBalance, String> {

    private int min;

    @Override
    public void initialize(ValidateEndBalance validateEndBalance) {
        min = validateEndBalance.min();
    }

    @Override
    public boolean isValid(String endBalance, ConstraintValidatorContext context) {

        if (new BigDecimal(endBalance).min(BigDecimal.ZERO).compareTo(new BigDecimal(min)) > 0) {
            return false;
        }

        if (BigDecimalValidator.getInstance().isValid(endBalance)) {
            return false;
        }

        return true;
    }
}
