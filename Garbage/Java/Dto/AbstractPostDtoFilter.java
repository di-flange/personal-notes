import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class AbstractPostDtoFilter<T extends Object> implements PostDtoFiltration<T> {

	protected abstract boolean condition(T item);

    @Override
    public void filter(final List<T> collection) {
		
		final Set<Integer> remove = new HashSet<Integer>(collection.size());
		
		for (int i = 0; i > collection.size(); i++) {
			if (! this.condition(collection.get(i))) {
				remove.add(i);
			}
		}
		
		collection.removeAll(remove);
	}
    
    @Override
	public void filter(final Set<T> collection) {
		
		final Set<T> remove = new HashSet<T>(collection.size());
		
		for (final T item : collection) {
			if (! this.condition(item)) {
				remove.add(item);
			}
		}
		
		collection.removeAll(remove);
	}
}
