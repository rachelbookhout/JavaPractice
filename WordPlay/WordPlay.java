import java.lang.*;



/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordPlay {
    
// Write a method isVowel that has one Char parameter named ch. 
//This method returns true if ch is a vowel (one of 'a', 'e', 'i', 'o', or 'u' or the uppercase versions) 
// and false otherwise.
public boolean isVowel (char ch){
   char cha = Character.toLowerCase(ch);
    if(cha == 'a' || cha == 'e' || cha == 'i' || cha == 'o' || cha == 'u'){
       return true;
    }
   else{
       return false;
    }
}    
//Write a method replaceVowels that has two parameters, a String named phrase and a Char named ch. 
//This method should return a String that is the string phrase with all the vowels (uppercase or lowercase) replaced by ch.
public String replaceVowels (String s, char ch){
    StringBuilder string = new StringBuilder(s);
    for (int i = 0; i < string.length(); i++){
        if(isVowel(string.charAt(i))){
            string.deleteCharAt(i);
            string.insert(i, ch);
        }
    }
    return string.toString();
}

 public void testReplaceVowels() {
        String string = replaceVowels("hello", '*');
        System.out.println(string);
 }
//Write a method emphasize with two parameters, a String named phrase and a character named ch. This method should return a String that is the string phrase but with the Char ch (upper- or lowercase) replaced by
//‘*’ if it is in an odd number location in the string (e.g. the first character has an odd number location but an even index, it is at index 0), or

//‘+’ if it is in an even number location in the string (e.g. the second character has an even number location but an odd index, it is at index 1).
}
