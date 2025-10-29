import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import project.processapi.DataStorageAPI;
import project.processapi.DataWriteRequest;
import project.processapi.DataStorageAPIIm;
import project.processapi.DataReadRequest;
import project.processapi.DataWriteResponse;
import project.processapi.DataReadResponse;

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
	public void setUp() {
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
		// Checks the file and should return failure when it doesn't exist
		assertFalse(response.getStatus().isSuccess(),"Should fail when file doesn't exist" );
	}
	
	/*
	 * Test that we can write output to the storage location
	 */
	@Test
	public void testWriteOutput() throws Exception{
		// Create a request to write data to the file
		DataWriteRequest request = new DataWriteRequest("outputData.txt", "3! = 6");
		// Try to write the data
		DataWriteResponse response = dataStorageAPI.writeOutput(request);
		
		// Makes sure we get response back
		assertNotNull(response, "Write response should not be null");

	}

}
