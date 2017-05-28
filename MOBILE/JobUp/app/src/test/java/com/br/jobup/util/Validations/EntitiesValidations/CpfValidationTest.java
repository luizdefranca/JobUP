package com.br.jobup.util.Validations.EntitiesValidations;

import org.junit.Test;

import java.util.List;

import br.com.caelum.stella.ValidationMessage;
import br.com.caelum.stella.validation.CPFValidator;

/**
 * Created by luizramos on 09/04/17.
 */
public class CpfValidationTest {
    @Test
    public void isACpfValid() throws Exception {
        String cpf = "793.901.104-91";
        List<ValidationMessage> messages = new CPFValidator().invalidMessagesFor(cpf);
        for (ValidationMessage error : messages) {
            System.out.println(error.getMessage());
        }

        new CPFValidator().assertValid("867.554.707-24");
    }

}