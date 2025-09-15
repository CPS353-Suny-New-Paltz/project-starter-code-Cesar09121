package project.networkapi;


/*
 * Request class for computing job from user to compute engine
 * Contains needed information to process a computing job:
 * - Input: Input data for computation
 * - Output: Where output/results go to
 * - Delimiters: Custom delimiters for formatting output 
 */

public class ComputingJobRequest {
	
		private final InputSource inputSource ;
		private final OutputSource outputSource;
		private final Delimiters delimiters;
		
		// Creates a new computing job request
		public ComputingJobRequest(InputSource input, OutputSource output, Delimiters delimiters) {
			this.inputSource = input; // Input where to read data from
			this.outputSource = output; // Output where to write results
			this.delimiters = delimiters; // Delimiters formatting characters (can be null or default)
		}
		
		public InputSource getInput() {
			return inputSource;
		}
		
		public OutputSource getOutput() {
			return outputSource;
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
