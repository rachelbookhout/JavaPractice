
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
 import edu.duke.*;
public class WordLengths {
    public void countWordLengths(FileResource resource, int counts[]){
        for (String word : resource.words()) {
            StringBuilder sb = new StringBuilder(word);
            if (!Character.isLetter(sb.charAt(0))){
                sb.deleteCharAt(0);
            }
            if (!Character.isLetter(sb.charAt(sb.length() - 1))){
                sb.deleteCharAt(sb.length()-1);
            }
            counts[sb.length()] ++;
        }
        
        // should read all words from resource and count the number of words
        // of each length for all the words in resource, storing those counts in array 
    }
    
    public int indexOfMax(int[] values){
		int max=0;
		for(int i=0; i< values.length;i++){
			if(values[i]>max){
				max = values[i];
			}  
		}    
		return max;
		}
    
    public void testCountWordLength(){
        FileResource resource = new FileResource();
	int[] counts = new int[31];
	countWordLengths(resource, counts);
	System.out.println(counts[indexOfMax(counts)]);
    }
    
    
}
