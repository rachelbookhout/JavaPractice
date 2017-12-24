
/**
 * Write a description of CodonCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;

public class CodonCount {
    private HashMap<String, Integer> map;
    
    public CodonCount(){
        map = new HashMap<String, Integer>();
    }
    
    public void buildCodonMap(int start, String dna) {
        // clear out the map
        map.clear();
        // iterate through string starting at start
        // go three positions
        int times = (dna.length() -1)/3;
        for(int i = start, j = 0; j < times - 1 ;i+=3, j++){
            String word = dna.substring(i, i + 3);
            if(map.keySet().contains(word)){
                map.put(word,map.get(word)+1);
            }
            else{
                map.put(word,1);
            }
        }
    } 
    
    public String getMostCommonCodon(){
        int max = 0;
        String codon = "";
        for (String key: map.keySet()) {
           if(map.get(key) > max){
               max = map.get(key);
               codon = key;
           }
           // doesn't take in account multiple codons with the same number
        }
        return codon;
    }
    
    public void printCodonCounts(int start, int end) {
        for(String key: map.keySet()){
            if(map.get(key) >= start && map.get(key) <= end) {
                System.out.println(key + ": " + map.get(key));
            }
        }
    }
    public void printValues(){
        System.out.println(map);
        String codon = getMostCommonCodon();
        System.out.println(codon);
        printCodonCounts(0,3);
    }
    public void testBuildCondonMap(){
        FileResource fr = new FileResource();
        String dna = fr.asString().trim();
        dna = dna.toUpperCase();
        buildCodonMap(0,dna);
        printValues();
        buildCodonMap(1,dna);
        printValues();
        buildCodonMap(2,dna);
        printValues();
    }
}
    
