import java.io.Serializable;

public interface Listener {

    ListenResult listen(Serializable target) throws Exception;
    
    void post(Serializable target, ListenResult result);
}
