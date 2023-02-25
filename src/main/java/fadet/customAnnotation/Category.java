package fadet.customAnnotation;

import fadet.customAnnotation.validator.CategoryValidator;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CategoryValidator.class)
public @interface Category {
    String message() default "저장된 카테고리만 가능";
    Class[] groups() default {};
    Class[] payload() default {};
}
