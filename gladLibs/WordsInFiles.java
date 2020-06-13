
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import java.io.*;
import edu.duke.*;

public class WordsInFiles {
    private HashMap<String,ArrayList<String>> map;
    public WordsInFiles(){
        map = new HashMap<String,ArrayList<String>>();
    }
    public void addWordsFromFile(File f){
        FileResource fr=new FileResource(f);
        String fname=f.getName();
        for(String s:fr.words()){
            if(map.containsKey(s)){
                if(!map.get(s).contains(fname))
                    map.get(s).add(fname);
            }
            else{
                map.put(s,new ArrayList<String>());
                map.get(s).add(fname);
            }
        }
    }
    public void buildWordFileMap(){
        map.clear();
        DirectoryResource dr=new DirectoryResource();
        for(File f:dr.selectedFiles()){
            addWordsFromFile(f);
        }
    }
    public int maxNumber(){
        int max=0;
        int count;
        for(String s:map.keySet()){
            count=map.get(s).size();
            if(max<count)
                max=count;
        }
        return max;
    }
    public ArrayList<String> wordsInNumFiles(int number){
        ArrayList<String> a=new ArrayList<String>();
        int count;
        for(String s:map.keySet()){
            count=map.get(s).size();
            if(count==number)
                a.add(s);
        }
        return a;
    }
    public void printFilesIn(String word){
        if(map.containsKey(word)){
            for(int i=0;i<map.get(word).size();i++){
                System.out.println(map.get(word).get(i));
                }
        }
    }
    public void tester(){
        buildWordFileMap();
        //System.out.println("Maximum num of files: "+maxNumber());
        //int num=maxNumber();
        //ArrayList<String> a=wordsInNumFiles(4);
        //System.out.println("Words that occur in five files: "+a.size());
        System.out.println("tree Word: "+map.get("sea"));
        /*System.out.println("All the words that are in max num of files: ");
        for(int i=0;i<a.size();i++){
            System.out.println(a.get(i));
        }*/
    }
}
