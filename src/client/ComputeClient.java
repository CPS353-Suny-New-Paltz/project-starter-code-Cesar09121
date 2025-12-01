package client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import grpc.computation.ComputingJobRequest;
import grpc.computation.ComputingJobResult;
import grpc.computation.JobStatusCode;
import grpc.computation.ComputingServiceGrpc;
import java.util.Scanner;
/*
 * User-facing client for submitting factorial computation jobs
 * Connects to ComputeServer and submits jobs for processing
 */
public class ComputeClient {

	public static void main(String[]args) {
		
		String inputLocation;
        String outputLocation;
        String delimiter;

        if (args.length >= 2) {       	
            inputLocation = args[0];
            outputLocation = args[1];
            delimiter = args.length >= 3 ? args[2] : ";";
            
        } else {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Input file: ");
            
            inputLocation = scanner.nextLine();

            System.out.print("Output file: ");
            
            outputLocation = scanner.nextLine();

            System.out.print("Delimiter: ");
            
            String delimInput = scanner.nextLine();
            delimiter = delimInput.isEmpty() ? ";" : delimInput;

            scanner.close();
        }
        // Creates a connection to ComputeServer
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                						              .usePlaintext() 
                						              .build();

        // Creates a blocking stub
        ComputingServiceGrpc.ComputingServiceBlockingStub stub = ComputingServiceGrpc.newBlockingStub(channel);

        // Creates the grpc request message
        ComputingJobRequest request = ComputingJobRequest.newBuilder()
                                                         .setInputLocation(inputLocation)
                                                         .setOutputLocation(outputLocation)
                                                         .setDelimiter(delimiter)
                                                         .build();

        System.out.println("Submitting job...");

        // Sends the request over the network
        ComputingJobResult result = stub.submitJob(request);

        if (result.getJobStatus() == JobStatusCode.SUCCESS) {
            System.out.println("SUCCESS: Sending results to "+ outputLocation);
        } else {
            System.out.println("FAILED: " +result.getJobStatus());
        }

        // Closes the connection
        channel.shutdown();


	}
}
