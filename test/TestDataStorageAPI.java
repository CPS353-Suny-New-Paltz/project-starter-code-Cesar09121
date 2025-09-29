import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import project.processapi.*;
/*
 * Unit test for DataStorageAPI implementation
 * Tests reading and writing data operations for file/database storage
 */

public class TestDataStorageAPI {
	
	// The data storage API instance we're testing
	private DataStorageAPI dataStorageAPI;
	
	/*
	 * Sets up the test by creating a new data storage implementation
	 * Runs before each individual test method
	 */
	
	@BeforeEach
	public void Setup() {
		dataStorageAPI = new DataStorageAPIIm();
	}
	
	/*
	 * Tests that we can read input from the storage location
	 */
	
	@Test
	public void testReadInput() throws Exception {
		// Create a request to read data from a test file
		DataReadRequest request = new DataReadRequest("inputData.txt");
		// Try to read the data
		DataReadResponse response = dataStorageAPI.readInput(request);
		
		// Makes sure we get a response back
		assertNotNull(response, "Read response should not be null");
		// Makes sure that empty implementation returns failure
		assertFalse(response.getStatus().isSuccess(),"Empty implementation shouldn't be read" );
	}
	
	/*
	 * Test that we can write output to the storage location
	 */
	@Test
	public void testWriteOutput() throws Exception{
		// Create a request to write data to the file
		DataWriteRequest request = new DataWriteRequest("inputData.txt", "3! = 6");
		// Try to write the data
		DataWriteResponse response = dataStorageAPI.writeOutput(request);
		
		// Makes sure we get response back
		assertNotNull(response, "Write response should not be null");
		// Makes sure that empty implementation return failure
		assertFalse(response.getStatus().isSuccess(),"Empty implementation shouldn't be written");
	}

}
