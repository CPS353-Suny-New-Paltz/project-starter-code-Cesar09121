package performance;

import org.junit.jupiter.api.Test;
import project.conceptualapi.ComputationAPIIm;
import project.conceptualapi.ComputationAPIImUpdated;

/*
 * Creates benchmarktest to compare original and updated factorial computation
 * Runs the implementations and measures the performance improvement
 */
public class BenchmarkTest {
	@Test
	public void improvementMeasurement() {
		ComputationAPIIm original = new ComputationAPIIm();
		ComputationAPIImUpdated updated = new ComputationAPIImUpdated();
		
		int repeat = 100000;
		int range = 20;
		
		// Measures how long the original implementation takes
		long originalStart = System.currentTimeMillis();
		for(int i= 0;i < repeat; i++) {
			for(int j =1; j <= range; j++) {
				original.computeFactorial(j);
			}
		}
		long originalTime = System.currentTimeMillis() - originalStart;
		
		
		// Measures how long the updated implementation takes
		long updatedStart = System.currentTimeMillis();
		for(int i= 0;i < repeat; i++) {
			for(int j =1; j <= range; j++) {
				updated.computeFactorial(j);
			}
		}
		long updatedTime = System.currentTimeMillis() - updatedStart;
		
		// Calculates the performance improvement
		double improvement = ((originalTime - updatedTime) / (double) originalTime) *100.0;
		
		System.out.println("Original: " +originalTime + " ms");
		System.out.println("Updated: "+ updatedTime + " ms");
		System.out.println("Improvement: " +improvement + "%");
	}
}
