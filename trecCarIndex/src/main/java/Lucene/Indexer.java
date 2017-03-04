package Lucene;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class Indexer {
	private  static String indexPath;
	private   static  String dataPath;
	
	

	public static IndexWriter indexWriterCreator(String indexPath, int create_or_append) throws IOException{
		
    	Analyzer analyzer = new StandardAnalyzer();
    	
        // 1. create the index
    	
        FSDirectory directory = FSDirectory.open(new File(indexPath));

        IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_4_10_1, analyzer);
        if (create_or_append ==0){config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);}else{
        	config.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);}
        config.setRAMBufferSizeMB(64);
        config.setMaxBufferedDocs(4000);
        IndexWriter w = new IndexWriter(directory, config);
        return w;
        
	}

    public  static void main(String[] args) throws IOException, ParseException {
        // 0. Specify the analyzer for tokenizing text.
        //    The same analyzer should be used for indexing and searching
         //0 is for tfidf, 1 is for BM25
    	Analyzer analyzer = new StandardAnalyzer();

        // 1. create the index 	
        FSDirectory directory = FSDirectory.open(new File(indexPath));

        IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_4_10_1, analyzer);
        config.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
        config.setRAMBufferSizeMB(64);
        config.setMaxBufferedDocs(4000);
        IndexWriter w = new IndexWriter(directory, config);
        
        //2.read the data file
        FileInputStream fstream = new FileInputStream(dataPath);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

        String strLine;

        //Read File Line By Line
        while ((strLine = br.readLine()) != null)   {
          // Print the content on the console
        	String[] oneDoc = strLine.split("\t");
        	String title = oneDoc[0];
        	String content = oneDoc[1];
        	System.out.println(title);
//        	addDoc(w,content,title);
          
        }

        //Close the input stream
        br.close();
        // Close the writer
        w.close(); 
    }

    public static void addDoc(IndexWriter w, String content, String paraId, String entities) throws IOException {
        Document doc = new Document();
        doc.add(new TextField("text", content, Field.Store.YES));

        // use a string field for isbn because we don't want it tokenized
        doc.add(new StringField("paraID", paraId, Field.Store.YES));
        doc.add(new StringField("entities", entities, Field.Store.YES));
        w.addDocument(doc);
    }
}