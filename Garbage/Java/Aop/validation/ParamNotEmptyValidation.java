import java.lang.annotation.Annotation;
import java.util.Collection;

public class ParamNotEmptyValidation implements AopValidationRule {

    @Override
    public boolean validate(final Object target, final Annotation annotation) {

        if (target == null) {
            return false;
        }

        if (target.getClass().isArray()) {
            return ((Object[]) target).length != 0;
        }
        
        if (Collection.class.isAssignableFrom(target.getClass())) {
            return ! ((Collection) target).isEmpty();
        }
        
        return false;
    }
}
