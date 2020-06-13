
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
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        //la.printAll();
        //System.out.println("There are "+la.countUniqueIps()+" IPs.");
        //la.printAllHigherThanNum(400);
        ArrayList<String> uniqIPVisits=la.uniqueIPVisitsOnDay("Sep 27");
        System.out.println(uniqIPVisits.size());
        System.out.println(la.countUniqueIPsInRange(200,299));
        
        HashMap<String,Integer> counts=la.countVisitsPerIP();
        //System.out.println("Counts visits per IP "+counts);
        
        int mostVisits=la.mostNumberVisitsByIP(counts);
        //System.out.println("Most Number of visits per IP "+mostVisits);
        /*
        ArrayList<String> maxIP=la.iPsMostVisits(counts);
        for(String s:maxIP){
            System.out.println(s);
        }
        
        HashMap<String,ArrayList<String>> daysIps=la.iPsForDays();
        System.out.println(la.dayWithMostIPVisits(daysIps));
        ArrayList<String> a=la.iPsWithMostVisitsOnDay(daysIps,"Mar 17");
        System.out.println(daysIps.get("Sep 30"));
        */
    }
}
