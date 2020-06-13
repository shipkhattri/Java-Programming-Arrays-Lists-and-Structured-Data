
/**
 * Write a description of TestCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class TestCaesarCipher {
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
        int key = getKey(encrypted);
        CaesarCipher cc1 = new CaesarCipher(key);
        System.out.println("Key: "+key);
        String decrypted = cc1.encrypt(encrypted);
        return decrypted;
    }
    public void simpleTests(){
        //FileResource fr = new FileResource();
        //String message = fr.asString();
        CaesarCipher cc = new CaesarCipher(18);
        //String encrypted = cc.encrypt(message);
        //System.out.println("Encrypted Message: "+encrypted);
        //String decrypted = breakCaesarCipher(encrypted);
        //System.out.println("Decrypted Message: "+decrypted);
        System.out.println(cc);
    }
}
