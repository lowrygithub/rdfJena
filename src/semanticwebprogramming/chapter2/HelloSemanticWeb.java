package semanticwebprogramming.chapter2;

import java.io.IOException;
import java.io.InputStream;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.FileManager;

public class HelloSemanticWeb {
	private Model friends =null;
	static String defaultNameSpace = "";
	public static void main(String[] args) throws IOException {
		HelloSemanticWeb hello =new HelloSemanticWeb();
		
		//load my FOAF friends
		System.out.println("load my FOAF friends");
		hello.populateFOAFFriends();
		//...
	}
	private void populateFOAFFriends() throws IOException {
		friends = ModelFactory.createOntologyModel();
		InputStream inFoafInstance = FileManager.get().open("Ontologies/FOAFFriends.rdf");
		friends.read(inFoafInstance,null);
		inFoafInstance.close();
	}

}
