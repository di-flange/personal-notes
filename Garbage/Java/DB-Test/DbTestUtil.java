import java.io.File;
import java.net.MalformedURLException;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.hsqldb.HsqldbDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.cfg.Configuration;

/**
 * Helper for testing data access layer.
 */
public class DbTestUtil {

    private static final String PARAM_CON_URL_PROPERTY = "hibernate.connection.url";
    
    public final SessionManager sessionManager;
    private final String connectionUrl;
    
    private File dump;
    
	public DbTestUtil(final String file) {
        
        final Configuration configuration = new Configuration().configure(file);
        
        this.sessionManager = new SimpleSessionManager(configuration.buildSessionFactory());
        this.connectionUrl = configuration.getProperty(PARAM_CON_URL_PROPERTY);        
	}
    
	public void setFixture(final String dump) {

        try {
            if (dump == null || ! (this.dump = new File(dump)).canRead()) {
                throw new DbTestException("Fixture can not be set because fixture file doesn't exist");
            }
            
            this.loadFixture();
        } catch (final SQLException exp) {
            throw new DbTestException("Fixture can not be set:", exp);
        } catch (final MalformedURLException exp) {
            throw new DbTestException("Fixture can not be set:", exp);
        } catch (final DatabaseUnitException exp) {
            throw new DbTestException("Fixture can not be set:", exp);
        }          
	}

    public void resetFixture() {

        try {
            if (this.dump == null || ! this.dump.canRead()) {
                throw new DbTestException("Fixture can not be reset because fixture file doesn't exist");
            }
            
            this.loadFixture();
        } catch (final SQLException exp) {
            throw new DbTestException("Fixture can not be reset:", exp);
        } catch (final MalformedURLException exp) {
            throw new DbTestException("Fixture can not be reset:", exp);
        } catch (final DatabaseUnitException exp) {
            throw new DbTestException("Fixture can not be reset:", exp);
        }                
    }

    
    public Table getTable(final String table) {

        return new Table(this.getConnection(), table); 
	}
 
    private void loadFixture() throws DatabaseUnitException, SQLException, MalformedURLException {
        
        // Connect to database
        IDatabaseConnection connection = this.getConnection();
        connection.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new HsqldbDataTypeFactory());

        // Insert data set to table
        DatabaseOperation.CLEAN_INSERT.execute(connection, this.getFixture());
    }
    
	private IDataSet getFixture() throws MalformedURLException, DataSetException {

        final FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
        builder.setColumnSensing(true);

        return builder.build(this.dump);
	}
        
    private IDatabaseConnection getConnection() {
        
        try {
            return new DatabaseConnection(DriverManager.getConnection(this.connectionUrl));
        } catch (final Exception exp) {
            throw new DbTestException(exp);
        }
    }
}
