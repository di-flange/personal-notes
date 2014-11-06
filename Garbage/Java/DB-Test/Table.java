import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.ITable;

public class Table {
 
    private final String name;
    private final String query;
    private final IDatabaseConnection connection;
    
    private ITable table;
    
    public Table(final IDatabaseConnection connection, final String name) {
        
        super();
        
        this.name = name;
        this.query = "SELECT * FROM " + this.name + " LIMIT 10000";
        
        this.connection = connection;
    }
    
    public void update() {
        
        this.table = null;
    }
    
    public int getRowsCount() {
        
        return this.getTable().getRowCount();
    }
    
    public Object getValue(final int row, final String column) {
        
        try {
            return this.getTable().getValue(row, column);
        } catch (final Exception exp) {
            throw new DbTestException(exp);
        }
    }
    
    public Object getValue(final RowCondition condition, final String column){
        
        try {
            final int row = this.getRowIndex(condition);
            
            if (row == -1) {
                return null;
            }
            
            return this.getTable().getValue(row, column);
        } catch (final Exception exp) {
            throw new DbTestException(exp);
        }        
    }
    
    public int getRowIndex(final RowCondition condition) {
        
        this.getTable();
        
        try {
            for (int i = 0; i < this.table.getRowCount(); i++) {
                if (condition.getValue().equals(this.table.getValue(i, condition.getColumn()))) {
                    return i;
                }
            }
            
            return -1;
        } catch (DataSetException exp) {
            throw new DbTestException(exp);
        }
    }
    
    private ITable getTable() {
        
        if (this.table == null) {
            this.table = this.loadTable();
        }
        
        return this.table;
    }
    
    private ITable loadTable() {
        
        try {
            // Create dump of selected table
            QueryDataSet partialDataSet = new QueryDataSet(this.connection);
            partialDataSet.addTable(this.name, this.query);
            
            return partialDataSet.getTable(this.name);
        } catch (final Exception exp) {
            throw new DbTestException(exp);
        }
    }
}
