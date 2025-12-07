package client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import grpc.computation.ComputingJobRequest;
import grpc.computation.ComputingJobResult;
import grpc.computation.JobStatusCode;
import grpc.computation.ComputingServiceGrpc;

import java.io.File;
import java.io.PrintWriter;
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
        	
            if(args[0].equals("-number")) {
            	String numbers = args[1];
            	inputLocation = tempFile(numbers);
                outputLocation = args[2];
                delimiter = args.length >= 4 ? args[3] : ";";
            } else {
            	inputLocation = args[0];
                outputLocation = args[1];
                delimiter = args.length >= 3 ? args[2] : ";";
            }
            
        } else {
        	
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter number or input file: ");
            String input = scanner.nextLine();
            
            // Checks if input is a file or numbers
            if (new File(input).exists()) {           	
                inputLocation = input;  // It's a file              
            } else {            	
                inputLocation = tempFile(input);  // It's numbers
            }

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
        // Creates temporary file from comma-separated numbers
        private static String tempFile(String numbers) {
        	
            try {
            	// Generate unique filename with timestamp
            	long timestamp = System.currentTimeMillis();
            	String filename = "test/temp_input_" + timestamp +".txt";
            	File temp = new File(filename);
                
                PrintWriter writer = new PrintWriter(temp);
                for (String num : numbers.split(",")) {
                    writer.println(num.trim());
                }
                
                writer.close();
                
                return filename;
                
            } catch (Exception e) {
            	
                System.err.println("Error: " + e.getMessage());
                System.exit(1);
                return null;
                
            }
	}
}
