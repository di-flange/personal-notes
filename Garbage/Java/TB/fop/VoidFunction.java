package ee.jiss.resourceflow.utils.fop;

public abstract class VoidFunction<I> implements Function<I,Object> {

    public abstract void executeVoid(final I in);
    
    @Override
    public Object execute(final I in) {

        this.executeVoid(in);
        return null;
    }
}