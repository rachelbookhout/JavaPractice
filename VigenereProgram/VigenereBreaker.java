import java.util.*;
import edu.duke.*;
import java.io.*;


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
        char mostCommon = mostCommonCharIn(dictionary);
        System.out.println(encrypted);
        for(int i= 1; i < 100; i++){
            int[] key = tryKeyLength(encrypted, i, mostCommon);
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
    
    public char mostCommonCharIn(HashSet<String> dictionary){
        HashMap<Character,Integer> charCount = new HashMap<Character,Integer>();
        for(String word: dictionary){
            for(char c: word.toLowerCase().toCharArray()){
                if(!charCount.containsKey(c)){
                    charCount.put(c,1);
                }
                else{
                    charCount.put(c, charCount.get(c)+1);
                }
            }
        }
        int max = 0;
    	char mostCommon = 'a';
    	
    	for (char c : charCount.keySet()) {
    		
    		if (charCount.get(c) > max) {
    			max = charCount.get(c);
    			mostCommon = c;
    		}
    		
    	}
    
    	return mostCommon;
    }
    
    public String breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages){
        int max = 0;
        HashMap<String, Integer> langCount = new HashMap<String,Integer>();
        for (String language: languages.keySet()){
            String s = breakForLanguage(encrypted, languages.get(language));
            int i = countWords(s, languages.get(language));
            langCount.put(s, i);
        }

        String foundLang = "";
        
        for (String lang : langCount.keySet()) {
            if (max < langCount.get(lang)) {
                max = langCount.get(lang);
                foundLang = lang;
            }
        }
        return foundLang;
      
    }

    public void breakVigenere () {
        String fr = new FileResource().asString();
        //FileResource dictionary =  new FileResource();
        HashMap<String,HashSet<String>> langs = new HashMap<String,HashSet<String>>();
        DirectoryResource dr= new DirectoryResource();
        for(File f:dr.selectedFiles()){
            String fname = f.getName();
            FileResource fd= new FileResource(f);    
            HashSet<String> dict= readDictionary(fd);
            langs.put(fname,dict);
            System.out.println(fname);
        }
        //System.out.println(breakForLanguage(s,dict));
        String decrypted = breakForAllLangs(fr,langs);

        //HashSet<String> dict =  readDictionary(dictionary);
        
        //String decrypted = breakForLanguage(fr,dict);
        System.out.println(decrypted);
    } 
    
    
}

