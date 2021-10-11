package bookmyconsultation.userservice.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Pattern(regexp = ".+@.+\\..+", message = "Email Id")
@Email(message = "Email Id")
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
public @interface EmailValidator {
    String message() default "Email Id";
    Class<?>[] groups() default {};
    Class<? extends Payload> [] payload() default {};
}
