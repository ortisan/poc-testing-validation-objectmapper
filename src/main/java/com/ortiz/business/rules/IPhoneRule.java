package com.ortiz.business.rules;

import br.com.fluentvalidator.context.ValidationResult;
import com.ortiz.domain.Phone;
import com.ortiz.business.rules.generic.IGenericCollectionRule;

import java.util.Collection;

public interface IPhoneRule {
    ValidationResult validate(Collection<Phone> phones, boolean isInsert);
    ValidationResult validate(Phone phone, boolean isInsert);
}
