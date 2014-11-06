import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;

public class Field {

    private final Table table;

    private final String field;
    private final String column;

    public Field(final Table table, final String field, final String column) {

        this.table = table;

        this.field = field;
        this.column = column;
    }

    public String getField() {

        return this.field;
    }

    public String getColumn() {

        return this.column;
    }

    protected void addAnnotation() throws NotFoundException {

        final ClassPool pool = ClassPool.getDefault();
        CtClass clazz = pool.get(this.table.getType().getName());
        ClassPool subPool = clazz.getClassPool();

        .getField(this.field).add
    }
}
