
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class WordLengths {
    public int countLength(String s){
        int l=0;
        char ch=s.charAt(0);
        if(Character.isLetter(ch))
                l++;
        ch=s.charAt(s.length()-1);
        if(Character.isLetter(ch))
                l++;
        for(int k=1;k<s.length()-1;k++){
            l++;
        }
        return l;
    }
    public void countWordLengths(FileResource resource, int counts[]){
        
        for(String s: resource.words()){
            int l=countLength(s);
            counts[l]+=1;
        }
        for(int i=1;i<counts.length;i++)
            if(counts[i]!=0)
                System.out.println(counts[i]+" words of length "+i);
                
        System.out.println("Common word length: "+indexOfMax(counts));
    } 
    public int indexOfMax(int values[]){
        int max=values[0],indx=0;
        for(int k=0;k<values.length;k++){
            if(max<values[k]){
                max=values[k];
                indx=k;
            }
        }
        return indx;
    }
    public void testCountWordLengths(){
        FileResource fr = new FileResource();
        int maxLength=0,l=0;
        for(String s: fr.words()){
            l=countLength(s);
            if(maxLength<l)
                maxLength=l;
        }
        int[] counts = new int[maxLength+1];
        countWordLengths(fr,counts);
        
        
    }
}
