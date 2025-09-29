import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import project.networkapi.*;
import project.processapi.DataStorageAPI;
/*
 * Unit test for UserComputingAPI implementation
 * Tests the function of job submission from user to computing engine
 */

public class TestUserComputingAPI {
	
	@Mock
	private DataStorageAPI mockDataStorage; // Fake data storage
	
	@Mock 
	private InputSource mockInputSource; // Fake input source
	
	@Mock 
	private OutputSource mockOutputSource;  // Fake output source
	
	// The API implementation we're testing
	private UserComputingAPI userComputingAPI;
	
	
	/*
	 * Sets up the environment for testing with mock objects
	 * Runs before each individual test method
	 */
	@BeforeEach
	public void setUp() {
		// Initialize the mock objects with @Mock
		MockitoAnnotations.openMocks(this);
		// Create the implementation with the fake data storage dependency
		userComputingAPI = new UserComputingAPIIm(mockDataStorage);
	}
	
	/*
	 * Tests function of job submission
	 * Uses mock objects so we don't need real files or databases
	 */
	@Test 
	public void testJobSubmission() throws Exception{
		// Creates a job request using fake input source 
		ComputingJobRequest request = new ComputingJobRequest(mockInputSource,mockOutputSource,null);
		// Submit job and get response
		ComputingJobResponse response = userComputingAPI.submission(request);
		
		// Makes sure we get a response back
		assertNotNull(response, "Response shouldn't be null");
		// Makes sure the empty implementation doesn't claim success
		assertNotEquals(ComputingJobSuccess.SUCCESS, response.getStatus(),"Empty implementation shouldn't be succesful");
	}
	
	/*
	 * Test function for job submission with delimiters formatting
	 * Makes sure the API can handle user's custom output formatting
	 */
	@Test
	public void testJobSubmissionWithDelimiters() throws Exception{
		// Creates a custom delimiters for output formatting
		Delimiters delimiters = new Delimiters("; ", "! = ");
		
		// Creates a job request with the custom delimiters
		ComputingJobRequest request = new ComputingJobRequest(mockInputSource, mockOutputSource, delimiters);
		// Submit the job and get response
		ComputingJobResponse response = userComputingAPI.submission(request);
		
		// Makes sure we get response back
		assertNotNull(response, "Response shouldn't be null");
		// Makes the empty implementation doesn't claim success
		assertNotEquals(ComputingJobSuccess.SUCCESS, response.getStatus(),"Empty implementation shouldn't be succesful");
		
		
	}
	

}
