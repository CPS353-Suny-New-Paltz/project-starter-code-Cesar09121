package project.networkapi;
import java.math.BigInteger;
/*
 * Multi-threaded implementation of UserComputingAPI
 * This class runs factorial calculations in parallel using multiple threads
 */
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import project.conceptualapi.ComputationAPI;
import project.processapi.DataReadRequest;
import project.processapi.DataReadResponse;
import project.processapi.DataStorageAPI;
import project.processapi.DataWriteRequest;
import project.processapi.DataWriteResponse;

public class UserComputingMultiThread implements UserComputingAPI{
	private final DataStorageAPI dataStorageAPI; // Handles reading/writing files
	private final ComputationAPI computationAPI; // Does the factorial math
	private final ExecutorService executor; 	 // Manages the thread pool
	private static final int MAX_THREADS = 4;	// Limit on how many threads we use at the same time
	
	// Sets up the multi-threaded coordinator
	public UserComputingMultiThread(DataStorageAPI dataStorageAPI, ComputationAPI computationAPI) {
		this.computationAPI=computationAPI;
		this.dataStorageAPI=dataStorageAPI;
		// Creates a pool of 4 threads that we can reuse
		this.executor=Executors.newFixedThreadPool(MAX_THREADS);
		
	}
	
	@Override
	public ComputingJobResponse submission(ComputingJobRequest request) {
		// Validate the request is not null
		if(request==null) {
			throw new IllegalArgumentException("Request can't be null!");
		}
		if(request.getInput()==null||request.getOutput()==null) {
			throw new IllegalArgumentException("Input and output can't be null");
		}
		
		try {
			// Read the input numbers from the file
			DataReadResponse readResponse = dataStorageAPI.readInput(new DataReadRequest(request.getInput().getLocation()));
			
			// Returns error response if reading failed
			if(!readResponse.getStatus().isSuccess()) {
				return failureResponse(request);
			}
			// Gets the lists of numbers to calculate factorial
			List<Integer>inputData = readResponse.getData();
			Delimiters delimiters = request.getDelimitersOrDefault();
			
			// Create a task for each number's factorial calculation
			List<Callable<String>> task = new ArrayList<>();
			for(int i =0;i<inputData.size();i++) {
				final int inputValue = inputData.get(i);
				// Creates a task which calculates 1 factorial
				task.add(()->{
					BigInteger factorial = computationAPI.computeFactorial(inputValue);
					// Result format (ex: "3! =6")
					return inputValue +delimiters.getDelimiterResult()+factorial;
				});
			}
			
			// Runs all the tasks in parallel and wait for them to finish
			List<Future<String>> future = executor.invokeAll(task);
			
			// Collect all the results from the threads
			StringBuilder result = new StringBuilder();
			for(int i =0;i<future.size();i++) {
				// Gets result from a thread
				result.append(future.get(i).get());
				if(i<future.size()-1) {
					// Adds delimiter between results (except after the last result)
					result.append(delimiters.getDelimiters());
				}
			}
			// Writes the formatted results to the output file
			DataWriteResponse writeResponse = dataStorageAPI.writeOutput(new DataWriteRequest(
																		request.getOutput().getLocation(), 
																		result.toString()));
			// Checks if the writing's successful
			if(!writeResponse.getStatus().isSuccess()) {
				return failureResponse(request);
			}
			// Returns success response if everything worked
			return new DefaultComputingJobResponse(request.getInput(),
													request.getOutput(),
													delimiters,
													ComputingJobSuccess.SUCCESS);
			
		} catch (Exception e) {
			// Returns failure response if something went wrong
			return failureResponse(request);
		}
	}
	
	// Helper method to create a failure response
	private ComputingJobResponse failureResponse(ComputingJobRequest request) {
		return new DefaultComputingJobResponse(request.getInput(),
												request.getOutput(),
												request.getDelimitersOrDefault(),
												ComputingJobSuccess.FAILED);
	}
	// Shuts down the thread pool when finishing to clean up 
	public void shutdown() {
		executor.shutdown();
	}
	
	
}
