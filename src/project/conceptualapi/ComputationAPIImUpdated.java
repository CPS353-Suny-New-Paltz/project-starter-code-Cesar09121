package project.conceptualapi;
import java.util.concurrent.ConcurrentHashMap;
/*
 * Updated implementation of ComputationAPI using memoization
 * Uses stored results to store previously calculated factorial 
 * When the same number is requested again, we return the stored result instead of recalculating
 * This makes repeated calculations faster
 */
public class ComputationAPIImUpdated implements ComputationAPI{
	
	private static final ConcurrentHashMap<Integer, Long> storedResults = new ConcurrentHashMap<>();

	// Calculates factorial of a positive integer using stored results when possible
	// The first computation is the slowest, but next computations will be faster by reusing the saved results
	@Override
	public long computeFactorial(int input) {
		// The factorial only works for the positive numbers
		if(input < 0) {
			throw new IllegalArgumentException("Invalid integer!! (must be positive)");
		}
		
		// If this computation is calculated before, return it
		if(storedResults.containsKey(input)) {
            return storedResults.get(input);
        }
		
		// Finds the highest factorial has been calculated (must be smaller than input)
		// Starts from there instead of 1 for reducing calculation steps
		int startFrom = 1;
        long result = 1;      
        for(int i = input - 1; i >= 0; i--) {
            if(storedResults.containsKey(i)) {
                startFrom = i + 1;
                result = storedResults.get(i);
                break;
            }
        }
        
        // Calculates factorial starting from the stored value
        // Saves each step so we can reuse it later
        for(int i = startFrom; i <= input; i++) {
            result *= i;
            storedResults.put(i, result);  // Stores this result for the future computation
        }
        
        return result;

	}
	
}
