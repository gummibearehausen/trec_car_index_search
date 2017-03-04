package edu.unh.cs.treccar.read_data;

import edu.unh.cs.treccar.Data;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.index.IndexWriter;

import Lucene.Indexer;




/**
 * User: dietz
 * Date: 12/9/16
 * Time: 5:17 PM
 */
public class ReadDataTest {

	private static String dir = "spritzer-v1/";
//	private  String articles;
//	private String outlines;
//	private String paragraphs;
//	public ReadDataTest(String dir,String file_name){
//		this.dir=dir;
//		this.articles=dir+file_name+"cbor";
//		this.outlines = dir+file_name+"cbor.outlines";
//		this.paragraphs = dir+file_name +"cbor.paragraphs";
//		
//		
//	}
    public static void main(String[] args) throws Exception{
        System.setProperty("file.encoding", "UTF-8");
        
//        if (args.length<3) {
//            System.out.println("Command line parameters: articlefile outlinefile paragraphfile");
//            System.exit(-1);
//        }

        //String articles = args[0];
        //String outlines = args[1];
        //String paragraphs = args[2];
        String articles = dir+"spritzer.cbor";
        String outlines = dir+"spritzer.cbor.outlines";
        String paragraphs = dir +"spritzer.cbor.paragraphs";

        final FileInputStream fileInputStream3 = new FileInputStream(new File(articles));
        for(Data.Page page: DeserializeData.iterableAnnotations(fileInputStream3)) {
            for (List<Data.Section> sectionPath : page.flatSectionPaths()){
                System.out.println(Data.sectionPathId(page.getPageId(), sectionPath)+"   \t "+Data.sectionPathHeadings(sectionPath));
            }
            System.out.println();
        }

        System.out.println("\n\n");
//     
//        final FileInputStream fileInputStream3 = new FileInputStream(new File("release.articles"));
//        for(Data.Page page: DeserializeData.iterableAnnotations(fileInputStream3)) {
//            for (List<String> line : page.flatSectionPaths()){
//                System.out.println(line);
//            }
//            System.out.println();
//        }
//
//        System.out.println("\n\n");
//
//        final FileInputStream fileInputStream4 = new FileInputStream(new File("release.articles"));
//        for(Data.Page page: DeserializeData.iterableAnnotations(fileInputStream4)) {
//            for (Data.Page.SectionPathParagraphs line : page.flatSectionPathsParagraphs()){
//                System.out.println(line.getSectionPath()+"\t"+line.getParagraph().getTextOnly());
//            }
//            System.out.println();
//        }

//
        System.out.println("\n\n");
        final FileInputStream fileInputStream = new FileInputStream(new File(outlines));
        for(Data.Page page: DeserializeData.iterableAnnotations(fileInputStream)) {
            System.out.println(page);
            System.out.println();
        }


        System.out.println("\n\n");

       final FileInputStream fileInputStream2 = new FileInputStream(new File(paragraphs));
       String indexPath = "indexfile/";
       IndexWriter w = Indexer.indexWriterCreator(indexPath,0);

        for(Data.Paragraph p: DeserializeData.iterableParagraphs(fileInputStream2)) {
        	String textonly = p.getTextOnly();
        	String paraId = p.getParaId();
        	String rawtext = p.getTextOnly().replace("%20", " ");// remove "%20" from  entity anchor text such as "Funafuti%20Conservation%20Area" to 
        	String entities = StringUtils.join(p.getEntitiesOnly(),"\t");
        	Indexer.addDoc(w,rawtext,paraId,entities);
        	
        	
        	System.out.println(entities);
        	System.out.println(p.getParaId());
            System.out.println(p.getTextOnly().replace("%20", " "));
            System.out.println();
        }
       w.close();

    }
}
