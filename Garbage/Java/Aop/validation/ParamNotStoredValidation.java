import java.lang.annotation.Annotation;

public class ParamNotStoredValidation implements AopValidationRule {

    @Override
    public boolean validate(final Object target, final Annotation annotation) {

        if (target == null || ! Domain.class.isAssignableFrom(target.getClass())) {
            return false;
        }
        
        final Domain domain = (Domain) target;
        
        return domain.getId() == null;
    }
}
