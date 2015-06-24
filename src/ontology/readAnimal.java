package ontology;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.FileManager;

public class readAnimal {
	private static String URINS="http://lkrontology/Animal/";
	public static void main(String[] args) throws IOException{
		OntModel model=ModelFactory.createOntologyModel();
		InputStream in = FileManager.get().open("E:\\eclipse_workspace\\rdfJena\\src\\animal");
		model.read(in, null);
		in.close();
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
		System.out.println("----------------------");
		String uri=URINS+"Animals";
		System.out.println(model.getOntClass(uri));
		
//		model.createIndividual(URINS+"lkr", model.getOntClass(URINS+"people"));
		model.add(model.getResource(URINS+"lkr"), model.getProperty(URINS+"hasdog"), model.getResource(URINS+"charis"));
		model.write(System.out);
		Map<String, String> map =model.getNsPrefixMap();
		System.out.println("==============");
		for(String s:map.keySet()){
			System.out.println(s+":"+map.get(s));
		}
		System.out.println("==============");
		model.close();
//		savetoMysql(model);
	}
}
