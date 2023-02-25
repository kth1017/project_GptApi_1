package fadet.customAnnotation.validator;

import fadet.P3_GptApi.domain.recomQ.RecomQ;
import fadet.customAnnotation.Category;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class CategoryValidator implements ConstraintValidator<Category, String> {


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        RecomQ recomQ = new RecomQ();
        if(value == null){
           return false;
       }
       return Arrays.asList(recomQ.getLData()).contains(value);
    }
}
