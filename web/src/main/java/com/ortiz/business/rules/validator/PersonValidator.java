package com.ortiz.business.rules.validator;

import br.com.fluentvalidator.AbstractValidator;
import com.ortiz.domain.Person;

import java.util.Objects;
import java.util.Optional;

import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.StringPredicate.stringEmptyOrNull;
import static br.com.fluentvalidator.predicate.StringPredicate.stringSizeBetween;

public abstract class PersonValidator <P extends Person> extends AbstractValidator<P> {

    protected boolean isInsert;

    protected PersonValidator(boolean insert) {
        this.isInsert = insert;
    }

    @Override
    public void rules() {
        setPropertyOnContext(this.getClass().getName());
        if (isInsert) {
            ruleFor((P p)-> Optional.ofNullable(p.getPersonIdentity()).map(personIdentity -> personIdentity.getId()).orElseGet(() -> null))
                    .must(stringEmptyOrNull())
                    .withMessage("Person must not have id in this operation")
                    .critical();
        } else {
            ruleFor( (P p)-> p.getPersonIdentity().getId())
                    .must(not(stringEmptyOrNull()))
                    .withMessage("Person Id is required")
                    .critical();
        }

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

