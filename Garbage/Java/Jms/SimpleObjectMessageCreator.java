import java.io.Serializable;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import org.springframework.jms.core.MessageCreator;

public class SimpleObjectMessageCreator implements MessageCreator {

    private final Serializable content;

    public SimpleObjectMessageCreator(final Serializable content) {

        this.content = content;
    }

    @Override
    public Message createMessage(final Session session) throws JMSException {

        return session.createObjectMessage(content);
    }
}
