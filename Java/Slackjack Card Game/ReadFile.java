
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;


public class ReadFile {
	private String path;
	
	public ReadFile (String h) {
		path = h;
	}
	
    public String toDisplay () throws IOException { 
      
        FileReader fr = new FileReader(path); 
        BufferedReader textReader = new BufferedReader (fr);
        int numberOfLines = readLines();
        String[ ] textData = new String[numberOfLines];
        int i;

        for (i=0; i < numberOfLines; i++) {
        textData[ i ] = textReader.readLine();

        }
        
        textReader.close( );
        if (numberOfLines == 0) {
        	return "0 0 0";
        }
        return textData[numberOfLines-1];
        
    } 
    
    public int readLines() throws IOException {
    		FileReader file_to_read = new FileReader (path);
    		BufferedReader bf = new BufferedReader (file_to_read);
    		
    		
    		int numberOfLines =0;
    		
    		while ((bf.readLine()) !=null) {
    				numberOfLines++;
    		}
    bf.close();
    return numberOfLines;
    		
    } 
}
