import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class SimpleSessionManager implements SessionManager {

    private final SessionFactory sessionFactory;
    private final ThreadLocal<Session> sessions;
    
    public SimpleSessionManager(final SessionFactory factory) {
    
        super();
        
        this.sessionFactory = factory;
        this.sessions = new ThreadLocal<Session>();
    }
    
    @Override
    public Session getSession() {

        Session session = this.sessions.get();
        
        if (session == null || ! session.isOpen()) {
            session = this.sessionFactory.openSession();
            this.sessions.set(session);
        }
        
        return session;
    }
}
