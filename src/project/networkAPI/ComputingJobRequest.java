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
		
		/**
		 * Creates a new computing job request.
		 * @param input where to read data from
		 * @param output where to write results  
		 * @param delimiters formatting characters (can be null)
		 */
		
		public ComputingJobRequest(Input input, Output output, Delimiters delimiters) {
			this.input = input;
			this.output = output;
			this.delimiters = delimiters;
		}
		
		/**
		 * Returns the input source for this job
		 * @return input source
		 */
		public Input getInput() {
			return input;
		}
		/**
		 * Returns the output source for this job
		 * @return output source
		 */
		public Output getOutput() {
			return output;
		}
		
		/**
		 * Returns the delimiters for this job.
		 * @return delimiters configuration
		 */
		public Delimiters getDelimiters() {
			return delimiters;
		}
		/**
		 * Gets delimiters or provides default ones if none specified
		 * This makes delimiters optional for users
		 * @return delimiters configuration
		 */
		public Delimiters getDelimitersOrDefault() {
			return delimiters != null ? delimiters : Delimiters.defaultDelimiters();
		}
}
