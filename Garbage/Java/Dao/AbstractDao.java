import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.DistinctRootEntityResultTransformer;

public class AbstractDao<T extends Domain> implements Dao<T>{

    protected final Class<T> type;
    
    protected SessionManager sessionManager;

    protected AbstractDao(final Class<T> type) {

        super();
        
        this.type = type;
    }

	@Override
    public T save(final T target) {
        
        if (DaoUtils.isNew(target)) {
			target.setId(DaoUtils.getId(this.sessionManager.getSession().save(target)));		
		} else {
			this.sessionManager.getSession().update(target);
		}
		
        return target;
    }

	@Override
    public T delete(final T target) {
        
        this.sessionManager.getSession().delete(target);
        target.setId(null);
        
		return target;
    }
	
    @Override
    public T merge(final T target) {
        
        return (T) this.sessionManager.getSession().merge(target);
    }

	@Override
    public T refresh(final T target) {
        
        this.sessionManager.getSession().refresh(target);
        return target;
    }

	@Override
    public T get(final Long id) {
        
        return (T) this.sessionManager.getSession().get(this.type, id);
    }
	
    @Override
    public List<T> getAll() {
        
        final Criteria criteria = this.getCriteria();
        return criteria.setResultTransformer(DistinctRootEntityResultTransformer.INSTANCE).list();
    }

    @Override
    public List<T> getInterval(final int start, final int count) {
        
        final Criteria criteria = this.getCriteria();
        criteria.setFirstResult(start).setMaxResults(count);
        
        return criteria.list();
    }
		
    @Override
    public long count() {
        
        final Criteria criteria = this.getCriteria();
		criteria.setProjection(Projections.rowCount());
        
        return DaoUtils.getLong(criteria.uniqueResult());
    }
	
    protected Criteria getCriteria() {
        
        return this.sessionManager.getSession().createCriteria(this.type);
    }
}
