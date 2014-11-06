import java.io.Serializable;

/**
 * Utils for data access classes.
 */
public class DaoUtils {
 
    public static boolean isNew(final Domain domain) {
		
        return domain.getId() == null;
	}
	
    public static int getInt(final Object object) {
        
        if (object == null || object.getClass() != Integer.class) {
            return 0;
        }
        
        return (Integer) object;
    }
    
    public static long getLong(final Object object) {

        if (object == null || object.getClass() != Long.class) {
            return 0;
        }
        
        return (Long) object;
    }
    
	public static Long getId(final Serializable id) {
		
        return id == null ? null : new Long(id.toString());
	}
}
