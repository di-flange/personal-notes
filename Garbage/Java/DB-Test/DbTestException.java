public class DbTestException extends RuntimeException {
    
    private static final String DEFAULT_MESSAGE = "Problem with data base test util."; 
    
    public DbTestException() {
        
        super(DEFAULT_MESSAGE);
    }
    
    public DbTestException(final Throwable exp) {
        
        super(DEFAULT_MESSAGE, exp);
    }
    
    public DbTestException(final String message) {
        
        super(message);
    }
    
    public DbTestException(final String message, final Throwable exp) {
        
        super(message, exp);
    }
}
