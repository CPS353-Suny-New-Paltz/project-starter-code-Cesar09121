import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigInteger;

import project.conceptualapi.ComputationAPIIm;
import project.networkapi.UserComputingAPI;
import project.networkapi.UserComputingAPIIm;
import project.processapi.DataStorageAPI;
import project.processapi.DataStorageAPIIm;
import project.conceptualapi.ComputationAPI;
/*
 * The unit test for Computation API implementation
 * Tests the function of factorial calculation
 */
public class TestComputationAPI {
	
	// The computation API instance we're testing
	private ComputationAPI computationAPI;
	private UserComputingAPI userComputingAPI;
	
	/*
	 * Sets up the creating new implementation instance
	 * Runs before each individual test method
	 */
	@BeforeEach
	public void setUp(){
		// Creates a empty implementation for testing 
		computationAPI = new ComputationAPIIm();
		// Creates userComputingAPI with the real dependencies for validation test
		DataStorageAPI dataStorageAPI = new DataStorageAPIIm();
		userComputingAPI = new UserComputingAPIIm(dataStorageAPI,computationAPI);
	}
	
	/*
	 * Tests the factorial calculation method
	 */
	@Test
	public void testComputeFactorial() throws Exception{
		// Tests the factorial with input 3
		BigInteger result = computationAPI.computeFactorial(3);

		assertEquals(BigInteger.valueOf(6),result, "Calculate actual factorial and the result should be 6");
		
	}
	/*
	 * Tests that in the negative number case, the exception will be thrown
	 */
	@Test
	public void testComputeFactorialNegative() throws Exception {
		assertThrows(IllegalArgumentException.class, () -> {
			computationAPI.computeFactorial(-5);
		}, "Throws exception if the number is negative");
	}
	
	@Test
	public void testNullRequestValidation() {
	    assertThrows(IllegalArgumentException.class, () -> {
	        userComputingAPI.submission(null);
	    }, "Throws exception if request is null");
	}

}
