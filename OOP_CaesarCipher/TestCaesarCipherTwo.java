
/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class TestCaesarCipherTwo {
    private String halfOfString(String message, int start){
        String half="";
        for(int i=start;i<message.length();i+=2){
            half = half + message.charAt(i);
        }
        return half;
    }
    private int[] countLetters(String message){
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
    private int maxIndex(int[] values){
        int max=values[0],indx=0;
        for(int k=0;k<values.length;k++){
            if(max<values[k]){
                max=values[k];
                indx=k;
            }
        }
        return indx;
    }
    private int getKey(String s){
        int[] freqs = countLetters(s);
        int maxIndex = maxIndex(freqs);
        int key = maxIndex-4;
        if(maxIndex<4)
            key = 26-(4-maxIndex);
        
        return key;
    }
    private String breakCaesarCipher(String encrypted){
        String message1 = halfOfString(encrypted,0);
        String message2 = halfOfString(encrypted,1);
        
        int key1 = getKey(message1);
        int key2 = getKey(message2);
        CaesarCipherTwo cc1 = new CaesarCipherTwo(26-key1,26-key2);
       
        //System.out.println("Key1: "+key1+" Key2: "+key2);
        String decrypted = cc1.encryptTwoKeys(encrypted);
        return decrypted;
    }
    public void simpleTests(){
       FileResource f = new FileResource();
       String message = f.asString();
       CaesarCipherTwo cc = new CaesarCipherTwo(17,3);
       String EncryptedTwo = cc.encryptTwoKeys(message);
       System.out.println("Encrypted message = " + EncryptedTwo);
       String DecryptedTwo = breakCaesarCipher(EncryptedTwo);
       System.out.println("Decrypted message = " + DecryptedTwo);
    }
}
