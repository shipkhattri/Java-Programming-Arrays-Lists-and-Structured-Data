
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
         FileResource fr = new FileResource(filename);
         for(String line : fr.lines()){
             LogEntry l = WebLogParser.parseEntry(line);
             records.add(l); 
         }
     }
     public HashMap<String,Integer> countVisitsPerIP(){
         HashMap<String,Integer> counts = new HashMap<String,Integer>();
         for(LogEntry le:records){
             String ip=le.getIpAddress();
             if(!counts.containsKey(ip)){
                counts.put(ip,1);
             }
             else{
                counts.put(ip,counts.get(ip)+1);
             }
             
         }
         
         return counts;
     }
     public int countUniqueIps(){
        HashMap<String,Integer> counts =countVisitsPerIP();
        return counts.size();
     } 
     public int mostNumberVisitsByIP(HashMap<String,Integer> counts){
         int max=0;
         for(String s:counts.keySet()){
             if(counts.get(s)>max)
                max=counts.get(s);
         }
         return max;
     }
     public ArrayList<String> iPsMostVisits(HashMap<String,Integer> counts){
         ArrayList<String>maxIP = new ArrayList<String>();
         int max=mostNumberVisitsByIP(counts);
         for(String s:counts.keySet()){
             if(counts.get(s)==max)
                maxIP.add(s);
         }         
         return maxIP;
     }
     public HashMap<String,ArrayList<String>> iPsForDays(){
         HashMap<String,ArrayList<String>> daysIps = new HashMap<String,ArrayList<String>>();
         for(LogEntry le:records){
             ArrayList<String> date=new ArrayList<String>();
             String ip=le.getIpAddress();
             Date d=le.getAccessTime();
             String str=d.toString();
             str=str.substring(4,10);
             if(!daysIps.containsKey(str)){
                date.add(ip);
                daysIps.put(str,date);
             }
             else{
                date=daysIps.get(str);
                date.add(ip);
             }
             
         }
         return daysIps;
     }
     public String dayWithMostIPVisits(HashMap<String,ArrayList<String>> daysIps){
         int max=0;
         String day="";
         for(String s:daysIps.keySet()){
             int num=daysIps.get(s).size();
             if(num>max){
                max=num;
                day=s;
            }
         }
         return day;
     }
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String,
                ArrayList<String>> daysIps, String day){
         for (String s: daysIps.keySet()){
            if (s.contains(day)){
                return daysIps.get(s);
            }
        }
        return null;
     }
     public void printAllHigherThanNum(int num){
        System.out.println("Status Code greater than "+num+" are: "); 
         for(LogEntry le:records){
            if(le.getStatusCode()>num)
                System.out.println(le.getStatusCode());
        }
     }
     public ArrayList<String> uniqueIPVisitsOnDay(String someday){
         ArrayList<String> uniq = new ArrayList<String>();
         ArrayList<String> uniqIP = new ArrayList<String>();
         for(LogEntry le:records){
            String str = le.toString();
            if(str.contains(someday) && !uniqIP.contains(le.getIpAddress()) )
            {    uniq.add(str);
                 uniqIP.add(le.getIpAddress());
            }
        }
        return uniq;
     }
     public int countUniqueIPsInRange(int low, int high){
         ArrayList<LogEntry> uniq = new ArrayList<LogEntry>();
         ArrayList<String> uniqIP = new ArrayList<String>();
         for(LogEntry le:records)
         {
            int num=le.getStatusCode();
            if(num>=low && num<=high)
            {
                if(!uniqIP.contains(le.getIpAddress()))
                {   uniq.add(le);
                    uniqIP.add(le.getIpAddress());
                }
            }
        }
        return uniq.size();
     }
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     
}
