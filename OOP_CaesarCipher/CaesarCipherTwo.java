
/**
 * Write a description of CaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipherTwo {
    private int key1,key2;
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    public CaesarCipherTwo(int key1, int key2){
        key1 = key1;
        key2 = key2;
        alphabet="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet1= alphabet.substring(key1)+alphabet.substring(0,key1);
        shiftedAlphabet2= alphabet.substring(key2)+alphabet.substring(0,key2);
    }
    public String encryptTwoKeys(String input){
        
        StringBuilder encrypted = new StringBuilder(input);
        String alphaLower= "abcdefghijklmnopqrstuvwxyz";
        String shiftLower1 = shiftedAlphabet1.toLowerCase();
        String shiftLower2 = shiftedAlphabet2.toLowerCase();
        int indx;
        for(int i=0; i<encrypted.length(); i++){
            char ch = encrypted.charAt(i);
            if(i%2==0)
            {
                if(Character.isUpperCase(ch)){
                indx = alphabet.indexOf(ch);
                if(indx != -1){
                    char newCh = shiftedAlphabet1.charAt(indx);
                    encrypted.setCharAt(i,newCh);
                }   
                }
                if(Character.isLowerCase(ch)){
                    indx = alphaLower.indexOf(ch);
                    if(indx != -1){
                        char newCh = shiftLower1.charAt(indx);
                        encrypted.setCharAt(i,newCh);
                    }
                }
            }
            else{
                if(Character.isUpperCase(ch)){
                indx = alphabet.indexOf(ch);
                if(indx != -1){
                    char newCh = shiftedAlphabet2.charAt(indx);
                    encrypted.setCharAt(i,newCh);
                }
            }
            if(Character.isLowerCase(ch)){
                indx = alphaLower.indexOf(ch);
                if(indx != -1){
                    char newCh = shiftLower2.charAt(indx);
                    encrypted.setCharAt(i,newCh);
                }
            }
           
        }
        }
        return encrypted.toString();
    }
    
}
