package rdfJena;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.vocabulary.VCARD;


public class Test {
	public static void main(String[] args){
		String tsegURI = "http://bupt/ComputerScience/tseg#";
		String wubin_name = "wubin";
		String likeran_name = "likeran";
		
		Model model = ModelFactory.createDefaultModel();
		
		model.setNsPrefix("TSEG", tsegURI);
		
		Resource wubin = model.createResource(tsegURI+wubin_name);
		wubin.addProperty(VCARD.FN, wubin_name);
		Resource likeran = model.createResource(tsegURI+likeran_name);
		likeran.addProperty(VCARD.FN, likeran_name);
		likeran.addProperty(VCARD.FN, "lkr");
		likeran.addProperty(VCARD.FN, "lowry");
		Property p = model.createProperty(tsegURI, "is_friend");
		wubin.addProperty(p, likeran);
		
		Resource r = model.getResource(tsegURI+likeran_name);
		System.out.println(r);
		
		Property pp = r.getProperty(VCARD.FN).getPredicate();
		System.out.println(pp);
		
//		System.out.println(r.getProperty(VCARD.FN).getObject());
		StmtIterator itr = r.listProperties();
		while(itr.hasNext())
			System.out.println(itr.next().getObject());
		
		ResIterator ritr = model.listSubjectsWithProperty(VCARD.FN,"lowry");
		while(ritr.hasNext())
			System.out.println(ritr.next().getURI());
//		System.out.println(r.getProperty(VCARD.FN).getResource());
		
		System.out.println("1----");
		model.write(System.out);
		System.out.println("2----");
		model.write(System.out, "RDF/XML-ABBREV");
		System.out.println("3----");
		model.write(System.out, "N-TRIPLES");
//		
	}
}
