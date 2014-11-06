package ee.jiss.resourceflow.utils.exception.reaction;

public class PactToIaeReaction<T> implements Reaction<T> {

    private final String message;
    
    public PactToIaeReaction() {
        
        this.message = null;
    }

    public PactToIaeReaction(final String message) {

        this.message = message;
    }

    @Override
    public T execute(final Exception in) {

        if (this.message == null) {
            throw new IllegalArgumentException(in);
        }

        throw new IllegalArgumentException(this.message, in);
    }
}