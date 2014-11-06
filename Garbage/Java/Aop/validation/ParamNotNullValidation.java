import java.lang.annotation.Annotation;

public class ParamNotNullValidation implements AopValidationRule {

    @Override
    public boolean validate(final Object target, final Annotation annotation) {

        return target != null;
    }
}
