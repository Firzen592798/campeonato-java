package br.com.firzen.campeoanto.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueNomeValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueNome {
    String message() default "Participante repetido";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}