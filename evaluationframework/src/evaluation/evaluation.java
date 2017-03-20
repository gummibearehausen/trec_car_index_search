package evaluation;

import java.io.IOException;


public class evaluation {
	public static void main(String[] args) throws NumberFormatException, IOException{
		String dataTruth = "spritzer.cbor.hierarchical.qrels";
		String dataResult = "spritzer-self-test2.run";
		
		String dataTruth1 = "all.test200.cbor.hierarchical.qrels";
		String dataResult1 = "test200-mock1.run";
		String dataPath = "data/";
		ReadMockResult searchResult =new  ReadMockResult(dataPath, dataResult1);
		ReadTruthFile newfile = new ReadTruthFile(dataPath, dataTruth1,searchResult.getSearchResult());
		newfile.printEval();	
	}
}
