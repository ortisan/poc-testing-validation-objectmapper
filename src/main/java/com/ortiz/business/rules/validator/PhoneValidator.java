package com.ortiz.business.rules.validator;

import br.com.fluentvalidator.AbstractValidator;
import com.ortiz.domain.Phone;

import static br.com.fluentvalidator.predicate.ComparablePredicate.between;
import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.ObjectPredicate.nullValue;
import static br.com.fluentvalidator.predicate.StringPredicate.stringEmptyOrNull;

public class PhoneValidator extends AbstractValidator<Phone> {

    private boolean isInsert;

    public PhoneValidator(boolean isInsert) {
        this.isInsert = isInsert;
    }

    @Override
    public void rules() {
        setPropertyOnContext(this.getClass().getName());

        if (isInsert) {
            ruleFor(Phone::getId)
                    .must(stringEmptyOrNull())
                    .withMessage("Phone Id must be empty")
                    .critical();
        } else {
            ruleFor(Phone::getId)
                    .must(not(stringEmptyOrNull()))
                    .withMessage("Phone Id is required")
                    .critical();
        }

        ruleFor(Phone::getDdi)
                .must(not(nullValue()))
                .withMessage("DDI is required")
                .must(between(1, 999))
                .withMessage("DDI invalid")
                .critical();

        ruleFor(Phone::getDdd)
                .must(not(nullValue()))
                .withMessage("DDD is required")
                .must(between(1, 999))
                .withMessage("DDD invalid")
                .critical();

        ruleFor(Phone::getNumber)
                .must(not(nullValue()))
                .withMessage("Phone number is required")
                .critical();
    }
}

