package grpc;

import io.grpc.stub.StreamObserver;
import grpc.computation.ComputingJobRequest;
import grpc.computation.ComputingJobResult;
import grpc.computation.JobStatusCode;
import grpc.computation.ComputingServiceGrpc;
import project.networkapi.UserComputingAPI;
import project.networkapi.FileInputSource;
import project.networkapi.FileOutputSource;
import project.networkapi.Delimiters;
import project.networkapi.ComputingJobResponse;
import project.networkapi.ComputingJobSuccess;
/* 
 * The grpc service implementation for factorial computation
 * This is a connection between grpc network messages and UserComputingAPI
 */
public class ComputeService extends ComputingServiceGrpc.ComputingServiceImplBase {

    private final UserComputingAPI computing;
  
    public ComputeService(UserComputingAPI computing) {
        this.computing = computing;
    }

    // Handles job submission requests from network clients
    // This method is called when the client calls submitJob()
    @Override
    public void submitJob(ComputingJobRequest request,StreamObserver<ComputingJobResult>responseObserver) {

    	// Validation request to prevent null request and input/output locations
    	if (request == null || request.getInputLocation() == null || request.getOutputLocation() == null) {
            ComputingJobResult error = ComputingJobResult.newBuilder()
            								             .setJobStatus(JobStatusCode.BAD_REQUEST)
            										     .build();
            
            responseObserver.onNext(error);
            responseObserver.onCompleted();
            return;
        }
    	
        // Extracts information from the grpc request and creates the input source
        FileInputSource inputSource = new FileInputSource(request.getInputLocation());
        
        // Creates output source
        FileOutputSource outputSource = new FileOutputSource(request.getOutputLocation());

        // Creates delimiters formatting
        Delimiters formatting = new Delimiters(request.getDelimiter(),"! = ");

        // Converts grpc request to our api request format
        project.networkapi.ComputingJobRequest apiRequest = new project.networkapi.ComputingJobRequest(inputSource, outputSource, formatting);

        // Submits the job to the computing coordinator
        ComputingJobResponse apiResponse = computing.submission(apiRequest);

        // Converts the status from our api format to grpc format
        JobStatusCode status = (apiResponse.getStatus() == ComputingJobSuccess.SUCCESS) ? JobStatusCode.SUCCESS : JobStatusCode.BAD_REQUEST;

        // Builds the grpc response message
        ComputingJobResult result = ComputingJobResult.newBuilder().setJobStatus(status).build();

        // Sends the response back to the client
        responseObserver.onNext(result);

        // Tells grpc we're done with the request
        responseObserver.onCompleted();
    }
}
