package com.ortiz.business.rules.validator;

import com.ortiz.business.rules.validator.utils.PredicatesUtils;
import com.ortiz.domain.CorporatePerson;

import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.StringPredicate.stringEmptyOrNull;


public class CorporatePersonValidator extends PersonValidator<CorporatePerson> {

    public CorporatePersonValidator(boolean isInsert) {
        super(isInsert);
    }

    @Override
    public void rules() {
        super.rules();
        setPropertyOnContext(this.getClass().getName());
        ruleFor(CorporatePerson::getCnpj)
                .must(not(stringEmptyOrNull()))
                .withMessage("CNPJ is required")
                .must(PredicatesUtils.isCNPJValid)
                .withMessage("CNPJ is invalid")
                .critical();
    }
}

