import java.lang.annotation.Annotation;

public interface AopValidationRule {
 
    boolean validate(Object target, Annotation annotation);
}
