package project.processapi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 * Implementation of DataStorageAPI for file-based operations
 * Handles reading integers from files and writing formatted results
 */
public class DataStorageAPIIm implements DataStorageAPI {
	
	/*
	 * Contains the file path to read from
	 * Reads integers from a file line by line
	 * Should return response with list of integers if successful, failure status otherwise 
	 */
	@Override
    public DataReadResponse readInput(DataReadRequest request) {
		
		List<Integer> data = new ArrayList<>();
		if (request == null) {
	        throw new IllegalArgumentException("Request cannot be null");
	    }
		if (request.getLocation() == null || request.getLocation().isEmpty()) {
		    throw new IllegalArgumentException("Location cannot be null or empty");
		}
		try (BufferedReader reader = new BufferedReader(new FileReader(request.getLocation()))){
			String line;
			while((line = reader.readLine()) != null) {
				line = line.trim();
				if (!line.isEmpty()) {
					data.add(Integer.parseInt(line));
				}
			}
				return new DataReadResponse(data, DataOperationStatus.SUCCESS);
		} catch (IOException e) {
		    return new DataReadResponse(null,DataOperationStatus.FAILED);
	    }
	}
  
	/*
	 * Writes formatted result to a file
	 * Should return failure or success response
	 */
    @Override
    public DataWriteResponse writeOutput(DataWriteRequest request) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(request.getLocation()))){
        	writer.write(request.getFormattedResult());
        	
        	return new DataWriteResponse(DataOperationStatus.SUCCESS);
        	
        } catch (IOException e) {
        	 return new DataWriteResponse(DataOperationStatus.FAILED);
        }
       
    }

}