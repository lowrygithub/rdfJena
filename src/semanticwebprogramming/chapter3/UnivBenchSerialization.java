package semanticwebprogramming.chapter3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class UnivBenchSerialization {
	private Model UnivBench =null;
	String inputFileFormat = "RDF/XML";
	String outputFileFormat1 = "TURTLE";
	String outputFileFormat2 = "N-TRIPLE";
	String outputPath = "Univ-Bench/";
	public static void main(String[] args) throws IOException {
		UnivBenchSerialization ubs =new UnivBenchSerialization();
		
		System.out.println("start serialization");
		String inputDir = "C:\\Users\\Lowry\\Desktop\\共建\\rdf\\uba1.7";
		ubs.serializeAll(inputDir);
		System.out.println("finish serialization");
	}
	
	
	private void serializeAll(String inputDir) throws IOException {
		File inputPath = new File(inputDir);
		for(File file: inputPath.listFiles()){
			if(file.getName().charAt(0) == 'U'){
				RandomAccessFile rf = new RandomAccessFile(file, "rw");
				rf.seek(40+9+56+51+43+60+8);
				rf.writeBytes("                                                                                                                            ");
				rf.close();
				serialization(file);
			}
		}
	}


	/**
	 * 
	 * @param inputFile(eg : University0_0.owl)
	 * @throws IOException
	 */
	private void serialization(File inputFile) throws IOException {
		System.out.println(inputFile);
		File outputDir = new File(outputPath + inputFile.getName().split("_")[0]);
		if(!outputDir.exists())
			outputDir.mkdirs();
		System.out.println(outputDir);
		UnivBench = ModelFactory.createOntologyModel();
		FileInputStream in = new FileInputStream(inputFile);
//		FileOutputStream out1 = new FileOutputStream(outputDir+"\\"+inputFile.getName().split("\\.")[0]+".ttl");
		FileOutputStream out2 = new FileOutputStream(outputDir+"\\"+inputFile.getName().split("\\.")[0]+".ntriple");
		UnivBench.read(in ,null, inputFileFormat);
//		UnivBench.write(out1, outputFileFormat1);
		UnivBench.write(out2, outputFileFormat2);
		
		in.close();
//		out1.close();
		out2.close();
		UnivBench.close();
	}

}
