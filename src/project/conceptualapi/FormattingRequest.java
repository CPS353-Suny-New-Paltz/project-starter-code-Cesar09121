package project.conceptualapi;

/*
 * Request for formatting a calculated result
 * Contains input, output and formatted displaying
 */

public class FormattingRequest {
	
	private final int input;
	private final long output;
	private final String delimiters;
	
	// Creates a request for formatting
	public FormattingRequest(int input,String delimiters ,long output) {
		this.input = input;					// input value
		this.output = output;				// factorial output
		this.delimiters = delimiters;		// the characters to separate the input and output
	
	}

	// Gets the original number (input)
	public int getInput() {
		return input;
	}

	// Gets the factorial result (output)
	public long getOutput() {
		return output;
	}

	// Gets the characters for formating ( "! =")
	public String getDelimiters() {
		return delimiters;
	}
	
	
	
}
