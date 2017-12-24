import edu.duke.*;

public class CaesarCipher {
    public String encrypt(String input, int key) {
        //Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);
        //Write down the alphabet
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        //Compute the shifted alphabet
        String shiftedAlphabet = alphabet.substring(key)+
        alphabet.substring(0,key);
        //Count from 0 to < length of encrypted, (call it i)
        for(int i = 0; i < encrypted.length(); i++) {
            //Look at the ith character of encrypted (call it currChar)
            char currChar = encrypted.charAt(i);
            //Find the index of currChar in the alphabet (call it idx)
            int idx = alphabet.indexOf(Character.toUpperCase(currChar));
            //If currChar is in the alphabet
            if(idx != -1){
                //Get the idxth character of shiftedAlphabet (newChar)
                char newChar = shiftedAlphabet.charAt(idx);
                //Replace the ith character of encrypted with newChar

                if (Character.isLowerCase(currChar)){
                    newChar=Character.toLowerCase(newChar);
                }
                else{
                    newChar=Character.toUpperCase(newChar);
                } 
                    encrypted.setCharAt(i, newChar);
               

            }
            //Otherwise: do nothing
        }
        //Your answer is the String inside of encrypted
        return encrypted.toString();
    }
    
    public String encryptTwoKeys(String input, int key1, int key2){
               //Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);
        //Write down the alphabet
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        //Compute the shifted alphabet
        String firstShift = alphabet.substring(key1)+ alphabet.substring(0,key1);
        String secondShift = alphabet.substring(key2)+ alphabet.substring(0,key2);
        //Count from 0 to < length of encrypted, (call it i)
        for(int i = 0; i < encrypted.length(); i++) {
            //Look at the ith character of encrypted (call it currChar)
            char currChar = encrypted.charAt(i);
            char newChar;
            //Find the index of currChar in the alphabet (call it idx)
            int idx = alphabet.indexOf(Character.toUpperCase(currChar));
            //If currChar is in the alphabet
            if(idx != -1){
                //Get the idxth character of shiftedAlphabet (newChar)
               
                if( i % 2 == 0) {
                   newChar = firstShift.charAt(idx);
                }
                else {
                   newChar = secondShift.charAt(idx);
                }
                

                //Replace the ith character of encrypted with newChar

                if (Character.isLowerCase(currChar)){
                    newChar=Character.toLowerCase(newChar);
                }
                else{
                    newChar=Character.toUpperCase(newChar);
                } 
                    encrypted.setCharAt(i, newChar);
               

            }
            //Otherwise: do nothing
        }
        return encrypted.toString();
    }
    
 
    public void testCaesar() {
        int key = 15;
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println(encrypted);
        String decrypted = encrypt(encrypted, 26-key);
        System.out.println(decrypted);
        String twiceEncrypted = encryptTwoKeys(message,key, key+1);
        System.out.println(twiceEncrypted);
    }
}

