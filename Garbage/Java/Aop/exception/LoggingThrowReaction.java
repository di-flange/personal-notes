import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingThrowReaction implements ThrowReaction {

    private final Logger logger;

    public LoggingThrowReaction() {

        super();

        this.logger = LoggerFactory.getLogger(LoggingThrowReaction.class);
    }
    
    @Override
    public void reaction(final Throwable problem) {
       
        this.logger.warn("Exception throwen " + problem);
    }
}
