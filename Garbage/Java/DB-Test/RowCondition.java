public class RowCondition {
 
    private final Object value;
    private final String column;
    
    public RowCondition(final Object value, final String column) {
        
        super();
        
        this.value = value;
        this.column = column;
    }

    public Object getValue() {
        
        return this.value;
    }

    public String getColumn() {
     
        return this.column;
    }
}
