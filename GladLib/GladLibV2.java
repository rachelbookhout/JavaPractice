
import edu.duke.*;
import java.util.*;

public class GladLibV2 {
    private ArrayList<String> usedWords;
    private HashMap<String, ArrayList<String>> myMap;
    private Random myRandom;
    private ArrayList<String> catUsed;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "datalong";
    
    public GladLibV2(){
        myMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(dataSourceDirectory);
        usedWords = new ArrayList<String>();
        myRandom = new Random();
        catUsed = new ArrayList<String>();
    }
    
    public GladLibV2(String source){
        initializeFromSource(source);
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        String[] labels = {"country", "noun", "animal", "adjective", "name",
            "color", "timeframe", "verb", "fruit"};
        for (String s: labels){
            ArrayList<String> list = readIt(source + "/"+s +".txt");
            myMap.put(s,list);

        }
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        if(label.equals("number")){
            return ""+myRandom.nextInt(50) + 5;
        }
        return randomFrom(myMap.get(label));
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String cat = w.substring(first+1,last);
        if(catUsed.indexOf(cat) == -1){
            catUsed.add(cat);
        }
        String sub = getSubstitute(cat);
        // check usedWords ArrayList
        // if word is already there, don't add it
        boolean used = checkIfUsed(sub);
        while (used) {
            sub = getSubstitute(w.substring(first + 1, last));
            used = checkIfUsed(sub);
        }
        return prefix + sub + suffix;
    }
    
        private boolean checkIfUsed(String word) {
        int index = usedWords.indexOf(word);
        if (index == -1) {
            usedWords.add(word);
            return false;
        } else {
            return true;
        }
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    private int totalWordsinMap(){
        int i = 0;
        for(String s: myMap.keySet()){
            i += myMap.get(s).size();
        }
        return i;
    }
    
    private int totalWordsConsidered(){
        int i = 0;
        for(String s: catUsed){
            if( s.equals("number")) {
                System.out.println("Number");
                continue;
            }
            else{
               System.out.println(s); 
               i += myMap.get(s).size();
            }
        }
        return i;
    }
    public void makeStory(){
        usedWords.clear();
        System.out.println("\n");
        String story = fromTemplate("datalong/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("We used " + usedWords.size() + "words");
        System.out.println("Story used " + totalWordsinMap() + " and considered" + totalWordsConsidered());
    }
    


}
