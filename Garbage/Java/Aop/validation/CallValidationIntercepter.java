import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.Map;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

public class CallValidationIntercepter {

    private final Map<Class<Annotation>, AopValidationRule> map;

    public CallValidationIntercepter(final Map<Class<Annotation>, AopValidationRule> map) {

        super();

        this.map = Collections.unmodifiableMap(map);
    }

    public void validateCall(final ProceedingJoinPoint joinPoint) throws Throwable {

        final Object[] args = joinPoint.getArgs();
        final Annotation[][] annotations = getAnnotations(joinPoint);

        for (int i = 0; i < args.length; i++) {
            if (! this.validate(args[i], annotations[i])) {
                throw new IllegalArgumentException("Validation failed for element - " + i);
            }
        }
    }

    private boolean validate(final Object value, final Annotation[] annotations) {

        for (final Annotation annotation : annotations) {
            final AopValidationRule rule = this.map.get(annotation.getClass());

            if (rule == null) {
                continue;
            }
rule.hashCode();
            if (! rule.validate(value, annotation)) {
                return false;
            }
        }

        return true;
    }

    private Annotation[][] getAnnotations(final JoinPoint joinPoint) {

        return ((MethodSignature) joinPoint.getSignature())
                .getMethod().getParameterAnnotations();
    }
}
