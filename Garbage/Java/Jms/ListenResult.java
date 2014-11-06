public class ListenResult {

    private final JmsTransactionStatus status;

    private final Object object;

    public ListenResult(final JmsTransactionStatus status, final Object object) {

        super();

        this.status = status;
        this.object = object;
    }

    public Object getObject() {

        return object;
    }

    public JmsTransactionStatus getStatus() {

        return status;
    }
}
