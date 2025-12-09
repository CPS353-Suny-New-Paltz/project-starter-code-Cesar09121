package performance;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import org.junit.jupiter.api.Assertions;
import project.conceptualapi.ComputationAPIIm;
import project.conceptualapi.ComputationAPIImUpdated;

/*
 * Integration benchmark test simulating full computation workflow
 * Tests computation API integrated with the coordinator layer
 */
public class IntegrationBenchmarkTest {

    @Test
    public void testIntegratedPerformance() {
        
        int repeat = 100000;
        int range = 20;
        
        // Tests with original implementation integrated into coordinator
        ComputationAPIIm original = new ComputationAPIIm();
        
        long originalStart = System.currentTimeMillis();
        for (int i = 0; i < repeat; i++) {
            for (int j = 1; j <= range; j++) {
                BigInteger result = original.computeFactorial(j);
                Assertions.assertTrue(result.compareTo(BigInteger.ZERO)> 0, "Factorial should be positive");
            }
        }
        long originalTime = System.currentTimeMillis() - originalStart;
        
        // Tests with updated implementation
        ComputationAPIImUpdated updated = new ComputationAPIImUpdated();
        
        long updatedStart = System.currentTimeMillis();
        for (int i = 0; i < repeat; i++) {
            for (int j = 1; j <= range; j++) {
                BigInteger result = updated.computeFactorial(j);
                Assertions.assertTrue(result.compareTo(BigInteger.ZERO)> 0, "Factorial should be positive");
            }
        }
        long updatedTime = System.currentTimeMillis() - updatedStart;
        
        // Calculates performance improvement
        double improvement = ((originalTime - updatedTime) / (double) originalTime) * 100.0;
        
        System.out.println("Original: " + originalTime + " ms");
        System.out.println("Updated: " + updatedTime + " ms");
        System.out.println("Improvement: " + improvement + "%");
        
        // Makes sure both produce same results
        for (int i = 1; i <= 20; i++) {
            Assertions.assertEquals(original.computeFactorial(i),
            						updated.computeFactorial(i),
            						"Both implementations should have same result"
            );
        }
        
        // Asserts performance improvement
        Assertions.assertTrue(improvement >= 10.0,
            "Expected at least 10% improvement and we got " + improvement + "% improvement!");
    }
}