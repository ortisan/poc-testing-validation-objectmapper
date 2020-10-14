package com.ortiz.business.rules.validator;

import com.ortiz.business.rules.validator.utils.PredicatesUtils;
import com.ortiz.domain.PhysicalPerson;

import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.StringPredicate.stringEmptyOrNull;


public class PhysicalPersonValidator extends PersonValidator<PhysicalPerson> {

    public PhysicalPersonValidator(boolean insert) {
        super(insert);
    }

    @Override
    public void rules() {
        super.rules();
        setPropertyOnContext(this.getClass().getName());
        ruleFor(PhysicalPerson::getCpf)
                .must(not(stringEmptyOrNull()))
                .withMessage("CPF is required")
                .must(PredicatesUtils.isCPFValid)
                .withMessage("CPF is invalid")
                .critical();

    }


}

