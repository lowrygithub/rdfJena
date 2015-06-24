package bupt;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class BUPTSerialization {
	static String owlName = "finalmr" ;
	private static Model bupt; 
	private static String inputFile = owlName + ".owl";
	private static String outputFile = owlName;
	static String inputFileFormat = "RDF/XML";
	String outputFileFormat1 = "TURTLE";
	static String outputFileFormat2 = "N-TRIPLE";
	public static void main(String[] args) throws IOException{
		bupt = ModelFactory.createOntologyModel();
		FileInputStream in = new FileInputStream(inputFile);
//		FileOutputStream out1 = new FileOutputStream(outputDir+"\\"+inputFile.getName().split("\\.")[0]+".ttl");
		FileOutputStream out2 = new FileOutputStream(outputFile+".ntriple");
		bupt.read(in ,null, inputFileFormat);
//		UnivBench.write(out1, outputFileFormat1);
		bupt.write(out2, outputFileFormat2);
		
		in.close();
//		out1.close();
		out2.close();
		bupt.close();
	}
}
