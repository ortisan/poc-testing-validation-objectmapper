package com.ortiz.business.rules.impl;

import br.com.fluentvalidator.context.ValidationResult;
import com.ortiz.domain.Phone;
import com.ortiz.business.rules.IPhoneRule;
import com.ortiz.business.rules.validator.PhoneInsertValidator;
import com.ortiz.business.rules.validator.utils.ValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class PhoneRuleImpl implements IPhoneRule {

    @Autowired
    private PhoneInsertValidator phoneInsertValidator;

    @Override
    public ValidationResult validate(Collection<Phone> phones) {
        Optional<ValidationResult> validationResult = phones.stream().map(this::validate).reduce((result1, result2) -> ValidatorUtils.combine(result1, result2));
        return validationResult.get();
    }

    @Override
    public ValidationResult validate(Phone phone) {
        return phoneInsertValidator.validate(phone);
    }
}
