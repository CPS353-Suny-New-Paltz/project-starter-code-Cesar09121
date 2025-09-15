package project.networkAPI;


/**
 * Request class for computing job from user to compute engine
 * Contains needed information to process a computing job:
 * - Input: Input data for computation
 * - Output: Where output/results go to
 * - Delimiters: Custom delimiters for formatting output 
 */

public class ComputingJobRequest {
	
		private final Input input ;
		private final Output output;
		private final Delimiters delimiters;
		
		// Creates a new computing job request
		
		public ComputingJobRequest(Input input, Output output, Delimiters delimiters) {
			this.input = input; // Input where to read data from
			this.output = output; // Output where to write results
			this.delimiters = delimiters; // Delimiters formatting characters (can be null or default)
		}
		
		public Input getInput() {
			return input;
		}
		
		public Output getOutput() {
			return output;
		}
		
		public Delimiters getDelimiters() {
			return delimiters;
		}
		/**
		 * Gets delimiters or provides default ones if none specified
		 * This makes delimiters optional for users
		 */
		public Delimiters getDelimitersOrDefault() {
			return delimiters != null ? delimiters : Delimiters.defaultDelimiters();
		}
}
