package rdfJena;

import java.io.InputStream;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.FileManager;


public class Test2 {
	public static void main(String[] args){
		
		Model model = ModelFactory.createDefaultModel();
		
		InputStream in = FileManager.get().open("test");
		
		model.read(in, null);
		
		System.out.println("1----");
		model.write(System.out);
		System.out.println("2----");
		model.write(System.out, "RDF/XML-ABBREV");
		System.out.println("3----");
		model.write(System.out, "N-TRIPLES");
		
	}
}
