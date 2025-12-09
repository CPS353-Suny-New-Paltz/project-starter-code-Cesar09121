package project.conceptualapi;

import java.math.BigInteger;

/*
 * Implementation of ComputingAPI for factorial calculation
 * Handles the computation of factorial for the positive integers
 */
public class ComputationAPIIm implements ComputationAPI{

		/*
		 * Calculates factorial of a given positive integer
		 */
	    @Override
	    public BigInteger computeFactorial(int input) {
	    	// Does not take negative integer
	        if(input< 0) {
	        	throw new IllegalArgumentException("Invalid integer!! (must be positive)");
	        }
	        // 0! =1 and 1! = 1
	        if(input ==0 || input ==1) {
	        	return BigInteger.ONE;
	        }
	        // Calculates factorial
	        BigInteger result = BigInteger.ONE;
	        for(int i =2;i<=input;i++) {
	        	result = result.multiply(BigInteger.valueOf(i));
	        }
	        return result;
	    }
	    

}
