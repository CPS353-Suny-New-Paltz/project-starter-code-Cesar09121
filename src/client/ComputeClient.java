package client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import grpc.computation.ComputingJobRequest;
import grpc.computation.ComputingJobResult;
import grpc.computation.JobStatusCode;
import grpc.computation.ComputingServiceGrpc;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/*
 * User-facing client for submitting factorial computation jobs
 * Connects to ComputeServer and submits jobs for processing
 * 
 * Extends the input flexibility by supporting more input options:
 * - CSV file
 * - Range notation (Ex: type "1-10" or "1-10,13-20" instead of typing all of the needed numbers)
 * - JSON output format 
 */
public class ComputeClient {

	public static void main(String[]args) {
		
		String inputLocation;
        String outputLocation;
        String delimiter;
        String outputFileFormat ="text";

        // Checks if user gave command line argument
        if (args.length >= 2) {       	
        	 if (args[0].equals("-number")) {
        		 
        		 if(args.length < 3) {
        			 System.err.println("Error: Missing arguments for -number!");
        			 System.err.println("Format should be: -number <numbers> <output-file> <delimiter>");
        			 // Command line for converting to JSON file format
        			 System.err.println("Command line example: -number 1-15 output.json ; --format json");
        			 System.exit(1);
        		 }
                 // In-memory mode: -number "5,8,14,19" output.txt ";" for example
                 String numbers = args[1];
                 // Parses the numbers (handles range or list of numbers)
                 List<Integer> parsedNumbers = parseNumbers(numbers);
                 inputLocation = tempFile(parsedNumbers);
                 outputLocation = args[2];
                 delimiter = args.length >= 4 ? args[3] : ";";              
                 // Checks if user wants the JSON output format
                 if (args.length >= 6 && args[4].equals("--format")) {
                	    outputFileFormat = args[5];
                	}
                 
             } else {
                 // File mode: input.txt output.txt ";" 
                 String inputFile = args[0];
                 // Makes sure output file is provided
                 if(args.length < 2) {
                	 System.err.println("Error: Output file needed");
                	 System.err.println("Format should be: <input-file> <output-file> <delimiter>");
                	 System.exit(1);
                 }
                 // Checks if it's a CSV file
                 if(inputFile.endsWith(".csv")) {
                	 List<Integer>numbers = parseCSV(inputFile);
                	 inputLocation = tempFile(numbers);
                 } else {
                	 // Regular text file will be used directly
                	 inputLocation = inputFile;
                 }
                 // Gets output and delimiters
                 outputLocation = args[1];
                 delimiter = args.length >= 3 ? args[2] : ";";                
                 // Checks for format flag
                 if (args.length >= 5 && args[3].equals("--format")) {
                	    outputFileFormat = args[4];
                	}
             }
            
        } else {
        	// No command line argument mode (interactive)
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter numbers, numbers range or file path: ");
            String input = scanner.nextLine();

            // Checks if input is a file or numbers
            File file = new File(input);
            if (file.exists()) {
            	// File case
            	if(input.endsWith(".csv")){
            		// If it's CSV file case then parse it
            		List<Integer> numbers = parseCSV(input);
            		inputLocation = tempFile(numbers); 
            	} else {
            			// Regular text file will be used directly
            			inputLocation = input;
            		}
            } else {
            	// Numbers case (range or numbers)
            	List<Integer> parsedNumbers = parseNumbers(input);
            	inputLocation = tempFile(parsedNumbers);
            }
            
            System.out.print("Output file: ");
            
            outputLocation = scanner.nextLine();
            
            System.out.print("Delimiter: "); // Default delimiter is ;
            
            String delimiterInput = scanner.nextLine();
            delimiter = delimiterInput.isEmpty() ? ";" : delimiterInput;

            // Asks for output format
            System.out.print("Output format (text/json, default text): ");
            String formatInput = scanner.nextLine();
            outputFileFormat = formatInput.isEmpty() ? "text" : formatInput;
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
            
            if(outputFileFormat.equals("json")) {
            	convertToJSON(outputLocation,delimiter);
            }
        } else {
            System.out.println("FAILED: " +result.getJobStatus());
        }

        // Closes the connection
        channel.shutdown();


		}
	
		// Parses numbers with support for ranges
		// Ex: "3,6,9" or "1-10" or "1-5,7-10"
		private static List<Integer> parseNumbers(String input){
			// Creates a list to store numbers
			List<Integer> numbers = new ArrayList<>();
			
			String[] parts = input.split(",");
			
			for(String part : parts) {
				part = part.trim();
				
				// Checks if it's a numbers range (with "-")
				if(part.contains("-")) {
					String[]range = part.split("-");
					int start = Integer.parseInt(range[0].trim());
					int end = Integer.parseInt(range[1].trim());
					
					// Adds all numbers in the range
					for(int i =start;i <= end;i++) {
						numbers.add(i);
					}
				} else {
					// Single number case
					numbers.add(Integer.parseInt(part));
				}
				
			}
			return numbers;
		}
		
		// Parses CSV file 
		private static List<Integer> parseCSV(String filePath){
			// Creates a list to store numbers
			List<Integer> numbers = new ArrayList<>();
			
			try {
				Scanner scanner = new Scanner(new File(filePath));
				
				// Reads the file
				if(scanner.hasNextLine()) {
					String line = scanner.nextLine();
					
					// Splits by comma
					String[]values = line.split(",");
					
					// Converts values to integer then adds to list
					for(String value : values) {
						numbers.add(Integer.parseInt(value.trim()));
					}
				}
				scanner.close();
			} catch (Exception e){
				System.err.println("Cannot read CSV file" + e.getMessage());
				System.exit(1);
			}
			return numbers;
		}
		
		// Converts text output to JSON format
		private static void convertToJSON(String outputFile, String delimiter) {
			try {
				// Checks if the file exists
				File file = new File(outputFile);
		        if (!file.exists()) {
		            System.err.println("Error: Cannot find the output file to convert");
		            return;
		        }
		        
				// Reads the text output file
				Scanner scanner = new Scanner(new File(outputFile));
				String content ="";
				if(scanner.hasNextLine()) {
					content = scanner.nextLine();
				}
				scanner.close();
				
				// Checks if file is empty
	            if (content.trim().isEmpty()) {
	                System.err.println("Error: Cannot convert an empty file");
	                return;
	            }
				// Builds the JSON format
				StringBuilder json = new StringBuilder();
				json.append("{\n  \"Factorial results\": [\n");
				
				// Splits results by delimiter
				String[]entries = content.split(delimiter);
				// Tracks if this is the first entry
				boolean first = true;
				
				for(String entry : entries) {
					if(entry.trim().isEmpty()) {
						continue;
					}
					
					// Parses format " input! = output"
					// "!" and "=" will not be used in the JSON format
					String[]parts = entry.split("=");
					if(parts.length == 2) {
						String input = parts[0].replace("!", "").trim();
						String factorial = parts[1].trim();
						
						// Adds comma before all entry except the first one
						if(!first) {
							json.append(",\n");
						}
						first = false;
						
						// Builds JSON entry
						json.append("  	 {\"input\": ")
							.append(input)
							.append(",  \"factorial\":  \"")
							.append(factorial)
							.append("\"}");
					}
				}
				json.append("\n   ]\n}");
				
				// Writes JSON back to the same file
				PrintWriter writer = new PrintWriter(outputFile);
				writer.println(json.toString());
				writer.close();
				
				System.out.println("Converted output file to JSON format");
			} catch (Exception e) {
				System.err.println("Cannot convert to JSON format "+ e.getMessage());
			}
		}
	
		// Creates temporary file from comma-separated numbers
		private static String tempFile(List<Integer>numbers) {
        try {
            // Generates unique filename using timestamp
            String filename = "test/temp_input_" + System.currentTimeMillis() + ".txt";
            File temp = new File(filename);
            
            PrintWriter writer = new PrintWriter(temp);
            for (Integer num : numbers) {
            	writer.println(num);
            }
            writer.close();
            
            return filename;
            
        } catch (Exception e) {
            System.err.println("Error: "+ e.getMessage());
            System.exit(1);
            return null;
        }
    }
}
