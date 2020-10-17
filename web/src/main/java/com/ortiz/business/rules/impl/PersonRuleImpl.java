package com.ortiz.business.rules.impl;


import br.com.fluentvalidator.context.Error;
import br.com.fluentvalidator.context.ValidationResult;
import com.ortiz.business.rules.IPersonRule;
import com.ortiz.business.rules.IPhoneRule;
import com.ortiz.business.rules.validator.CorporatePersonValidator;
import com.ortiz.business.rules.validator.PhysicalPersonValidator;
import com.ortiz.business.rules.validator.utils.ValidatorUtils;
import com.ortiz.domain.CorporatePerson;
import com.ortiz.domain.Person;
import com.ortiz.domain.Phone;
import com.ortiz.domain.PhysicalPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class PersonRuleImpl implements IPersonRule {

    @Autowired
    private IPhoneRule phoneRule;

    @Override
    public ValidationResult validate(Person person, boolean isInsert) {
        ValidationResult result;
        if (person instanceof PhysicalPerson) {
            result = new PhysicalPersonValidator(isInsert).validate((PhysicalPerson) person);
        } else {
            result = new CorporatePersonValidator(isInsert).validate((CorporatePerson) person);
        }
        final List<Phone> phones = person.getPhones();
        if (!CollectionUtils.isEmpty(phones)) {
            ValidationResult phonesValidationResult = phoneRule.validate(phones, isInsert);
            result = ValidatorUtils.combine(result, phonesValidationResult);
        }
        if (!result.isValid()) {
            Collection<Error> errors = result.getErrors();
            String errorsStr = errors.stream().map(Error::toString).collect(Collectors.joining(", "));
            System.out.println("errorsStr = " + errorsStr);
            throw new IllegalArgumentException(errorsStr);
        }
        return null;
    }
}
