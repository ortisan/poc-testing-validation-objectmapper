package com.ortiz.business.rules.impl;

import br.com.fluentvalidator.context.ValidationResult;
import com.ortiz.business.rules.IPhoneRule;
import com.ortiz.business.rules.validator.PhoneValidator;
import com.ortiz.business.rules.validator.utils.ValidatorUtils;
import com.ortiz.domain.Phone;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class PhoneRuleImpl implements IPhoneRule {

    @Override
    public ValidationResult validate(Collection<Phone> phones, boolean isInsert) {
        Optional<ValidationResult> validationResult = phones.stream().map((phone -> validate(phone, isInsert))).reduce((result1, result2) -> ValidatorUtils.combine(result1, result2));
        return validationResult.get();
    }

    @Override
    public ValidationResult validate(Phone phone, boolean isInsert) {
        return new PhoneValidator(isInsert).validate(phone);
    }
}
