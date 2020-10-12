package com.ortiz.rules.generic;


import br.com.fluentvalidator.context.ValidationResult;

public interface IGenericRule<T> {
    ValidationResult validate(T object);
}
