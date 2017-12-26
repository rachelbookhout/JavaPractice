import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder slicedStr = new StringBuilder("");
        StringBuilder str = new StringBuilder(message);
        for (int i = whichSlice; i < message.length(); i+= totalSlices){
            char curChar = message.charAt(i);
            slicedStr.append(curChar);
            
        }
        return slicedStr.toString();
    }
    
    public String sliceStringTester(){
      String string = sliceString("abcdefghijklm", 0, 3);
      return string;
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
       int[] key = new int[klength];
       CaesarCracker cc = new CaesarCracker(mostCommon);
        
       for (int i=0; i< klength; i++) {
        	String slice = sliceString(encrypted, i, klength);
        	key[i] = cc.getKey(slice);
       }
        
       return key;
    }
    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> dict = new HashSet<String>();
        for (String word: fr.words()){
         dict.add(word.toLowerCase());
        }
        return dict;
    }
    
    public int countWords(String encrypted, HashSet<String> dictionary){
        String[] words = encrypted.toLowerCase().split("\\W+");
        int count = 0;
        for(String word: words){
            if(dictionary.contains(word)){
                count++;
            }
        }
        return count;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary){
    	HashMap<int[], Integer> keys = new HashMap<int[], Integer>();
    	System.out.println(encrypted);
        for(int i= 1; i < 100; i++){
            int[] key = tryKeyLength(encrypted, i, 'e');
            VigenereCipher cip = new VigenereCipher(key);
            String decrypted = cip.decrypt(encrypted);
            int count = countWords(decrypted, dictionary);
            keys.put(key, count);
        }
        int max = 0;
    	int[] foundKey = null;
    	
    	for (int[] key : keys.keySet()) {
    	    if (max < keys.get(key)) {
    		max = keys.get(key);
    		foundKey = key;
            }
    	}
    	System.out.println(foundKey);
    	VigenereCipher vc = new VigenereCipher(foundKey);
    	System.out.println("Decrypted word count:" + countWords(vc.decrypt(encrypted), dictionary));
    	return vc.decrypt(encrypted);
    }
    public void breakVigenere () {
        String fr = new FileResource().asString();
        FileResource dictionary =  new FileResource();
        HashSet<String> dict =  readDictionary(dictionary);
        String decrypted = breakForLanguage(fr,dict);
        System.out.println(decrypted);
    }
    
   
    
}
