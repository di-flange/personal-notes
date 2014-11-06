package ee.aiskov.aimtoolbox.collection;

import java.util.HashMap;
import java.util.Map;

public class ClassMap<V extends Object> extends HashMap<Class<? extends Object>, V> {

    private final Map<Class<? extends Object>, V> cache;
    
    public ClassMap() {
    
        super();

        this.cache = new HashMap<Class<? extends Object>, V>();
    }

    public ClassMap(final int initialCapacity, final float loadFactor) {
        
        super(initialCapacity, loadFactor);
        
        this.cache = new HashMap<Class<? extends Object>, V>();
    }

    public ClassMap(final Map<? extends Class<? extends Object>, ? extends V> m) {
    
        super(m);

        this.cache = new HashMap<Class<? extends Object>, V>();
    }

    public ClassMap(final int initialCapacity) {
        
        super(initialCapacity);

        this.cache = new HashMap<Class<? extends Object>, V>();
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
        
        for (final Class<? extends Object> availableKey : this.keySet()) {            
            if (availableKey.isAssignableFrom((Class<? extends Object>) key)) {
                result = super.get(availableKey);
                this.cache.put((Class<? extends Object>) key, result);

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