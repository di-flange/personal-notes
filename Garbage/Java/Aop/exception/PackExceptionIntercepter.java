import java.util.Collections;
import java.util.Map;
import org.aspectj.lang.ProceedingJoinPoint;

public class PackExceptionIntercepter {
  
    private Map<Class<? extends Exception>, Class<? extends Exception>> exceptions;

    public PackExceptionIntercepter(Map<Class<? extends Exception>, Class<? extends Exception>> exceptions) {
     
        this.exceptions = Collections.unmodifiableMap(exceptions);
    }
    
    public Object advice(final ProceedingJoinPoint jointPoint) throws Throwable {
		
        try {
            return jointPoint.proceed();
        } catch (final Exception problem) {
            final Class<? extends Exception> type
                    = this.exceptions.get(problem.getClass());

            if (type == null) {
                throw problem;
            }
            
            try {
                throw type.getConstructor(Throwable.class).newInstance(problem);
            } catch (final NoSuchMethodException notFound) {
                throw type.getConstructor().newInstance();
            }
        }
	}
}
