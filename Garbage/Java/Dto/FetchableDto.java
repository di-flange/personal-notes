public interface FetchableDto {

	Long getId();

	void fetch(Object... objects);
    
    void freeze();
    
    boolean isFrozen();
}
