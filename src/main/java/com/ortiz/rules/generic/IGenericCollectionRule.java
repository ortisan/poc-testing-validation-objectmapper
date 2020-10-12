package com.ortiz.rules.generic;

import br.com.fluentvalidator.context.ValidationResult;

import java.util.Collection;

public interface IGenericCollectionRule<T> extends IGenericRule<T> {
    ValidationResult validate(Collection<T> object);
}
