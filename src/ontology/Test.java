package ontology;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.FileManager;

public class Test {
	private static String URINS="http://lkrontology/Animal#";
	public static void main(String[] args) throws IOException{
		OntModel model=ModelFactory.createOntologyModel();
		model.read("animal");
//		OntClass Animal=model.createClass(URINS+"Animals");
//		OntClass Dogs=model.createClass(URINS+"Dogs");
//		OntClass Cats=model.createClass(URINS+"Cats");
//		Animal.addSubClass(Dogs);
//		Animal.addSubClass(Cats);
//		Dogs.addDisjointWith(Cats);
//		
//		OntClass people=model.createClass(URINS+"people");
//		ObjectProperty hasdog=model.createObjectProperty(URINS+"hasdog");
//		ObjectProperty hasPet=model.createObjectProperty(URINS+"haspet");
//		hasdog.addSuperProperty(hasPet);
//		hasPet.addSubProperty(hasdog);
//		hasPet.addDomain(people);
//		hasPet.addRange(Animal);
//		
//		Individual dog1=model.createIndividual(URINS+"charis",Dogs);
		System.out.println(model);
		model.write(System.out);
		
//		System.out.println(model.getOntClass(URINS+"Animals").getURI());
		model.close();
//		savetoMysql(model);
	}
}
