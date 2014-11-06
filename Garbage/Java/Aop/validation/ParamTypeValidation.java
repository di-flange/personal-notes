import java.lang.annotation.Annotation;

public class ParamTypeValidation implements AopValidationRule {

    @Override
    public boolean validate(final Object target, final Annotation annotation) {

        if (annotation == null) {
            return false;
        }
        
        if (target == null) {
            return true;
        }
        
        final ParamType typeAnnotation = (ParamType) annotation;
        final Class<? extends Object>[] types = typeAnnotation.type();    
        
        for (Class<? extends Object> type : types) {
            if (type.isAssignableFrom(target.getClass())) {
                return true;
            }
        }
                
        return false;
    }
}
