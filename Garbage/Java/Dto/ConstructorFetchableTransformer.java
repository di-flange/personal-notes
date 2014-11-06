import com.google.common.collect.Lists;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.transform.ResultTransformer;

public class ConstructorFetchableTransformer<T extends FetchableDto> implements ResultTransformer {

	private static final long serialVersionUID = 2072849463472108732L;

	private static final String DEFAULT_ID_ALIAS = "id";
	private static final int DEFAULT_ID_LOCATION = -1;
	private static final int DEFAULT_RESULT_LIST_SIZE = 10;

	private final Set<T> results;
	private final Set<AbstractPostDtoFilter<? extends Object>> filters;

	private int idLocation;
	private final String idAlias;

	private final Constructor<T> constructor;
	private boolean isSessionEnded;

	public ConstructorFetchableTransformer(final Class<T> type) {

		super();

		this.filters = new HashSet<AbstractPostDtoFilter<?>>();
		this.results = new HashSet<T>(DEFAULT_RESULT_LIST_SIZE);

		this.idLocation = DEFAULT_ID_LOCATION;
		this.isSessionEnded = false;

		this.constructor = this.getConstructor(type);
		this.idAlias = DEFAULT_ID_ALIAS;
	}

	public ConstructorFetchableTransformer(final Class<T> type, final String idAlias) {

		super();

		this.filters = new HashSet<AbstractPostDtoFilter<?>>();
		this.results = new HashSet<T>(DEFAULT_RESULT_LIST_SIZE);

		this.idLocation = DEFAULT_ID_LOCATION;
		this.isSessionEnded = false;

		this.constructor = this.getConstructor(type);
		this.idAlias = idAlias;
	}

	public void addFilter(final AbstractPostDtoFilter<?> filter) {

		this.filters.add(filter);
	}

	@Override
	public Object transformTuple(final Object[] tuple, final String[] aliases) {

		if (this.isSessionEnded) {
			throw new IllegalStateException("Transformation session ended");
		}

		if (this.idLocation == DEFAULT_ID_LOCATION) {
			this.idLocation = this.findAliasLocation(aliases);
		}

        this.checkTypes(tuple);

		try {
			for (final T row : this.results) {
				if (row.getId().equals(tuple[this.idLocation])) {
					row.fetch(tuple);
					return row;
				}
			}

            final T newInstance = this.constructor.newInstance(tuple);
            this.results.add(newInstance);

			return newInstance;
		} catch (Exception e) {
			throw new IllegalStateException("Problems with tuple transformation", e);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List transformList(final List collection) {

		this.finalizeTransformationSession();

		final List<T> result = Lists.newArrayList(new LinkedHashSet(collection));

		try {
			for (final T row : result) {
				this.runFilter(row);
			}
		} catch (final Exception e) {
			throw new IllegalStateException("Problems with list transformation", e);
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	private Constructor<T> getConstructor(final Class<T> type)  {

		this.idLocation = -1;

		for (final Constructor<?> construct : type.getConstructors()) {
			if (construct.getAnnotation(TransformationConstructor.class) != null) {
				return (Constructor<T>) construct;
			}
		}

		throw new IllegalStateException("Type does not contain annotated constructor for this transformer");
	}

	private void runFilter(final T row)
			throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {

		final Method[] methods = row.getClass().getMethods();

		for (final Method method : methods) {
			final PostDtoFilter annotation = method.getAnnotation(PostDtoFilter.class);

			if (annotation == null) {
				continue;
			}

			final AbstractPostDtoFilter<?> filter = this.getFilter(annotation.filter());

			if (filter != null) {
				method.invoke(row, filter);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private <F extends AbstractPostDtoFilter<?>> F getFilter(final Class<F> type) {

		for (final AbstractPostDtoFilter<?> filter : this.filters) {
			if (filter.getClass().equals(type)) {
				return (F) filter;
			}
		}

		return null;
	}

	private int findAliasLocation(final String[] aliases) {

		for (int i = 0; i < aliases.length; i++) {

			if (this.idAlias.equalsIgnoreCase(aliases[i])) {
				return i;
			}
		}

		throw new IllegalStateException("Id alias not found");
	}

	/**
	 * Finalize transformation session
	 */
	private void finalizeTransformationSession() {

		this.isSessionEnded = true;
		this.results.clear();
	}

    /**
     * Check types of tuple and constructor
     *
     * Just debug method can be commented out
     *
     * @param tuple Row tuple
     */
    private void checkTypes(final Object[] tuple) {

        final Class<?>[] type = this.constructor.getParameterTypes();

        if (tuple.length != type.length) {
            throw new IllegalStateException("Wrong count of arguments");
        }

        for (int i = 0; i < tuple.length; i++) {
            if (tuple[i] != null && ! tuple[i].getClass().equals(type[i])) {
                throw new IllegalStateException("Wrong type of argument at index - " + i + ". It is "
                        + tuple[i].getClass().getName() + " but excepted " + type[i].getName());
            }
        }
    }
}
