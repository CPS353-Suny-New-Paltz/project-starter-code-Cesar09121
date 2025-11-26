package grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.IOException;

/**
 * gRPC server for file storage operations
 * Listens for network requests on port 50052
 * Handles file reading and writing operation remotely	
 */
public class StorageServer {
    
	// Main method for starting the storage server
    public static void main(String[] args) throws IOException, InterruptedException {
        
    	// Creates and configure the gRPC server 
        Server server = ServerBuilder.forPort(50052) // uses port 50052 for storage operation
                					 .addService(new StorageService()) // registers the service
                                     .build()      // builds the server
                                     .start();     // starts listening for request
        
        System.out.println("StorageServer started on port 50052");
        // Stops the server with shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutting down StorageServer");
            server.shutdown();
        }));
        // The server keeps running until finished
        server.awaitTermination();
    }
}