import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


public class Analysis {
	public static void main(String[] args){
		File file = new File("bupt.ntriple-com");
		
		Analysis t = new Analysis();
		t.propertyAnalysis(file);
		t.classAnalysis(file);
	}
	public void classAnalysis(File file){
		Set<String> set = new HashSet<String>();
		try {
			BufferedReader bfw = new BufferedReader(new FileReader(file));
			String line;
			while((line=bfw.readLine())!=null){
				String[] lineSplit = line.split(" ");
				if(lineSplit[1].equals("rdf.type")){
					set.add(lineSplit[2]);
				}
			}
			bfw.close();
			System.out.println("class size : "+set.size());
			for(String property:set){
				System.out.println(property);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void propertyAnalysis(File file){
		Set<String> set = new HashSet<String>();
		try {
			BufferedReader bfw = new BufferedReader(new FileReader(file));
			String line;
			while((line=bfw.readLine())!=null){
				String[] lineSplit = line.split(" ");
				set.add(lineSplit[1]);
			}
			bfw.close();
			System.out.println("property size : "+set.size());
			for(String property:set){
				System.out.println(property);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
