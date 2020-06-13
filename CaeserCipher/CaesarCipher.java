
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class CaesarCipher {
    public String encrypt(String input, int key){
        
        StringBuilder encrypted = new StringBuilder(input);
        String Ualphabet= "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String Lalphabet= "abcdefghijklmnopqrstuvwxyz";
        String shiftedUAlphabet = Ualphabet.substring(key)+Ualphabet.substring(0,key);
        String shiftedLAlphabet = Lalphabet.substring(key)+Lalphabet.substring(0,key);
        int indx;
        for(int i=0; i<encrypted.length(); i++){
            char ch = encrypted.charAt(i);
            if(Character.isUpperCase(ch)){
                indx = Ualphabet.indexOf(ch);
                if(indx != -1){
                    char newCh = shiftedUAlphabet.charAt(indx);
                    encrypted.setCharAt(i,newCh);
                }
            }
            if(Character.isLowerCase(ch)){
                indx = Lalphabet.indexOf(ch);
                if(indx != -1){
                    char newCh = shiftedLAlphabet.charAt(indx);
                    encrypted.setCharAt(i,newCh);
                }
            }
        }
        return encrypted.toString();
    }
    public String encryptTwoKeys(String input, int key1, int key2){
        
        StringBuilder encrypted = new StringBuilder(input);
        String Ualphabet= "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String Lalphabet= "abcdefghijklmnopqrstuvwxyz";
        String shiftedUAlphabet1 = Ualphabet.substring(key1)+Ualphabet.substring(0,key1);
        String shiftedUAlphabet2 = Ualphabet.substring(key2)+Ualphabet.substring(0,key2);
        String shiftedLAlphabet1 = Lalphabet.substring(key1)+Lalphabet.substring(0,key1);
        String shiftedLAlphabet2 = Lalphabet.substring(key2)+Lalphabet.substring(0,key2);
        int indx;
        char newCh=' ';
        for(int i=0; i<encrypted.length(); i++){
            char ch = encrypted.charAt(i);
            if(i%2==0)
            {
                if(Character.isUpperCase(ch)){
                indx = Ualphabet.indexOf(ch);
                if(indx != -1){
                    newCh = shiftedUAlphabet1.charAt(indx);
                }   
            }
            if(Character.isLowerCase(ch)){
                indx = Lalphabet.indexOf(ch);
                if(indx != -1){
                    newCh = shiftedLAlphabet1.charAt(indx);
                }
            }
            }
            else{
                if(Character.isUpperCase(ch)){
                indx = Ualphabet.indexOf(ch);
                if(indx != -1){
                    newCh = shiftedUAlphabet2.charAt(indx);
                }
            }
            if(Character.isLowerCase(ch)){
                indx = Lalphabet.indexOf(ch);
                if(indx != -1){
                    newCh = shiftedLAlphabet2.charAt(indx);
                }
            }
           
        }
        if(Character.isLetter(ch))
            encrypted.setCharAt(i,newCh);
    }
    return encrypted.toString();
}
    public void test(){
        
        //FileResource fr = new FileResource();
        String message = "Hfs cpwewloj loks cd Hoto kyg Cyy.";
        String encrypted = encryptTwoKeys(message,12,2);
        System.out.println(encrypted);
        //String decrypted = encrypt(encrypted,26-key);
        //System.out.println(decrypted);
    }
}

