# Software Engineering Project
This program computes the factorial of a given integer.

### Description 
Given a positive integer n, the program runs the product of all positive integers less than or equal to n.

## System Diagram

![System Diagram](https://github.com/CPS353-Suny-New-Paltz/project-starter-code-Cesar09121/blob/Checkpoint2-demo/images/Factorial_Computing.jpg)

## Multi-Threading
I chose 4 as the maximum number of threads to run multiple factorial calculations at the same time.

## Integration Benchmark Test

### Results

- Original: 45ms
- Updated: 10ms
- Improvement: 77.78%

### Updated result after changing return type from long to BigInteger

- Original: 1051ms
- Updated: 7ms
- Improvement: 99.33%

### Link to Benchmark Test

[`test/performance/IntegrationBenchmarkTest.java`](test/performance/IntegrationBenchmarkTest.java)

### The Issue With the Original Computation

The original computation recalculated factorial from scratch every time even for the repeated input.

### The Fix

Implemented caching using a static array for factorial from 0 to 50. When the computation is requested, the result is instantly retrieved from the cache instead of recalculation.

### Link to PR for the Fix

`https://github.com/CPS353-Suny-New-Paltz/project-starter-code-Cesar09121/pull/54`


### Extended input flexibility

Added more input options to make the system easier to use and save more time

- CSV file support
- Range notation (Ex: type "1-10" or "1-10,13-20" instead of typing all of the needed numbers)


	