import org.aspectj.lang.ProceedingJoinPoint;

public class ExceptionHideInterceptor {
 
    private ThrowReaction throwReaction;
    private Object defaultResult;

    public ExceptionHideInterceptor() {
    
        super();
    }

    public ExceptionHideInterceptor(final ThrowReaction throwReaction, final Object defaultResult) {
    
        this();
        
        this.throwReaction = throwReaction;
        this.defaultResult = defaultResult;
    }
    
    public Object advice(final ProceedingJoinPoint jointPoint) throws Throwable {
		
        try {
            return jointPoint.proceed();
        } catch (final Exception problem) {
            if (this.throwReaction != null) {
                this.throwReaction.reaction(problem);
            }
            
            return this.defaultResult;
        }
	}
}
