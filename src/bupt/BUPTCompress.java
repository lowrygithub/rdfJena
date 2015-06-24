package bupt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BUPTCompress {
	static String fileName = "finalmr";
	static String baseDir = fileName + ".ntriple";
	static String CompressBaseDir = fileName + ".ntriple-com";
	
	private static final String bupt = "http://www.semanticweb.org/wangwg/ontologies/2012/8/BigOnto#";
	private static final String rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#";
	private static final String rdfs="http://www.w3.org/2000/01/rdf-schema#";
	private static final String owl="http://www.w3.org/2002/07/owl#";
	private static final String xsd="http://www.w3.org/2001/XMLSchema#";
	public static void main(String[] args){
		compress(new File(baseDir));
	}
	private static void compress(File file) {
		if(file.isFile()){
			String newFile = file.getPath().replace(baseDir, CompressBaseDir);
			try {
				new File(newFile).createNewFile();
				BufferedReader bfr = new BufferedReader(new FileReader(file));
				BufferedWriter bfw = new BufferedWriter(new FileWriter(new File(newFile)));
				String line;
				while((line=bfr.readLine())!=null){
					line=line.replace(bupt, "bupt.").replace(rdf,"rdf.").replace(rdfs,"rdfs.").replace(owl, "owl.").replace(xsd, "xsd.").replace("<","").replace(">","");
					bfw.write(line+"\n");
				}
				bfr.close();
				bfw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			String newFile = file.getPath().replace(baseDir, CompressBaseDir);
			System.out.println(newFile);
			new File(newFile).mkdirs();
			for(File f:file.listFiles()){
				compress(f);
			}
		}
	}
}
