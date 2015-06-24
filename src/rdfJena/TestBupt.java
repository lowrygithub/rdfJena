package rdfJena;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.mindswap.pellet.jena.PelletReasonerFactory;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.FileManager;

public class TestBupt {
	public static void main(String[] args) throws FileNotFoundException{
		OntModel model = ModelFactory.createOntologyModel(PelletReasonerFactory.THE_SPEC);
		
//		InputStream in = FileManager.get().open("testbupt_s");
		model.read(new FileInputStream("E:\\eclipse_workspace\\rdfJena\\src\\rdfJena\\testbupt_s"),"");
		
		System.out.println("1----");
		model.write(System.out);
//		System.out.println("2----");
//		model.write(System.out, "RDF/XML-ABBREV");
//		System.out.println("3----");
//		model.write(System.out, "N-TRIPLES");
		
	}
}
