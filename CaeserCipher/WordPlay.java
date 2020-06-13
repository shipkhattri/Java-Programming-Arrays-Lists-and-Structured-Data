
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordPlay {
    public boolean isVowel(char ch){
        if(ch=='a' || ch=='e' || ch=='i' || ch=='o' || ch=='u'
        || ch=='A' || ch=='E' || ch=='I' || ch=='O' || ch=='U')
        {   
            return true;
        }    
        return false;
    }
    public String replaceVowels(String phrase, char ch){
        StringBuilder sb = new StringBuilder(phrase);
        for(int i=0;i<phrase.length();i++){
            char newCh=phrase.charAt(i);
            if(isVowel(newCh)){
                sb.setCharAt(i,ch);
            }
        }
        return sb.toString();
    }
    public String emphasize(String phrase, char ch){
        StringBuilder sb = new StringBuilder(phrase);
        
        for(int i=0;i<phrase.length();i++){
            char newCh=phrase.charAt(i);
            if(newCh==ch){
                if(i%2==0)
                    sb.setCharAt(i,'*');
                else
                    sb.setCharAt(i,'+');
            }
        }
        return sb.toString();
    }
    public void test(){
        //System.out.println(replaceVowels("Hello World",'*'));
        System.out.println(emphasize("dna ctgaaactga",'a'));
    }
}
