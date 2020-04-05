package gtes.validator;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


@Documented
@Constraint(validatedBy = SsoValidator.class)
@Target( {ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)

public @interface UniqueSSO {
    String message() default "{UniqueSSO.user.ssoId}";
    Class<?> [] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
