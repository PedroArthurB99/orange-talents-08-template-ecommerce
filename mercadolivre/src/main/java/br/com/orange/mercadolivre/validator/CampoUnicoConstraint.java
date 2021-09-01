package br.com.orange.mercadolivre.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CampoUnicoValidator.class)
public @interface CampoUnicoConstraint {

    String message() default "Esse campo já está cadastrado no sistema!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String campo();
    Class<?> modelClass();
}
