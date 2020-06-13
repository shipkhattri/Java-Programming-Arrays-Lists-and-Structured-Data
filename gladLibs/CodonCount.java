
/**
 * Write a description of CodonCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
public class CodonCount {
    
    private HashMap<String,Integer>myMap;
    public CodonCount(){
        myMap = new HashMap<String,Integer>();
    }
    public void buildCodonMap(int start, String dna){
        myMap.clear();
        String sub;
        for(int i=0; i<(dna.length()-start)/3; i++){
            sub=dna.substring(start+i*3,start+i*3+3);
            if(myMap.containsKey(sub))
                myMap.put(sub,myMap.get(sub)+1);
            else
                myMap.put(sub,1);
        }
    }
    public String getMostCommonCodon(){
        String largestCodon="";
        int max=0;
        for(String s:myMap.keySet()){
            int count = myMap.get(s);
            if(count>max){
                largestCodon=s;
                max=count;
            }
        }    
        return largestCodon;
    }
    public void printCodonCounts(int start,int end){
        //System.out.println("Counts of codons between "+start+" and "+end+" inclusive are:");
        for(String s:myMap.keySet()){
            int count = myMap.get(s);
            if(count>=start && count<=end){
                System.out.println(s+" "+count);
            }
        }  
    }
    public void tester(){
        FileResource fr = new FileResource();
        String dna = fr.asString().trim();
        dna=dna.toUpperCase();
        
        int start=7,end=7;
        buildCodonMap(0, dna);
         System.out.println("Reading frame starting with 0 results in "+myMap.size()+" unique codons"+"\t");
         String largest = getMostCommonCodon();
         System.out.println("Most common codon is "+largest+" with count "+myMap.get(largest)+"\t"); 
         System.out.println("Counts of codons between "+start+" and "+end+" inclusive are:"+"\t");
         printCodonCounts(start, end);
         
         buildCodonMap(1, dna);
         System.out.println("Reading frame starting with 1 results in "+myMap.size()+" unique codons"+"\t");
         largest = getMostCommonCodon();
         System.out.println("Most common codon is "+largest+" with count "+myMap.get(largest)+"\t"); 
         System.out.println("Counts of codons between "+start+" and "+end+" inclusive are:"+"\t");
         printCodonCounts(start, end);
         
         buildCodonMap(2, dna);
         System.out.println("Reading frame starting with 2 results in "+myMap.size()+" unique codons"+"\t");
         largest = getMostCommonCodon();
         System.out.println("Most common codon is "+largest+" with count "+myMap.get(largest)+"\t"); 
         System.out.println("Counts of codons between "+start+" and "+end+" inclusive are:"+"\t");
         printCodonCounts(start, end);
    }
}
