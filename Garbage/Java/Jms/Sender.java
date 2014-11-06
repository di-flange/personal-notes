import java.io.Serializable;

public interface Sender<T extends Serializable> {

    boolean send(T content);
}
