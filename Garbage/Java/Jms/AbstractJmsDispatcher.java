import java.io.Serializable;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import org.springframework.jms.listener.SessionAwareMessageListener;

public abstract class AbstractJmsDispatcher
        implements SessionAwareMessageListener<ObjectMessage> {

    protected void commit(final Serializable content, final Session session)
            throws JMSException {
        
        session.commit();
    }

    protected void rollback(final Serializable content, final Session session)
            throws JMSException {
        
        session.rollback();
    }
    
}
