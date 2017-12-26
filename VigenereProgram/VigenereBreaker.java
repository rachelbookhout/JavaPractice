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

    public void breakVigenere () {
        String fr = new FileResource().asString();
        int[] length = tryKeyLength(fr,5,'e');
        VigenereCipher cip = new VigenereCipher(length);
        String decrypted = cip.decrypt(fr);
        System.out.println(decrypted);
    }
    
}
