
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         // complete method
         FileResource fr = new FileResource(filename);
         for (String line: fr.lines()){
             LogEntry le = WebLogParser.parseEntry(line);
             records.add(le);
         }
         
     }
     
     public int countUniqueIPs(){
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for (LogEntry le: records){
             String ipAddr = le.getIpAddress();
             if( !uniqueIPs.contains(ipAddr)){
                 uniqueIPs.add(ipAddr);
             }
         }
         return uniqueIPs.size();
     }
     
     public void printAllHigherThanNum(int num){
         int entries = 0;
         for (LogEntry le: records){
            int code = le.getStatusCode();
            if (code > num){
                System.out.println("Status code higher than" + num + ":"+ le);
            }
         }
     }
     
      public ArrayList<String> uniqueIPVisitsOnDay(String someday){
    	 
    	 ArrayList<String> ips = new ArrayList<String>();
    	 
    	 for (LogEntry le : records) {
    		 String date = le.getAccessTime().toString();
    		 String ip = le.getIpAddress();
    		 if(date.indexOf(someday)!=-1) 
    			 if (!ips.contains(ip)){ 
    			     ips.add(ip);
    			 };
    	 }
    	 
    	 return ips;
    	 
     }
     
     public int countUniqueIPsInRange(int low, int high){
         ArrayList<String> uniques = new ArrayList<String>();
         for(LogEntry le: records){
             int code = le.getStatusCode();
             if(code >= low && code <= high) {
                 if (!uniques.contains(le.getIpAddress())){
                     uniques.add(le.getIpAddress());
                } 
             }
         }
         return uniques.size();
     }
     
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     
}
