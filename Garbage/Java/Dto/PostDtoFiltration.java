import java.util.List;
import java.util.Set;

public interface PostDtoFiltration<T extends Object> {

    void filter(final List<T> collection);

    void filter(final Set<T> collection);    
}
