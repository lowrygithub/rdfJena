import java.io.File;


public class BasicInfo {

	private String inputPath;
	long rdfTripleNum;
	long diskSpace;
	public BasicInfo(String inputPath) {
		this.inputPath = inputPath;
		this.rdfTripleNum = 0;
		this.diskSpace = 0;
	}
	public static void main(String[] args){
		args=new String[]{"E:\\eclipse_workspace\\rdfJena\\Univ-Bench-com\\"};
		BasicInfo bi = new BasicInfo(args[0]);
		bi.Statistics();
	}
	public void Statistics(){
		for(int i=0;i<600;i++){
			File university = new File(inputPath+"University"+i);
			for(File file:university.listFiles()){
				diskSpace+=file.length();
			}
			if(i==0||i==19||i==99||i==299||i==599){
				System.out.println("Univ Num: "+(i+1)+", Disk Space:"+this.diskSpace);
			}
		}
	}
	
}
