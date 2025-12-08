package performance;

import org.junit.jupiter.api.Test;

import project.conceptualapi.ComputationAPIIm;

/*
 * Creates an initial measurement to identify performance bottlenecks 
 * Measures the original ComputationAPIIm implementation
 */
public class BenchmarkTest {
	@Test
	public void originalMeasurement() {
		ComputationAPIIm original = new ComputationAPIIm();
		
		int repeat = 100000;
		int range = 20;
		
		// Measures how long the implementation takes
		long start = System.currentTimeMillis();
		for(int i= 0;i < repeat; i++) {
			for(int j =1; j <= range; j++) {
				original.computeFactorial(j);
			}
		}
		long runTime = System.currentTimeMillis() - start;
		
		System.out.println("Total calculation: " +(repeat * range));
		System.out.println("Run time: " +runTime +" ms");
	}
}
