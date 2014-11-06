import java.lang.annotation.Annotation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingIntercepter {

    private final Logger logger;

    public LoggingIntercepter() {

        super();

        this.logger = LoggerFactory.getLogger(LoggingIntercepter.class);
    }

    public void logEntry(final JoinPoint joinPoint) {

        this.logger.debug("--> " + this.getName(joinPoint));
    }

    public void logExit(final JoinPoint joinPoint) {

        this.logger.debug("<-- " + this.getName(joinPoint));
    }

    public void logAfterThrowsAdvice(final JoinPoint joinPoint, final Throwable throwable) {

        this.logger.debug(joinPoint.getSignature().getName() + " throws "
                + throwable.getClass().getSimpleName());
    }   

    private String getName(final JoinPoint joinPoint) {

        final Object[] args = joinPoint.getArgs();
        final Annotation[][] annotations
                = ((MethodSignature) joinPoint.getSignature())
                .getMethod().getParameterAnnotations();

        final StringBuffer buffer = new StringBuffer()
                .append(joinPoint.getSignature().getName());
        
        for (int i = 0; i < args.length; i++) {
            this.appendArgument(buffer, args[i].getClass(), args[i], annotations[i]);
        }
        
        return buffer.toString();
    }
    
    private StringBuffer appendArgument(final StringBuffer buffer,
                                        final Class<? extends Object> type,
                                        final Object value,
                                        final Annotation[] annotations) {
        
        buffer.append(" [ ").append(type.getSimpleName()).append(": ");
        
        for (final Annotation annotation : annotations) {
            if (annotation.annotationType().equals(HideInLog.class)) {
                return buffer.append(value.hashCode()).append(" ] ");
            }
        }
        
        return buffer.append(value.toString()).append(" ] ");
    }
}
