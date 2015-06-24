package query;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class TestQuery {

	private String sparqlString;

	public TestQuery(String sparqlString) {
		this.sparqlString = sparqlString;
	}

	public static void main(String[] args) {
		String input = "/home/tseg/likeran/hbase/Univ-Bench/";
//		String input = "E:\\eclipse_workspace\\rdfJena\\Univ-Bench/";
		
//		args=new String[]{"1"};
		if(args.length==0){
			System.out.println("Usage: Univ-Number");
			return;
		}
		int univNum = Integer.parseInt(args[0]);
		String[] sparql = new String[14];
		sparql[0] = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
				+ "PREFIX ub: <http://swat.cse.lehigh.edu/onto/univ-bench.owl#>"
				+ "SELECT ?X "
				+ "WHERE"
				+ "{"
				+ "?X rdf:type ub:GraduateStudent . "
				+ "?X ub:takesCourse <http://www.Department0.University0.edu/GraduateCourse0> ."
				+ "}";
		sparql[1] = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
				+ "PREFIX ub: <http://swat.cse.lehigh.edu/onto/univ-bench.owl#>"
				+ "SELECT ?X ?Y ?Z "
				+ "WHERE"
				+ "{"
				+ "?X rdf:type ub:GraduateStudent . "
				+ "?Y rdf:type ub:University ."
				+ "?Z rdf:type ub:Department ."
				+ "?X ub:memberOf ?Z ."
				+ "?Z ub:subOrganizationOf ?Y ."
				+ "?X ub:undergraduateDegreeFrom ?Y"
				+ "}";
		sparql[2] = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
				+ "PREFIX ub: <http://swat.cse.lehigh.edu/onto/univ-bench.owl#>"
				+ "SELECT ?X "
				+ "WHERE"
				+ "{"
				+ "?X rdf:type ub:Publication . "
				+ "?X ub:publicationAuthor <http://www.Department0.University0.edu/AssistantProfessor0> ."
				+ "}";
		sparql[3] = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
				+ "PREFIX ub: <http://swat.cse.lehigh.edu/onto/univ-bench.owl#>"
				+ "SELECT ?X ?Y1 ?Y2 ?Y3 "
				+ "WHERE"
				+ "{"
				+ "?X rdf:type ?P . "
				+ "?X ub:worksFor <http://www.Department1.University0.edu> ."
				+ "?X ub:name ?Y1 ."
				+ "?X ub:emailAddress ?Y2 ."
				+ "?X ub:telephone ?Y3 ."
				+ "FILTER(?P = ub:Professor||?P = ub:FullProfessor||?P = ub:AssociateProfessor||?P = ub:AssistantProfessor||?P = ub:Chair||?P = ub:Dean)"
				+ "}";
		sparql[4] = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
				+ "PREFIX ub: <http://swat.cse.lehigh.edu/onto/univ-bench.owl#>"
				+ "SELECT ?X "
				+ "WHERE"
				+ "{"
//				+ "?X rdf:type ub:Person . "
				+ "?X ?M <http://www.Department0.University0.edu> ."
				+ "FILTER ( ?M = ub:worksFor || ?M = ub:memberOf || ?M = ub:headOf ) "
				+ "}";
		sparql[5] = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
				+ "PREFIX ub: <http://swat.cse.lehigh.edu/onto/univ-bench.owl#>"
				+ "SELECT ?X "
				+ "WHERE"
				+ "{"
				+ "?X rdf:type ?S . "
				+ "FILTER ( ?S = ub:Student || ?S = ub:GraduateStudent || ?S = ub:UnderGraduateStudent ) "
				+ "}";
		sparql[6] = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
				+ "PREFIX ub: <http://swat.cse.lehigh.edu/onto/univ-bench.owl#>"
				+ "SELECT ?X ?Y"
				+ "WHERE"
				+ "{"
				+ "?X rdf:type ?S . "
				+ "?Y rdf:type ?C ."
				+ "?X ub:takesCourse ?Y ."
				+ "<http://www.Department0.University0.edu/AssociateProfessor0> ub:teacherOf ?Y ."
				+ "FILTER ( ?C = ub:course || ?C = ub:GraduateCourse ) "
				+ "FILTER ( ?S = ub:Student || ?S = ub:GraduateStudent || ?S = ub:UnderGraduateStudent ) "
				+ "}";
		sparql[7] = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
				+ "PREFIX ub: <http://swat.cse.lehigh.edu/onto/univ-bench.owl#>"
				+ "SELECT ?X ?Y ?Z"
				+ "WHERE"
				+ "{"
				+ "?X rdf:type ?S . "
				+ "?Y rdf:type ub:Department ."
				+ "?X ?M ?Y ."
				+ "?Y ub:subOrganizationOf <http://www.University0.edu> ."
				+ "?X ub:emailAddress ?Z ."
				+ "FILTER ( ?M = ub:worksFor || ?M = ub:memberOf || ?M = ub:headOf ) "
				+ "FILTER ( ?S = ub:Student || ?S = ub:GraduateStudent || ?S = ub:UnderGraduateStudent ) "
				+ "}";
		sparql[8] = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
				+ "PREFIX ub: <http://swat.cse.lehigh.edu/onto/univ-bench.owl#>"
				+ "SELECT ?X ?Y ?Z"
				+ "WHERE"
				+ "{"
				+ "?X rdf:type ?S . "
				+ "?Y rdf:type ?P ."
				+ "?Z rdf:type ?C ."
				+ "?X ub:advisor ?Y ."
				+ "?Y ub:teacherOf ?Z ."
				+ "?X ub:takesCourse ?Z ."
				+ "FILTER(?P = ub:Faculty ||?P = ub:PostDoc ||?P = ub:Lecturer ||?P = ub:Professor||?P = ub:FullProfessor||?P = ub:AssociateProfessor||?P = ub:AssistantProfessor||?P = ub:Chair||?P = ub:Dean)"
				+ "FILTER ( ?C = ub:course || ?C = ub:GraduateCourse ) "
				+ "FILTER ( ?S = ub:Student || ?S = ub:GraduateStudent || ?S = ub:UnderGraduateStudent ) "
				+ "}";
		sparql[9] = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
				+ "PREFIX ub: <http://swat.cse.lehigh.edu/onto/univ-bench.owl#>"
				+ "SELECT ?X "
				+ "WHERE"
				+ "{"
				+ "?X rdf:type ?S . "
				+ "?X ub:takesCourse <http://www.Department0.University0.edu/GraduateCourse0>"
				+ "FILTER ( ?S = ub:Student || ?S = ub:GraduateStudent || ?S = ub:UnderGraduateStudent ) "
				+ "}";
		Model model = ModelFactory.createOntologyModel();
		long loadStart = System.currentTimeMillis();
		for(int i=0;i<univNum;i++){
			initModel(model,new File(input+"University"+i));
		}
		System.out.println("load finished in "+(System.currentTimeMillis()-loadStart)+" ms");
		
		TestQuery tq = new TestQuery(sparql[0]);
		tq.sparqlquery(model,0);
		
		int left = 0;
		int right = 10;
		if(args.length == 2){
			right = Integer.parseInt(args[1]);
			left = right - 1;
		}
		for(int i=left;i<right;i++){
			tq = new TestQuery(sparql[i]);
			
			System.out.println("query "+(i+1)+" start");
			long start = System.currentTimeMillis();
			tq.sparqlquery(model,i);
			System.out.println("query "+(i+1)+" finished in "+(System.currentTimeMillis()-start)+" ms");
//			long start1 = System.currentTimeMillis();
//			tq.sparqlquery(model,i);
//			System.out.println("query "+(i+1)+" finished in "+(System.currentTimeMillis()-start1)+" ms");
//			long start2 = System.currentTimeMillis();
//			tq.sparqlquery(model,i);
//			System.out.println("query "+(i+1)+" finished in "+(System.currentTimeMillis()-start2)+" ms");
		}
	}

	private static void export(ResultSet rs, String outFile) {
		try {
			BufferedWriter bfw = new BufferedWriter(new FileWriter(new File(outFile)));
			for(;rs.hasNext();){
				bfw.write(rs.next()+"\n");
			}
			bfw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ResultSet sparqlquery(Model model, int i) {
		Query query = QueryFactory.create(sparqlString);
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		ResultSet results = qe.execSelect();
		export(results, "qout-"+(i+1)+"-jena");
		qe.close();
		return results;
	}

	private static void initModel(Model model, File file) {
		InputStream in;
		try {
			if(file.isFile()){
				in = new FileInputStream(file);
				model.read(in, null, "N-TRIPLE");
				in.close();		
			}
			else{
				for(File childFile:file.listFiles()){
					initModel(model, childFile);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
