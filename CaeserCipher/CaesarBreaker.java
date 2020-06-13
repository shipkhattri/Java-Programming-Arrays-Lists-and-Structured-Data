
/**
 * Write a description of CaesarBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;

public class CaesarBreaker {
    public String halfOfString(String message, int start){
        String half="";
        for(int i=start;i<message.length();i+=2){
            half = half + message.charAt(i);
        }
        return half;
    }
    public int[] countLetters(String message){
        int[] counts = new int[26];
        String alphabet ="abcdefghijklmnopqrstuvwxyz";
        for(int i=0;i<message.length();i++){
            char ch=Character.toLowerCase(message.charAt(i));
            int index = alphabet.indexOf(ch);
            //System.out.println(index);
            if (index != -1) {
                counts[index] += 1;
            }
        }
        return counts;
    }
    public int maxIndex(int[] values){
        int max=values[0],indx=0;
        for(int k=0;k<values.length;k++){
            if(max<values[k]){
                max=values[k];
                indx=k;
            }
        }
        return indx;
    }
    public int getKey(String s){
        int[] freqs = countLetters(s);
        int maxIndex = maxIndex(freqs);
        int key = maxIndex-4;
        if(maxIndex<4)
            key = 26-(4-maxIndex);
        
        return key;
    }
    public String decryptTwoKeys(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        String message1 = halfOfString(encrypted,0);
        String message2 = halfOfString(encrypted,1);
        
        int key1 = getKey(message1);
        int key2 = getKey(message2);
        
        System.out.println("Key1: "+key1+" Key2: "+key2);
        String decrypted = cc.encryptTwoKeys(encrypted,26-key1,26-key2);
        
        return decrypted;
    }
    public String decrypt(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        int key = getKey(encrypted);
        System.out.println("Key: "+key);
        String decrypted = cc.encrypt(encrypted,key);
        return decrypted;
    }
    public void testDecrypt(){
        
        FileResource resource = new FileResource();
        String message = resource.asString();
        String decrypted = decrypt(message);
        System.out.println(decrypted);
    }
    public void testDecryptTwoKeys(){
       FileResource resource = new FileResource();
       String message = resource.asString();
       
       //String message="Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";
       String d_TwoKeyMessage = decryptTwoKeys(message);
       
       System.out.println(message);
       System.out.println(d_TwoKeyMessage);
    }
}
