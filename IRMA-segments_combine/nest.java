import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.PrintWriter;

public class nest {
	public static void main(String[] args) throws FileNotFoundException{
		
		File RSV1 = new File(args[0]); // read the RSV1 alignment file
		Scanner scnr1 = new Scanner(RSV1);

		List<String> seq1 = new ArrayList<String>();
		List<String> taxa1 = new ArrayList<String>();
		

		while(scnr1.hasNextLine()) {
			String line = scnr1.nextLine().trim();
			
			if (line.charAt(0)=='>') {
				taxa1.add(line);
			}
			else {
				seq1.add(line);
			
			}
						
		}
		
		int seq_size = seq1.size()/taxa1.size(); // This is alignment file, we should have the same number for single sequence				
		String[] sequence1 = new String[taxa1.size()];
		
		for(int i=0;i<sequence1.length;i++) {
			StringBuilder sb= new StringBuilder();
			int n= seq_size*i;
			for(int j=0; j<seq_size;j++) {
				sb.append(seq1.get(n+j));
			}
			String sequence= sb.toString().toUpperCase();
			sequence1[i]= sequence;
		
		}
		
		

		
		
		// get the RSV1 gene start and end region using references sequences
		//the 2nd sequence is the wo-SHGF reference and the 3rd is the WGS reference
		int start1=0;
		int end1=0;
        for (int i=0;i< sequence1[1].length();i++) {
    	   if (sequence1[1].charAt(i) !=sequence1[2].charAt(i) && sequence1[1].charAt(i)=='-') {
    		   start1=i;
    		   break;
    	   }
       }
	
        for (int i= sequence1[1].length()-1; i>0; i--) {
    	   if (sequence1[1].charAt(i) !=sequence1[2].charAt(i) && sequence1[1].charAt(i)=='-') {
    		  end1=i;
    		   break;
    	   }
       }
        
		//System.out.println(start1 + "\t" + end1);
        // cut the first sequence ( assembly consensus) from the alignment file
        
      String RSV1bf= sequence1[0].substring(0,start1);
      String RSV1af= sequence1[0].substring(end1+1,sequence1[1].length()-1);
      

      
      
      
      
     // RSV-SHGF region processed, inter-genetic overlap is going to be removed
      File RSV2 = new File(args[1]); // read the RSV1 alignment file
		Scanner scnr2 = new Scanner(RSV2);	
		List<String> seq2 = new ArrayList<String>();
		List<String> taxa2 = new ArrayList<String>();
		


		while(scnr2.hasNextLine()) {
			String line = scnr2.nextLine().trim();
			
			if (line.charAt(0)=='>') {
				taxa2.add(line);
			}
			else {
				seq2.add(line);
			
			}
						
		}
		
		int seq_size2 = seq2.size()/taxa2.size(); // This is alignment file, we should have the same number for single sequence				
		String[] sequence2 = new String[taxa2.size()];
		
		for(int i=0;i<sequence2.length;i++) {
			StringBuilder sb= new StringBuilder();
			int n= seq_size2*i;
			for(int j=0; j<seq_size2;j++) {
				sb.append(seq2.get(n+j));
			}
			String sequence= sb.toString().toUpperCase();
			sequence2[i]= sequence;
		
		}
		
		
		int start2=0;
		int end2=0;
        for (int i=0;i< sequence2[1].length();i++) {
    	   if (sequence2[1].charAt(i) !='-') {
    		   start2=i;
    		   break;
    	   }
       }
		
        for (int i= sequence2[1].length()-1; i>0; i--) {
    	   if (sequence2[1].charAt(i) != '-') {
    		  end2=i;
    		   break;
    	   }
       }
        
		//System.out.println(start2 + "\t" + end2);
		
		String SHGF = sequence2[0].substring(start2,end2+1);
		//System.out.println(SHGF);
		
		
		// combine substring together
		
		String WGS= RSV1bf+ SHGF + RSV1af;
		
		
		// remove the gaps
		String WGSn= WGS.replace("-", "");
		String taxa= taxa1.get(0).replace("_RSV1", "");
		String id = taxa.replace(">", "");
		String gp = taxa2.get(2).replace(">", "");
		int len = WGSn.length();
		
		PrintWriter outFile = new PrintWriter(id +".fasta");
		outFile.println(taxa);
		outFile.println(WGSn);
		
		System.out.println(id + "\t" + gp +"\t" + len );

     
      
      
      
      
      
      
      
      
        
        
        
        

		
		
		
		scnr1.close();
		scnr2.close();
		outFile.close();

	}
	

}
