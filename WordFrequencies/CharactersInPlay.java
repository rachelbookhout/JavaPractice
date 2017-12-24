
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.ArrayList;

public class CharactersInPlay {
    private ArrayList<String> characterNames;
    private ArrayList<Integer> characterLineCount;
    
    public CharactersInPlay (){
        characterNames = new ArrayList<String>();
        characterLineCount = new ArrayList<Integer>();
    }
    
    public void update(String person){
        // update two ArrayLists, add characters name if not already there
        // count the line as speaking part for thi s person
        int charIndex = characterNames.indexOf(person);
        if (charIndex == -1){
            characterNames.add(person);
            characterLineCount.add(1);
        }
        else {
            int freq = characterLineCount.get(charIndex);
            characterLineCount.set(charIndex,freq+1);
        }
    }
    
    public void findAllCharacters(){
        // opens file
        characterNames.clear();
        characterLineCount.clear();
        FileResource resource = new FileResource();
        // reads file line by line
        for(String s: resource.lines()){
           int index = s.indexOf('.');
           if (index != -1){
             String name = s.substring(0, index);
             update(name);
           }
        }
        // if period on the line, extract name of speaking part
        // then call update to count it as occurance
        // clear the file
       
    }
    
    public void charactersWithNumParts(int num1, int num2) {
        // num1 is less than or equal to nums2
        //print out characters with more than num1, less than 2
        for (int i = 0; i < characterNames.size(); i++){
            String s = characterNames.get(i);
            int n = characterLineCount.get(i);
            if(n >= num1 && n <= num2){
                System.out.println("Character:" + s + ": " + n + " Lines");
            }
        }
        // print out the main character
        // followed by the number of speaking parts for each character
    }
    
    
    public void tester() {
        findAllCharacters();
        charactersWithNumParts(2,1000000);        
    }
    
    
}
