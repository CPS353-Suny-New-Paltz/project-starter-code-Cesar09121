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
		public ComputingJobRequest(InputSource inputSource, OutputSource outputSource, Delimiters delimiters) {
			this.inputSource = inputSource; // Input where to read data from
			this.outputSource = outputSource; // Output where to write results
			this.delimiters = delimiters; // Delimiters formatting characters (can be null or default)
		}
		
		// Gets the input source location for reading data
		public InputSource getInput() {
			return inputSource;
		}
		
		// Gets the output destination for writing results
		public OutputSource getOutput() {
			return outputSource;
		}
		
		// Gets the delimiters for formatting output
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
