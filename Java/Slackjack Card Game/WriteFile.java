import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class WriteFile {
private String path;


public WriteFile (String q) {
	path = q;
}

public void writeToFile(String textLine) throws IOException {
	FileWriter write = new FileWriter(path);
	PrintWriter print_line = new PrintWriter( new BufferedWriter(write) );
	print_line.printf( "%s" + "\n" , textLine);
	print_line.close();
}
}
