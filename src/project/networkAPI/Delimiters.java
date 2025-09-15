package project.networkAPI;


/**
 * Handles delimiter characters in output formatting
 * Allows customization of how output/results will be formatted and displayed
 * 
 * Expected output example: 
 * - "input1! = output1 ; input2! = output2"
 * - "! = " separates input and output 
 * - " ; " separates input-output pairs  
 */
public class Delimiters {
	// String with characters that separates different input-output pairs 
	private final String delimiters;
	// String with characters that separates input from output
	private final String delimiterResult;
	
	
	//Construct a delimiter configuration 
	public Delimiters(String delimiters, String delimiterResult) {
		this.delimiters = delimiters; // delimiters characters for separating inputs	
		this.delimiterResult = delimiterResult;  //delimiterResult characters for separating results
		
	}
	
	// Gets the delimiter that separates different input-result pairs
	public String getDelimiters() {
		return delimiters;
	}
	// Gets the delimiter that separates input from output
	public String getDelimiterResult() {
		return delimiterResult;
	}
	/**
	 * Creates method for returning default delimiters 
	 * - delimiters: " ; "
	 * - delimiterResult: "! = " 
	 */
	
	public static Delimiters defaultDelimiters() {
		return new Delimiters(" ; ", "! = ");
	}
}
