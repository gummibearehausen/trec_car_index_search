package dmmMixture;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONObject;

public class readfile {
	String fileName;	
	InputStream is;
	int D = 0;

	
	

	
	public static void main(String[] args) throws IOException {
		
	
	String dataDir = "datatest/";
	ArrayList<String> documents = new ArrayList<String>();		
	int [] fileId = new int []{1,2};
	for(int i:fileId){
		String fName = "textfile";
		String filename = dataDir+fName+Integer.toString(i)+".txt";
		InputStream is = new FileInputStream(new File(filename));
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		while (br.readLine() != null){
			String line = br.readLine();
			documents.add(line);	
	}
	
		
		
	}
	System.out.println(documents);
}
}