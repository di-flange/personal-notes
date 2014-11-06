import java.util.List;

/**
 * Interface of data access layer classes.
 */
public interface Dao<T extends Domain> {

    T save(T target);

    T delete(T target);
	
    T merge(T target);
            
    T refresh(T target);
            
    T get(Long id);
	
    List<T> getAll();
            
    List<T> getInterval(int start, int count);
		
    long count();
}
