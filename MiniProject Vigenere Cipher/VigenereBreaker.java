import java.util.*;
import edu.duke.*;
import java.io.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder sb = new StringBuilder();
        for(int i=whichSlice;i<message.length();i=i+totalSlices){
            sb.append(message.charAt(i));
        }
        return sb.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        for(int i=0;i<klength;i++){
            String s=sliceString(encrypted,i,klength);
            CaesarCracker cc=new CaesarCracker(mostCommon);
            int k=cc.getKey(s);
            key[i]=k;
        }
        return key;
    }
    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> dictionaryList = new HashSet<String>();
        for (String line : fr.lines()) {
            String lineLower = line.toLowerCase();
            dictionaryList.add(lineLower);
        }
        return dictionaryList;
    }
    public int countWords(String message, HashSet<String> dict){
        String[] messageSplit = message.split("\\W+");
        int commonWords = 0;
        for(int i=0; i < messageSplit.length; i++){
            String word = messageSplit[i].toLowerCase();
            if (dict.contains(word)){
                commonWords++;
            }
        }
        return commonWords;
    }
    public String breakForLanguage(String encrypted, HashSet<String> dict){
        String perfectDecrypted="";
        char ch=mostCommonCharIn(dict);
        int max=0,validKey=0;
        HashMap<Integer,Integer> map=new HashMap<Integer,Integer>();
        for(int k=1;k<=100;k++){
            int[] keys=tryKeyLength(encrypted,k,ch);
            VigenereCipher c=new VigenereCipher(keys);
            String decrypted=c.decrypt(encrypted);
            int count=countWords(decrypted,dict);
            if(max<count){
                max=count;
                perfectDecrypted=decrypted;
                validKey=k;
            }
        }
        System.out.println("Key: "+validKey+" Valid words: "+max);
        return perfectDecrypted;
    }
    public void breakVigenere () {
        //System.out.println(sliceString("abcdefghijklm",4,5));
        FileResource fr=new FileResource("messages/secretmessage2.txt");
        String message=fr.asString();
        FileResource d=new FileResource("dictionaries/English");
        HashSet<String> dict=readDictionary(d);
        String decrypted=breakForLanguage(message,dict);
        
        String line1 = decrypted.substring(0,decrypted.indexOf("\n"));
        System.out.println("Decrypted message: "+line1);
    }
    public void breakVigenere2() {
        HashMap<String,HashSet<String>> dictionaries=new HashMap<String,HashSet<String>>();
        DirectoryResource dr=new DirectoryResource();
        for(File f:dr.selectedFiles()){
            FileResource fr=new FileResource(f);
            HashSet<String> dict=readDictionary(fr);
            String fname=f.getName();
            dictionaries.put(fname,dict);
        }
        FileResource fp=new FileResource("messages/secretmessage4.txt");
        String message=fp.asString();
        breakAllLangs(message,dictionaries);
    }
    
    public char mostCommonCharIn(HashSet<String> dict){
        
        HashMap<Character,Integer> map=new HashMap<Character,Integer>();
        for(String s:dict){
            s=s.toLowerCase();
            for(int i=0;i<s.length();i++){
                char c=s.charAt(i);
                if(!map.containsKey(s.charAt(i)))
                    map.put(c,1);
                else
                    map.put(c,map.get(c)+1);
            }
        }
        int max=0;
        char mostCommon='a';
        for(char c:map.keySet()){
            if(map.get(c)>max){
                max=map.get(c);
                mostCommon=c;
            }
        }
        return mostCommon;
    }
    public void breakAllLangs(String encrypted, HashMap<String,HashSet<String>> languages){
        for(String s:languages.keySet()){
            HashSet<String> dict=languages.get(s);
            String decrypted=breakForLanguage(encrypted,dict);
        
            String line1 = decrypted.substring(0,decrypted.indexOf("\n"));
            System.out.println("Decrypted message: "+line1);
        }
    }
}
