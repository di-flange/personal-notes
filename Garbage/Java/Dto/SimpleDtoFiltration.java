public class SimpleDtoFiltration implements DtoFiltration {

	protected String alias;	
	protected Object value;
	
	protected SimpleDtoFiltration(final String alias, final Object value) {
		
		super();
		
		this.alias = alias;
		this.value = value;
	}
    
    @Override
    public boolean fiter(final Object[] typle, final String[] aliases) {
        
        final Object typleValue = null;
        
        for (int i = 0; i < aliases.length; i++) {
            if (this.alias.equals(aliases[i])) {
                
                break;
            }
        }
        
        if (typleValue == null) {
            return false;
        }
        
        return this.value.equals(typleValue);
    }
}
