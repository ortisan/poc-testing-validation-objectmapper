package com.ortiz.business.rules.test;

import br.com.fluentvalidator.context.ValidationResult;
import com.ortiz.business.rules.IPersonRule;
import com.ortiz.domain.DocumentTypeEnum;
import com.ortiz.domain.Person;
import com.ortiz.domain.PersonIdentity;
import com.ortiz.domain.PhysicalPerson;
import com.ortiz.domain.enums.PersonTypeEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
public class PersonRuleTest {

    @Autowired
    private IPersonRule personInsertRule;

    @Test
    public void testValidateInsert() {
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
        personInsertRule.validate(person, true);
    }

    @Test
    public void testValidateInsertError() {
        Person person = new PhysicalPerson();
        assertThrows(IllegalArgumentException.class, () -> {
            ValidationResult validate = personInsertRule.validate(person, true);
        });
    }
}
