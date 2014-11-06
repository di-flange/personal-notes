package eu.aiskov.toolbox.collection;

import java.util.HashMap;
import java.util.Map;

/**
 * Map which key is class. Should get object not only by class comparing but also find values by children classes.
 *
 * @param <V> Value type
 */
@SuppressWarnings("UnusedDeclaration")
public class ClassMap<V> extends HashMap<Class<?>, V> {

    /**
     * Cache used for implement faster search by frequently searched keys which not in a list but children classes.
     */
    private final Map<Class<?>, V> cache;

    /**
     * Default init.
     */
    public ClassMap() {
    
        super();

        this.cache = new HashMap<Class<?>, V>();
    }


    public ClassMap(final int initialCapacity, final float loadFactor) {
        
        super(initialCapacity, loadFactor);
        
        this.cache = new HashMap<Class<?>, V>();
    }

    public ClassMap(final Map<? extends Class<?>, ? extends V> m) {
    
        super(m);

        this.cache = new HashMap<Class<?>, V>();
    }

    public ClassMap(final int initialCapacity) {
        
        super(initialCapacity);

        this.cache = new HashMap<Class<?>, V>();
}
    
    @Override
    public V get(final Object key) {

        if (key == null) {
            return null;
        }
        
        V result = super.get(key);
        
        if (result != null) {
            return result;
        }
            
        result = this.cache.get(key);
        
        if (result != null) {
            return result;
        }
        
        for (final Class<?> availableKey : this.keySet()) {
            if (availableKey.isAssignableFrom((Class<?>) key)) {
                result = super.get(availableKey);
                this.cache.put((Class<?>) key, result);

                return result;
            }
        } 
        
        return null;
    }

    @Override
    public V remove(final Object key) {
        
        this.cache.clear();
        return super.remove(key);
    }

    @Override
    public void clear() {
    
        this.cache.clear();
        super.clear();
    }
}