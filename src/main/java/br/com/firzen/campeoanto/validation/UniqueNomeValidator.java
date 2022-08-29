package br.com.firzen.campeoanto.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.firzen.campeoanto.repository.ParticipanteRepository;

public class UniqueNomeValidator implements ConstraintValidator<UniqueNome, String> {

	@Autowired
	ParticipanteRepository participanteRepository;
		
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return true;
	}
}
