package client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import grpc.storage.DataReadRequest;
import grpc.storage.DataReadResult;
import grpc.storage.DataWriteRequest;
import grpc.storage.DataWriteResult;
import grpc.storage.DataStorageServiceGrpc;
import project.processapi.DataStorageAPI;
import project.processapi.DataOperationStatus;
import project.processapi.DataReadResponse;
import project.processapi.DataWriteResponse;
import java.util.List;
/*
 * Network client that implements DataStorageAPI
 * Sends read and write requests to the remote storage server over the network
 */
public class StorageClient implements DataStorageAPI {
	// Network connection to the StorageServer
	private final ManagedChannel network;
	
	// grpc client that makes procedure calls
	private final DataStorageServiceGrpc.DataStorageServiceBlockingStub stub;
	
	// Constructor sets the connection to StorageServer
	public StorageClient(String host, int port) {
		// Host validation to prevent invalid connection
		if (host == null || host.trim().isEmpty()) {
		       throw new IllegalArgumentException("Host can't be null or empty!");
		 }
		// Creates a connection to the server
		this.network = ManagedChannelBuilder.forAddress(host,port)
											.usePlaintext()
											.build();
		// Creates a new blocking stub
		this.stub = DataStorageServiceGrpc.newBlockingStub(network);
	}
	
	// Reads data from the file on the remote server and implements DataStorageAP interface
	public DataReadResponse readInput(project.processapi.DataReadRequest apiReadRequest) {
		// Checks if request and file location are valid
		if (apiReadRequest == null || apiReadRequest.getLocation() == null) {
		    return new DataReadResponse(null, DataOperationStatus.FAILED);
		}
		// Gets file path from API request
		String filePath = apiReadRequest.getLocation();
		// Converts to grpc request format
		DataReadRequest grpcReadRequest = DataReadRequest.newBuilder()
														 .setFilePath(filePath)
														 .build();
		// Sends the request and waits for the response
		DataReadResult grpcReadResult = stub.readInput(grpcReadRequest);
		
		// Checks if the remote operation's successful
		if(grpcReadResult.getSuccess()) {
			List<Integer>numbers = grpcReadResult.getNumbersList();
			
			// Converts back to the API response format with success status
			return new DataReadResponse(numbers,DataOperationStatus.SUCCESS);
		} else {
			// Returns failure status if failed
			return new DataReadResponse(null,DataOperationStatus.FAILED);
		}
	}
	
	// Writes data to a file on the remote server and implements DataStorageAPI interface
	public DataWriteResponse writeOutput(project.processapi.DataWriteRequest apiWriteRequest) {
		// Checks if request and file location are valid
		if (apiWriteRequest == null || apiWriteRequest.getLocation() == null) {
	        return new DataWriteResponse(DataOperationStatus.FAILED);
	    }
		// Gets file path and content from API request (location and formatted result)
		String filePath = apiWriteRequest.getLocation();
		String formattedResult = apiWriteRequest.getFormattedResult();
		// Formatted result validation
		if (formattedResult == null) {
		    return new DataWriteResponse(DataOperationStatus.FAILED);
		}
		// Converts to the grpc request format
		DataWriteRequest grpcWriteRequest = DataWriteRequest.newBuilder()
															.setFilePath(filePath)
															.setContent(formattedResult)
															.build();
		// Sends request and waits for the response
		DataWriteResult grpcWriteResult = stub.writeOutput(grpcWriteRequest);
		
		// Converts the result to API response format
		if(grpcWriteResult.getSuccess()) {
			return new DataWriteResponse(DataOperationStatus.SUCCESS);
		} else {
			return new DataWriteResponse(DataOperationStatus.FAILED);
		}
		
	}
	// Closes the network connection to the server when done using
	public void shutdown() {
		if(network != null && !network.isShutdown()) {
			network.shutdown();
		}
	}
}


