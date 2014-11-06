public class Table {

    private final String table;
    private final Class<? extends Domain> type;

    public Table(final String table, Class<? extends Domain> type) {

        this.table = table;
        this.type = type;
    }

    public String getTable() {

        return this.table;
    }

    public Class<? extends Domain> getType() {

        return this.type;
    }
}
