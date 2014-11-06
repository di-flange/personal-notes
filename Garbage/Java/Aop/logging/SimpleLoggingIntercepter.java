import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleLoggingIntercepter {

    private final Logger logger;

    public SimpleLoggingIntercepter() {

        super();

        this.logger = LoggerFactory.getLogger(SimpleLoggingIntercepter.class);
    }

    public void logEntry(final JoinPoint joinPoint) {

        final StringBuffer buffer = new StringBuffer("--> ")
                .append(joinPoint.getSignature().getName());

        for (final Object arg : joinPoint.getArgs()) {
            buffer.append(" [ ").append(arg.getClass().getSimpleName())
                    .append(": ").append(arg.toString()).append(" ] ");
        }

        this.logger.debug(buffer.toString());
    }

    public void logExit(final JoinPoint joinPoint) {

        final StringBuffer buffer = new StringBuffer("<-- ")
                .append(joinPoint.getSignature().getName());

        for (final Object arg : joinPoint.getArgs()) {
            buffer.append(" [ ").append(arg.getClass().getSimpleName())
                    .append(": ").append(arg.toString()).append(" ] ");
        }

        this.logger.debug(buffer.toString());
    }

    public void logAfterThrowsAdvice(final JoinPoint joinPoint, final Throwable throwable) {

        this.logger.debug(joinPoint.getSignature().getName() + " throws "
                    + throwable.getClass().getSimpleName());
    }   
}
