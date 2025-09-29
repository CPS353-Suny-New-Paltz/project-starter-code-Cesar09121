import project.networkapi.InputSource;
import java.util.List;

/*
 * Test input source that stores data in memory
 * Used for integration testing instead of reading from actual files
 */

public class TestMemoryInput implements InputSource{
	
	// List of integers for test input
	private final List<Integer> data;
	
	// Creates a test input source with given input
	public TestMemoryInput(List<Integer>data) {
		this.data = data;
	}
	
	// Gets stored input in the memory
	public List<Integer>getData(){
		return data;
	}
	
	// Return a location for in-memory input 
	@Override
	public String getLocation() {
		return "in_memory_input";
	}
	

}
