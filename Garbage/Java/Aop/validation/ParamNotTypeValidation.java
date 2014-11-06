import java.lang.annotation.Annotation;

public class ParamNotTypeValidation implements AopValidationRule {

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
        
        if (types == null || types.length == 0) {
            return true;
        }
        
        for (Class<? extends Object> type : types) {
            if (type.isAssignableFrom(target.getClass())) {
                return false;
            }
        }
                
        return true;
    }
}
