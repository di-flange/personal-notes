import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Management of hibernate sessions.
 */
public class HibernateSessionManager implements SessionManager {

    private final ThreadLocal<Session> sessions;
	private final SessionFactory factory;

	/**
	 * Default constructor
	 */
	public HibernateSessionManager(final SessionFactory factory) {
        
		super();
        
        this.sessions = new ThreadLocal<Session>();
        this.factory = factory;
	}

	@Override
	public Session getSession() {

        Session session = (Session) this.sessions.get(); 
        
        if (session == null) {
            this.factory.openSession();
            this.sessions.set(session);
        }
        
		return session;
	}
}
