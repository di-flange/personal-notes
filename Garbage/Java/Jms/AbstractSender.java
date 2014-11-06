import java.io.Serializable;
import javax.jms.Destination;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;

public abstract class AbstractSender<T extends Serializable> implements Sender<T> {
    
    private final JmsTemplate template;

    protected AbstractSender(final JmsTemplate template) {

        super();
        this.template = template;
    }

    protected boolean send(T content, Destination destination) {

        try {
            this.template.send(destination, new SimpleObjectMessageCreator(content));
        } catch (final JmsException exp) {
            return false;
        }

        return true;
    }
}
