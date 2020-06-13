
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class CaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
    public CaesarCipher(int key){
        mainKey=key;
        alphabet="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet= alphabet.substring(key)+alphabet.substring(0,key);
    }
    public String encrypt(String input){
        
        StringBuilder encrypted = new StringBuilder(input);
        String alphaLower = alphabet.toLowerCase();
        String shiftLower = shiftedAlphabet.toLowerCase();
        int indx;
        for(int i=0; i<encrypted.length(); i++){
            char ch = encrypted.charAt(i);
            if(Character.isLowerCase(ch)){
                indx = alphaLower.indexOf(ch);
                if(indx != -1){
                char newCh = shiftLower.charAt(indx);
                encrypted.setCharAt(i,newCh);
                }    
            }
            else{
                indx = alphabet.indexOf(ch);
                if(indx != -1){
                char newCh = shiftedAlphabet.charAt(indx);
                encrypted.setCharAt(i,newCh);
                } 
            }
        }
        return encrypted.toString();
    }
    public String decrypt(String encrypted){
        CaesarCipher cc = new CaesarCipher(26-mainKey);
        String decrypted = cc.encrypt(encrypted);
        return decrypted;
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
        for(int i=0; i<encrypted.length(); i++){
            char ch = encrypted.charAt(i);
            if(i%2==0)
            {
                if(Character.isUpperCase(ch)){
                indx = Ualphabet.indexOf(ch);
                if(indx != -1){
                    char newCh = shiftedUAlphabet1.charAt(indx);
                    encrypted.setCharAt(i,newCh);
                }   
            }
            if(Character.isLowerCase(ch)){
                indx = Lalphabet.indexOf(ch);
                if(indx != -1){
                    char newCh = shiftedLAlphabet1.charAt(indx);
                    encrypted.setCharAt(i,newCh);
                }
            }
            }
            else{
                if(Character.isUpperCase(ch)){
                indx = Ualphabet.indexOf(ch);
                if(indx != -1){
                    char newCh = shiftedUAlphabet2.charAt(indx);
                    encrypted.setCharAt(i,newCh);
                }
            }
            if(Character.isLowerCase(ch)){
                indx = Lalphabet.indexOf(ch);
                if(indx != -1){
                    char newCh = shiftedLAlphabet2.charAt(indx);
                    encrypted.setCharAt(i,newCh);
                }
            }
           
        }
    }
    return encrypted.toString();
}
    public void test(){
        
        //FileResource fr = new FileResource();
        //String message = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        //String encrypted = encryptTwoKeys(message,8,21);
        //System.out.println(encrypted);
        CaesarCipher cc = new CaesarCipher(18);
        System.out.println(cc);
        //String decrypted = encrypt(encrypted,26-key);
        //System.out.println(decrypted);
    }
}
