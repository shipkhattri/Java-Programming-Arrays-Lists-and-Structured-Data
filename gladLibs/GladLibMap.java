import edu.duke.*;
import java.util.*;

public class GladLibMap {
    private HashMap<String ,ArrayList<String>> map;
    ArrayList<String> trackList;
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLibMap(){
        map=new HashMap<String,ArrayList<String>>();
        trackList=new ArrayList<String>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }
    
    public GladLibMap(String source){
        initializeFromSource(source);
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        String[] labels={"country","noun","animal",
                    "adjective","name","color","timeframe","verb"};
        for(String s:labels){
            ArrayList<String> list = readIt(source+"/"+s+".txt");
            map.put(s,list);
        }
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        if (label.equals("number")){
                return ""+myRandom.nextInt(50)+5;
        }
        return randomFrom(map.get(label));
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        while(trackList.contains(sub))
            sub = getSubstitute(w.substring(first+1,last));
        
        trackList.add(sub);
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    public int totalWordsInMap(){
        int total=0;
        for(String s:map.keySet()){
            total=total+map.get(s).size();
        }
        return total;
    }
    public int totalWordsConsidered(){
        return trackList.size();
    }
    public void makeStory(){
        trackList.clear();
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate.txt");
        printOut(story, 60);
        System.out.println("Total no of words possible to pick from: "+totalWordsInMap());
        System.out.println("Total Words considered: "+totalWordsConsidered());
        System.out.println("\nReplaced words: "+trackList.size());
    } 
    


}
