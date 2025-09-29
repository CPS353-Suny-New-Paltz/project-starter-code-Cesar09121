import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import project.conceptualapi.*;
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
		
		// Makes sure that we won't get the default value 0
		// This should fail until having real logic for factorial calculation
		assertNotEquals(0,result, "Calculate actual factorial, not default");
		
	}
		

}
