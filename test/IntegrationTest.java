import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.ArrayList;
import java.util.List;


import project.conceptualapi.ComputationAPI;
import project.conceptualapi.ComputationAPIIm;
import project.networkapi.UserComputingAPI;
import project.networkapi.ComputingJobRequest;
import project.networkapi.ComputingJobResponse;
import project.networkapi.ComputingJobSuccess;
import project.networkapi.FileInputSource;
import project.networkapi.FileOutputSource;
import project.networkapi.UserComputingAPIIm;

/*
 * Integration test for the completed computing engine system
 * Tests components without using mock objects
 * Uses in-memory test infrastructure instead of actual file
 */

public class IntegrationTest {
	
	private UserComputingAPI userComputingAPI;           // Main API that user interact with
	private ComputationAPI computationAPI;	    // This component does calculations
	private TestDataStorageMemory dataMemory;	// Test-only data storage works with in-memory data
	/*
	 * Sets up test environment with components
	 * Creates implementations connect together without mocks
	 */
	@BeforeEach
	public void setUp() {
		dataMemory = new TestDataStorageMemory();			// Creates test data storage works with in-memory data
		computationAPI = new ComputationAPIIm();  			// Creates a real computationAPI
		userComputingAPI = new UserComputingAPIIm(dataMemory, computationAPI);  // Creates user API with both dependencies
				
	}
	
	/*
	 * Tests the completed workflow from user request to response
	 * Makes sure all components can work together 
	 */
	@Test
	public void testWorkflow() throws Exception{
		// Creates the test input data
		List<Integer> testInput = new ArrayList<>();
		testInput.add(1);
		testInput.add(10);
		testInput.add(25);
		
		// Creates in-memory input and output for testing
		TestMemoryInput input = new TestMemoryInput(testInput);
		TestMemoryOutput output = new TestMemoryOutput();
		
		// Creates a job request with no delimiters
		ComputingJobRequest request = new ComputingJobRequest(input, output, null);
		// Submit the job through the system
		ComputingJobResponse response = userComputingAPI.submission(request);
		// Makes sure we get a response 
		assertNotNull(response, "Response shouldn't be null");
		// Checks that the job is successful
		assertEquals(ComputingJobSuccess.SUCCESS,response.getStatus(), "Successfully with valid input!!");
		
		// Verifies the response contains correct input/output references
		assertNotNull(response.getInput(), "Response should contain input source");
		assertNotNull(response.getOutput(), "Response should contain output source");
		assertEquals(input, response.getInput(), "Response should reference the same input");
		assertEquals(output, response.getOutput(), "Response should reference the same output");
	}
	
	@Test
	public void testExceptionHandling() {
	    // Creates a request that will cause an exception
	    ComputingJobRequest badRequest = new ComputingJobRequest( 
	    		new FileInputSource("input.txt"),
	    		new FileOutputSource("output.txt"),
	            null
	    );
	    
	    // Shouldn't throw exception and returns FAILED status instead
	    ComputingJobResponse response = userComputingAPI.submission(badRequest);
	    
	    assertEquals(ComputingJobSuccess.FAILED, response.getStatus(),"Job should fail with non-existent input file");
	}
	
	
}
