package project.checkpointtests;
import project.conceptualapi.ComputationAPI;
import project.conceptualapi.ComputationAPIIm;
import project.processapi.DataStorageAPI;
import project.processapi.DataStorageAPIIm;
import project.networkapi.UserComputingAPI;
import project.networkapi.UserComputingAPIIm;
import project.networkapi.ComputingJobRequest;
import project.networkapi.ComputingJobResponse;
import project.networkapi.FileInputSource;
import project.networkapi.FileOutputSource;
import project.networkapi.Delimiters;

public class ManualTesting {
    
    public static final String INPUT = "testInput.txt";
    public static final String OUTPUT = "testOutput.txt";

    public static void main(String[] args) {
        
        // Creates the data storage API (Process API) that handles file reading/writing
        DataStorageAPI dataStorageAPI = new DataStorageAPIIm();
        
        // Creates the computation API (Conceptual API) that handles factorial calculations
        ComputationAPI computationAPI = new ComputationAPIIm();
        
        // Creates the user computing API (Network API) with both dependencies
        // This is the coordination component that brings everything together
        UserComputingAPI userComputingAPI = new UserComputingAPIIm(dataStorageAPI, computationAPI);
        
        
        // Creates input source pointing to the input file
        FileInputSource inputSource = new FileInputSource(INPUT);
        
        // Creates output source pointing to the output file
        FileOutputSource outputSource = new FileOutputSource(OUTPUT);
        
        
        // Format will be: "input1! = output2, input2! = output2, input3! = output3"
        Delimiters delimiters = new Delimiters(",", "! = ");
        
        // Creates the computing job request with all the parameters
        ComputingJobRequest request = new ComputingJobRequest(inputSource, outputSource, delimiters);
        
        // Submits the job through the system
        ComputingJobResponse response = userComputingAPI.submission(request);
        
        // Prints out the result
        System.out.println("Job status: " + response.getStatus().getMessage());
    }
}