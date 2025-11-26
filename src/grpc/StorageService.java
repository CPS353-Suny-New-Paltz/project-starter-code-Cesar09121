package grpc;

import io.grpc.stub.StreamObserver;
import grpc.storage.DataReadRequest;
import grpc.storage.DataReadResult;
import grpc.storage.DataWriteRequest;
import grpc.storage.DataWriteResult;
import grpc.storage.DataStorageServiceGrpc;
import project.processapi.DataStorageAPI;
import project.processapi.DataStorageAPIIm;
import java.util.List;

/*
 * grpc service implementation for file storage operations
 * Adapts the gRPC messages to the existing DataStorageAPI
 */
public class StorageService extends DataStorageServiceGrpc.DataStorageServiceImplBase {
    
    private final DataStorageAPI dataStorageAPI;
    
    // Creates the storage engine
    public StorageService() {
        this.dataStorageAPI = new DataStorageAPIIm();
    }
    
    // Handles read requests from network clients
    // Called when client calls readInput()
    @Override
    public void readInput(DataReadRequest request, StreamObserver<DataReadResult> responseObserver) {
        
    	// Extracts file path from the grpc request
    	String filePath = request.getFilePath();
    	
        System.out.println("Read request: " + filePath);
        
        // Converts grpc request to API request format
        project.processapi.DataReadRequest apiRequest = new project.processapi.DataReadRequest(filePath);        
        // Calls storage implementation to read the file
        project.processapi.DataReadResponse apiResponse = dataStorageAPI.readInput(apiRequest);
            
        // Build gRPC response
        DataReadResult.Builder resultBuilder = DataReadResult.newBuilder();
            
        // Checks if the reading was successful
        if (apiResponse.getStatus().isSuccess()) {
        	// Gets numbers from the read file
            List<Integer> numbers = apiResponse.getData();
            // Adds all numbers to grpc response
            resultBuilder.addAllNumbers(numbers);
            resultBuilder.setSuccess(true);
            
            System.out.println("Read " +numbers.size() +" numbers");
         } else {
        	// Sends failure response if reading failed
            resultBuilder.setSuccess(false);
            
            System.err.println("Read failed");
         }
            
            // Send response back to client
            responseObserver.onNext(resultBuilder.build());
            responseObserver.onCompleted();
    }
    
    // Handles write requests from network clients
    // Called when client calls writeOutput()
    @Override
    public void writeOutput(DataWriteRequest request, StreamObserver<DataWriteResult> responseObserver) {
        
    	String filePath = request.getFilePath();
        String content = request.getContent();
        System.out.println("Writing file: " +filePath);
        
        // Use our existing DataStorageAPI
        project.processapi.DataWriteRequest apiRequest = new project.processapi.DataWriteRequest(filePath,content);
        
        project.processapi.DataWriteResponse apiResponse = dataStorageAPI.writeOutput(apiRequest);
        
        // Build gRPC response
        DataWriteResult result = DataWriteResult.newBuilder()
                				.setSuccess(apiResponse.getStatus().isSuccess())
                				.build();
        
        // Send response
        responseObserver.onNext(result);
        responseObserver.onCompleted();
        
        if (result.getSuccess()) {
            System.out.println("Write succeeded");
        } else {
            System.err.println("Write failed");
        }
    }
}