package com.ortiz.rules.impl;


import br.com.fluentvalidator.context.Error;
import br.com.fluentvalidator.context.ValidationResult;
import com.ortiz.domain.CorporatePerson;
import com.ortiz.domain.Person;
import com.ortiz.domain.Phone;
import com.ortiz.domain.PhysicalPerson;
import com.ortiz.rules.IPersonUpdateRule;
import com.ortiz.rules.IPhoneRule;
import com.ortiz.rules.validator.CorporatePersonUpdateValidator;
import com.ortiz.rules.validator.PhysicalPersonUpdateValidator;
import com.ortiz.rules.validator.utils.ValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Validated
@Service
public class PersonUpdateRuleImpl implements IPersonUpdateRule {

    @Autowired
    private PhysicalPersonUpdateValidator physicalPersonUpdateValidator;

    @Autowired
    private CorporatePersonUpdateValidator corporatePersonUpdateValidator;

    @Autowired
    private IPhoneRule phoneRule;

    @Override
    public ValidationResult validate(Person person) {
        ValidationResult result;
        if (person instanceof PhysicalPerson) {
            result = physicalPersonUpdateValidator.validate((PhysicalPerson) person);
        } else {
            result = corporatePersonUpdateValidator.validate((CorporatePerson) person);
        }
        final List<Phone> phones = person.getPhones();
        if (!CollectionUtils.isEmpty(phones)) {
            ValidationResult phonesValidationResult = phoneRule.validate(phones);
            result = ValidatorUtils.combine(result, phonesValidationResult);
        }
        if (!result.isValid()) {
            Collection<Error> errors = result.getErrors();
            String errorsStr = errors.stream().map(Error::toString).collect(Collectors.joining(", "));
            throw new IllegalArgumentException(errorsStr);
        }
        return null;
    }
}
