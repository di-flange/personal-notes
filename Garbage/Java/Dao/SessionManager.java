import org.hibernate.Session;

/**
 * Interface for hibernate session management.
 */
public interface SessionManager {
    
    /**
     * Get session
     *
     * @return Hibernate session
     */
    Session getSession();
}
