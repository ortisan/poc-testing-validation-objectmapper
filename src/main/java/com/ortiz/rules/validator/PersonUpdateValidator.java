package com.ortiz.rules.validator;

import br.com.fluentvalidator.AbstractValidator;
import com.ortiz.domain.Person;

import java.util.Objects;

import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.StringPredicate.stringEmptyOrNull;
import static br.com.fluentvalidator.predicate.StringPredicate.stringSizeBetween;

public abstract class PersonUpdateValidator<P extends Person> extends AbstractValidator<P> {

    @Override
    public void rules() {
        setPropertyOnContext(this.getClass().getName());

        ruleFor("Id person", (P p)-> p.getPersonIdentity().getId())
                .must(not(stringEmptyOrNull()))
                .withMessage("Person Id is required")
                .critical();

        ruleFor(Person::getTenantId)
                .must(not(stringEmptyOrNull()))
                .withMessage("Tenant Id is required")
                .critical();

        ruleFor(Person::getFullName)
                .must(not(stringEmptyOrNull()))
                .withMessage("Name is required")
                .must(stringSizeBetween(3, 120))
                .withMessage("Name must be lengh between 3 and 120 chars")
                .critical();

        ruleFor(Person::getPersonType)
                .must(not(Objects::isNull))
                .withMessage("Person type is required")
                .critical();
    }
}

