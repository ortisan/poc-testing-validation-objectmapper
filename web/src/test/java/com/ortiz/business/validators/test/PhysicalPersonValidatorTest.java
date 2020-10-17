package com.ortiz.business.validators.test;

import br.com.fluentvalidator.context.ValidationResult;
import com.ortiz.business.rules.validator.PhysicalPersonValidator;
import com.ortiz.domain.DocumentTypeEnum;
import com.ortiz.domain.PersonIdentity;
import com.ortiz.domain.PhysicalPerson;
import com.ortiz.domain.enums.PersonTypeEnum;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class PhysicalPersonValidatorTest {

    @Test
    public void testValidatorInsertOk() {
        PhysicalPerson person = new PhysicalPerson();
        person.setTenantId("a96f3526-0daf-11eb-adc1-0242ac120002");
        person.setFullName("Marcelo Ortiz de Santana");
        person.setPersonType(PersonTypeEnum.PHISICAL);
        person.setCpf("18685964008");
        PersonIdentity personIdentity = new PersonIdentity();
        personIdentity.setCountryCode("BR");
        personIdentity.setDocumentNumber("18685964008");
        personIdentity.setDocumentType(DocumentTypeEnum.CPF);
        person.setPersonIdentity(personIdentity);
        ValidationResult validate = new PhysicalPersonValidator(true).validate(person);
        assertTrue(validate.isValid());
    }

    @Test
    public void testValidatorInsertError() {
        PhysicalPerson person = new PhysicalPerson();
        ValidationResult validate = new PhysicalPersonValidator(true).validate(person);
        assertFalse(validate.isValid());
    }

}
