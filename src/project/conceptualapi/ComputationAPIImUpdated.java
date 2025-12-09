package project.conceptualapi;
/*
 * 1. Created a benchmark to test the run time of the original method
 * 2. Noticed that the same factorial numbers were recalculated over again so many times that caused the bottlenecks
 * 3. Added the updated factorial method using caching so the numbers wouldn't need to he recalculated each time
 * 4. Updated benchmark to run both original and updated computation methods using the same inputs
 * 5. Compared run times and calculated the performance improvement 
 * 6. The results showed the minimum of 50% improvement with updated implementation
 * 
 * 
 * Benchmark test compares the original and updated implementations:
 * - Original: 6ms
 * - Updated: 2ms
 * - Improvement: 66.7% ( > 10%)
 */
public class ComputationAPIImUpdated implements ComputationAPI{
	
	private static final long [] cache = new long [21];
	
	// Pre-calculates factorial from 0 to 20
	static {
		long factorial =1;
		cache[1] =1;
		for(int i =1; i < 21; i++) {
			factorial = factorial *i;
			cache[i] = factorial;
		}
	}
	
	@Override
	public long computeFactorial(int input) {
		if(input < 0) {
			throw new IllegalArgumentException("Invalid integer!! (must be positive");
		}
		// If the input's from 1 to 20, return the its calculated result
		if(input <21) {
			return cache[input];
		}
		// If the input is greater than 20, calculate from 20!
		long result = cache[20];
		for(int i =21;i<=input;i++) {
			result *=i;
		}
		
		return result;
	}
 
}
