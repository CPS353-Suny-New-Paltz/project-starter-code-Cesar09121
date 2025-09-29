import project.networkapi.OutputSource;
import java.util.List;
import java.util.ArrayList;

/*
 * Test output destination that stores results in memory
 * Used for integration testing instead of writing to actual files
 */
public class TestMemoryOutput implements OutputSource{
	
	// List of results that are written during testing
	private final List<String> results= new ArrayList<>();
	
	// Adds result to the in-memory location
	public void addResult(String result) {
		results.add(result);
	}
	
	// Gets the stored results
	public List<String>getResult(){
		return new ArrayList<>(results);
	}
	
	// Clears the stored results 
	public void deleteResult() {
		results.clear();
	}
	
	// Returns a location for the in-memory destination
	@Override
	public String getLocation() {
		return "in_memory_output";
	}
}
