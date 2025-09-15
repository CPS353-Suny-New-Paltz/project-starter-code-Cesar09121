package project.networkapi;


/**
 * Request class for computing job from user to compute engine.
 * Contains needed information to process a computing job:
 * - Input: Input data for computation
 * - Output: Where output/results go to
 * - Delimiters: Custom delimiters for formatting output 
 */

public class ComputingJobRequest {
	
		private final InputSource inputSource;
		private final OutputSource outputSource;
		private final Delimiters delimiters;
		
		/**
		 * Creates a new computing job request.
		 * @param input where to read data from
		 * @param output where to write results  
		 * @param delimiters formatting characters (can be null)
		 */
		
		public ComputingJobRequest(InputSource inputSource, OutputSource outputSource, Delimiters delimiters) {
			this.inputSource = inputSource;
			this.outputSource = outputSource;
			this.delimiters = delimiters;
		}
		
		/**
		 * Returns the input source for this job
		 * @return input source
		 */
		public InputSource getInput() {
			return inputSource;
		}
		/**
		 * Returns the output source for this job
		 * @return output source
		 */
		public OutputSource getOutput() {
			return outputSource;
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
