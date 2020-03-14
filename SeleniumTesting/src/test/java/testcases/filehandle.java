package testcases;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class filehandle {

	public static void main(String[] args) throws IOException {
		// create a file
		File file = new File("D:\\test.txt");
		file.createNewFile();

		// Write inside a file
		FileWriter fw = new FileWriter("D:\\test.txt");
		BufferedWriter filewriter = new BufferedWriter(fw);

		filewriter.write("This is the First Line");
		filewriter.newLine();
		filewriter.write("This is the Second Line");
		filewriter.newLine();
		filewriter.write("This is the Third Line");
		filewriter.newLine();
		filewriter.write("This is the Fourth Line");
		filewriter.newLine();
		filewriter.write("This is the Fifth Line");
		filewriter.newLine();

		filewriter.flush();

		// Reading the file
		FileReader fr = new FileReader("D:\\test.txt");
		BufferedReader filereader = new BufferedReader(fr);
		String line = null;
		while ((line = filereader.readLine()) != null)
			System.out.println(line);

		filereader.close();

	}

}
