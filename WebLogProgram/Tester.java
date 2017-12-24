
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer lz = new LogAnalyzer();
        lz.readFile("short-test_log");
        lz.printAll();
        int uniqueIPs = lz.countUniqueIPs();
        System.out.println("Unique IPs:" + uniqueIPs);
        lz.printAllHigherThanNum(200);
    }
    
    public void testUniqueIPOnDay(){
    	LogAnalyzer lg = new LogAnalyzer();     
        lg.readFile("short-test_log");
        System.out.println(lg.uniqueIPVisitsOnDay("Sep 30").size());
     	System.out.println(lg.countUniqueIPsInRange(200,299));   
    }
}
