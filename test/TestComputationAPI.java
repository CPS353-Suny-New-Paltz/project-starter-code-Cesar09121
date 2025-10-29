import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import project.conceptualapi.ComputationAPIIm;
import project.conceptualapi.ComputationAPI;
/*
 * The unit test for Computation API implementation
 * Tests the function of factorial calculation
 */
public class TestComputationAPI {
	
	// The computation API instance we're testing
	private ComputationAPI computationAPI;
	
	/*
	 * Sets up the creating new implementation instance
	 * Runs before each individual test method
	 */
	@BeforeEach
	public void setUp(){
		// Creates a empty implementation for testing 
		computationAPI = new ComputationAPIIm();
	}
	
	/*
	 * Tests the factorial calculation method
	 */
	@Test
	public void testComputeFactorial() throws Exception{
		// Tests the factorial with input 3
		long result = computationAPI.computeFactorial(3);
		
		// This should return the actual correct result now
		assertEquals(6,result, "Calculate actual factorial and the result should be 6");
		
	}
	/*
	 * Tests that in the negative number case, the exception will be thrown
	 */
	@Test
	public void testComputeFactorialNegative() throws Exception {
		assertThrows(IllegalArgumentException.class, () -> {
			computationAPI.computeFactorial(-5);
		}, "Negative number should throw IllegalArgumentException");
	}

}
