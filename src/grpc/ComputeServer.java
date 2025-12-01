package grpc;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import project.conceptualapi.ComputationAPI;
import project.conceptualapi.ComputationAPIIm;
import project.networkapi.UserComputingMultiThread;
import client.StorageClient;
import java.io.IOException;
/*
 * Main grpc server for the factorial computation
 * Coordinates the whole factorial system
 * Connects to StorageServer for the input and output files
 * Listens to the client request
 */
public class ComputeServer {

	  public static void main(String[] args) throws IOException, InterruptedException {
		  // Connects to the StorageServer (on port 50052)
		  StorageClient storage = new StorageClient("localhost",50052);
		  
		  // Creates local computation engine
		  ComputationAPI computation = new ComputationAPIIm();
		  
		  // Creates the coordinator that manages the process
		  UserComputingMultiThread coordinator = new UserComputingMultiThread(storage, computation);
		  
		  // Puts the coordinator in the grpc service
		  ComputeService computeService = new ComputeService(coordinator);
		  
		  // Builds and start a server
		  Server server = ServerBuilder.forPort(50051).addService(computeService)
				  										  .build()
				  										  .start();
		  
		  System.out.println("Server started on port 50051");
		  
		  // Waits until the server is finished
		  server.awaitTermination();

				  										
	  }
}
