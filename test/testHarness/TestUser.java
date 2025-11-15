package testHarness;
/*
 * Shows a single user submitting job to the compute engine
 * Used for testing multiple users at once
 */
import java.io.File;
import java.io.FileInputStream;

import project.networkapi.ComputingJobRequest;
import project.networkapi.ComputingJobResponse;
import project.networkapi.Delimiters;
import project.networkapi.FileInputSource;
import project.networkapi.FileOutputSource;
import project.networkapi.UserComputingAPI;


public class TestUser {
	
	// TODO 3: change the type of this variable to the name you're using for your
	// @NetworkAPI interface; also update the parameter passed to the constructor
	private final UserComputingAPI coordinator;

	public TestUser(UserComputingAPI coordinator) {
		this.coordinator = coordinator;
	}

	public void run(String outputPath) {
		char delimiter = ';';
		String inputPath = "test" + File.separatorChar + "testInputFile.test";
		
		// TODO 4: Call the appropriate method(s) on the coordinator to get it to 
		// run the compute job specified by inputPath, outputPath, and delimiter
		
		// Creates input and output sources
		FileInputSource inputSource = new FileInputSource(inputPath);
		FileOutputSource outputSource = new FileOutputSource(outputPath);
		// Creates delimiters for formatting the results
		Delimiters delimiters = new Delimiters(String.valueOf(delimiter),"! =");
		
		// Creates the job request with all the needed parameters
		ComputingJobRequest request = new ComputingJobRequest(inputSource,outputSource,delimiters);
		// Submit the job to the coordinator
		ComputingJobResponse response = coordinator.submission(request);
	}

}
