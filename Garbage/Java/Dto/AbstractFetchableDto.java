public abstract class AbstractFetchableDto implements FetchableDto {

    private boolean frozen;
	protected final Long id;
	
	protected AbstractFetchableDto(final Long id) {
		
		super();
		
		this.id = id;
        this.frozen = false;
	}
	
	@Override
	public Long getId() {

		return this.id;
	}

    @Override
    public boolean isFrozen() {
        
        return this.frozen;
    }

    @Override
    public void fetch(Object... objects) {
        
        if (this.frozen) {
            throw new IllegalStateException("Attempt to modify dto " + this.id + " when it frozen");
        }
        
        this.fetchAction();
    }
    
    public abstract void fetchAction();
    
    @Override
    public void freeze() {

        this.frozen = true;
    }
    
	@Override
	public int hashCode() {
		
		return this.id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}
		
		if (obj == null) {
			return false;
		}
		
		if (getClass() != obj.getClass()) {
			return false;			
		}
		
		return ((AbstractFetchableDto) obj).id.equals(this.id);
	}
}
