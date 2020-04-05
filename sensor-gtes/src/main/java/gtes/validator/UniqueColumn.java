package gtes.validator;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

//    @Documented
//    @Constraint(validatedBy = NumberSensorValidator.class)
//    @Target( { ElementType.METHOD, ElementType.FIELD })
//    @Retention(RetentionPolicy.RUNTIME)
//
//    public @interface UniqueColumn {
//        String message() default "Номер существует";
//        Class<?> [] groups() default {};
//        Class<? extends Payload>[] payload() default {};
//
//}
@Documented
@Constraint(validatedBy = NumberSensorValidator.class)
@Target( {ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)

public @interface UniqueColumn {
    String message() default "Номер существует";
    Class<?> [] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
