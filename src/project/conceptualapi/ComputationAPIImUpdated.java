package project.conceptualapi;

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
