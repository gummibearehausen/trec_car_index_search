package evaluation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ReadMockResult {
	private String data_path;
	private String data_result;
	private Map<String, ArrayList<String>> ranklistByquery;
	public ReadMockResult(String data_path,String data_result) throws IOException{
		this.data_path=data_path;
		this.data_result = data_result;
		this.ranklistByquery = new HashMap<String, ArrayList<String>>();
		result_constructor();
	}
	
	
	public void result_constructor() throws IOException{
		String filename2 = data_path+data_result;
		String line2;
		InputStream is2 = new FileInputStream(new File(filename2));
		BufferedReader br2 = new BufferedReader(new InputStreamReader(is2));

		
	    
		while ((line2 =br2.readLine()) != null){
				String[] parsedLine2 = line2.split(" ");
				String queryId = parsedLine2[0];
				String docId2 = parsedLine2[2];
				if(ranklistByquery.containsKey(queryId)){
					ranklistByquery.get(queryId).add(docId2);
				}else{
					ArrayList<String> ranklist= new ArrayList<String>();
					ranklist.add(docId2);
					ranklistByquery.put(queryId, ranklist);
				}
			}
		br2.close();
	}
	
	public Map<String, ArrayList<String>>getSearchResult(){
		return ranklistByquery;
	}

}
