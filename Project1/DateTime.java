/*************************************************************************
 *  Compilation:  javac DateTime.java <br>
 *  Execution:    java DateTime <br>
 *************************************************************************/

/**
 * A simple data type DateTime to be fleshed out for Project 1 (CSE 1325, Spring 2014)
 * <br>
 * Useful for creating DateTime objects (a la DATETIME type supported in Oracle)
 * 
 * @author skeleton developed by Sharma Chakravarhty 
 * @author To be completed by the student
 * @see java.util.Calendar
 * 
 */

public class DateTime implements DateConstants {

 static private final int VALID_STRING = 21;
 static private final int DATE_INDEC = 0;
 static private final int TIME_INDEX = 1;
 static private final int MONTHS_INYEAR = 12;
 static private final int HOURS_INDAY = 24;
 
 private Date date; // from Date Class
 private Time time; // from Time class

 
/**
  * Constructor: default; returns today's date and time
  */
    public DateTime() {
        date = new Date();
        time = new Time();
    }

 /**
  * Constructor: Does bounds-checking to ensure object represents a valid
  * date and time
  * 
  * @param m    represents the month between 1 and 12
  * @param d    represents the date between 1 and the corresponding number
  *             from array DAYS
  * @param y    represents the year
  * @exception RuntimeException
  *                if the date is invalid
  */
 public DateTime(int mo, int d, int y, int h, int mi, int s, int hun) {
  
    date= new Date(mo,d,y);
    time= new Time(h,mi,s,hun);
    
 }

 /**
  * Constructor: Does bounds-checking to ensure string represents a valid
  * date
  * 
  * @param sDate    represents a date given in format mm-dd-yyyy,hh:mm:ss:hund as a string
  * @exception RuntimeException if the date is invalid
  */
 public DateTime(String sDateTime) {
    
   String [] splitResult= sDateTime.split(",");
   date= new Date(splitResult[0]);
   time= new Time(splitResult[1]);   
 }

    
 // is this DateTime after b?
 /**
  * compares two DateTime objects
  * 
  * @param b DateTime object
  * @return true if this DateTime is after DateTime b
  */
 public boolean isAfter(DateTime b) {
 
   return compareTo(b)>0;
 }

 // is this DateTime a before b?
 /**
  * compares two date objects
  * 
  * @param b DateTime object
  * @return true if this DateTime is before DateTime b
  */
 public boolean isBefore(DateTime b) {
   
   return compareTo(b)<0;
 }


 // comparison function between two dates
 /**
  * compares two DateTime objects
  * 
  * @param b    DateTime object
  * @return     0 if this DateTime is the same as DateTime b <br>
  *             negative integer if this DateTime is earlier than DateTime b <br>
  *             positive integer if this DateTime is after DateTime b
  */
 public int compareTo(DateTime b) {

  if(date.isBefore(b.date))
    return date.compareTo(b.date);
  
    return time.compareTo(b.time);
 }

 
 // advance DateTime by days
 /**
  * advances the datetime by days
  * 
  * @param d    represents the  days to advance
  * @return     modifies the same DateTime object)
  */
 
 public DateTime addDays(int d){
   for(int i=0;i<d;i++)    
   date.next();
      
      return this; 
 }
 
  // advance DateTime by months
 /**
  * advances the datetime by montsh
  * 
  * @param m    represents the  monthss to advance
  * @return     modifies the same DateTime object)
  */
 public DateTime addMonths(int m){
     
  date.addMonths(m);
   return this;
 }
 
  // advance DateTime by years
 /**
  * advances the datetime by years
  * 
  * @param y    represents the  years to advance
  * @return     modifies the same DateTime object)
  */
 public DateTime addYears(int y){
   
  date.addMonths(y*MONTHS_INYEAR);
   return this;
 }
 
  // advance DateTime by hours
 /**
  * advances the datetime by hours
  * 
  * @param h    represents the  hours to advance
  * @return     modifies the same DateTime object)
  */
 public DateTime addHours(int h){
   
  int daysOver=0;
  daysOver=time.addHours(h);
  this.addDays(daysOver);
   
  return this;
 }
 
  // advance DateTime by minutes
 /**
  * advances the datetime by minutes
  * 
  * @param m    represents the  minutes to advance
  * @return     modifies the same DateTime object)
  */
 public DateTime addMinutes(int m){
   
   int daysOver=0;
   daysOver=time.addMinutes(m);
   this.addDays(daysOver);
   
   return this;
 }
 
  // advance DateTime by seconds
 /**
  * advances the datetime by seconds
  * 
  * @param s    represents the  seconds to advance
  * @return     modifies the same DateTime object)
  */
 public DateTime addSeconds(int s){
   
   int daysOver=0;
   daysOver=time.addSeconds(s);
   this.addDays(daysOver);
   
   return this;
 }

 // return a string representation of this date
 /**
  * replaces the default toString of Object class
     * @override
  */
 public String toString() {
      
      return this.date+ " " + this.time;  
 }
 
 //Add two DateTimes returning a DateTime
 /**
  * adds Date and Time and returns new DateTime object
  * 
  * @return returns a new DateTime adding Date and Time
  */
 public DateTime addDateTime(DateTime d2){
   
  return this; 
 }
 
 //Subtract two DateTimes returning a DateTime
 /**
  * subtracts Date and Time and returns new DateTime object
  * 
  * @return returns a new DateTime subtracting Date and Time
  */
 public DateTime subtractDateTime(DateTime dt2){
  
      return this;
 }
  
 

 /**
  * Code for testing the DateTime class
  * 
  * @param args Array of String arguments
  */

 public static void main(String[] args) {

    System.out.println("\n-------Testing Constructors:-------");
    
    System.out.println("Building Blank Constructor...");
    DateTime today = new DateTime();
    System.out.println("Today's date and time is: " + today);
    
    System.out.println("Building String Constructor...");    
    //Before todays date
    DateTime today1 = new DateTime("01-01-2012,01:01:03:00");
    
    System.out.println("Today's date and time is: " + today1);
    
    System.out.println("Building Integer Constructor...");
    //After todays date
    DateTime today2 = new DateTime(03,01,2014,01,01,03,00);
    
    System.out.println("----------Testing comapre()------------\n");
    
    System.out.println("Is "+today+" before "+today1 +today.isBefore(today1));
    
    System.out.println("Is "+today+ "after "+today1 +today.isAfter(today1));
    
    System.out.println("If "+today+ "is after  "+today1+" than it should be positive "+today.compareTo(today1));
    
    System.out.println("If "+today+ "is earlier than "+today2+" than it should be negative "+today.compareTo(today2));
    
    System.out.println("-----------Testing addDays---------\n");
    System.out.println("Adding 1 day to: "+today1);
    today1=today1.addDays(1);
    System.out.println("New today 1: "+today1);
    System.out.println("Adding 3 more days to today1");
    today1.addDays(3); 
    System.out.println("New today 1: "+today1);
    System.out.println("Adding 100 more days to today1");
    today1.addDays(100);
    
    System.out.println("-----------Testing addMonths---------\n");
    System.out.println("New today 1: "+today1);
    System.out.println("Adding 1 month to today1");
    today1.addMonths(1);
    System.out.println("New today 1: "+today1);
    System.out.println("Adding 3 month to today1");
    today1.addMonths(3);
    System.out.println("New today 1: "+today1);
    System.out.println("Adding 17 Months");
    today1.addMonths(17);
    System.out.println("New today 1: "+today1);
    
    System.out.println("-----------Testing addYears-------\n");
    
    System.out.println("New today 1: "+today1);
    System.out.println("Adding 1 year to today1:");
    today1.addYears(1);
    System.out.println("New today 1: "+today1);
    System.out.println("adding 6 years to today1");
    today1.addYears(6);
    System.out.println("New today 1: "+today1);
    
    System.out.println("------------Hours Test-------------");
    
    
    System.out.println("Adding 26hours to today1");
    today1.addHours(26);
    System.out.println("New today 1: "+today1+"\n");
    
    System.out.println("Adding 24hours to today1");
    today1.addHours(24);
    System.out.println("New today 1: "+today1+"\n");
    
    System.out.println("Adding 23hours to today1");
    today1.addHours(23);
    System.out.println("New today 1: "+today1+"\n");
    
    System.out.println("Adding 2200 hours to today1");
    today1.addHours(2200);
    System.out.println("New today 1: "+today1+"\n");    
    
    System.out.println("------------Minutes Test---------\n");
    
    System.out.println("Adding 1minute to today1");
    today1.addMinutes(1);
    System.out.println("New today 1: "+today1+"\n");
    
    System.out.println("Adding 60minutes to today1");
    today1.addMinutes(60);
    System.out.println("New today 1: "+today1+"\n");
    
    System.out.println("Adding 13000 minute to today1");
    today1.addMinutes(13000);
    System.out.println("New today 1: "+today1+"\n");
    
    System.out.println("----------Testing isBefore()-----------\n");
    
    System.out.println("Is "+today+" before "+today1+" ? should be true result: "+today.isBefore(today1));
    System.out.println("Is "+today1+" before "+today+" ? should be false result: "+today1.isBefore(today));
    System.out.println("Is "+today1+" before "+today1+" ? should be false result: "+today1.isBefore(today1));
    
    System.out.println("----------Testing isAfter()------------\n");
    
    System.out.println("Is "+today+" after "+today1+" ? should be false result: "+today.isAfter(today1));
    System.out.println("Is "+today1+" after "+today+" ? should be true result: "+today1.isAfter(today));
    
   System.out.println("------------Running given test cases--------------");
   DateTime today001 = new DateTime();
   System.out.println("Testing no arg constructor: "+today001);
   
   System.out.println("\n");
   System.out.println("-------- Testing hours-minutes-seconds 1--------\n");
   DateTime dateTime1 = new DateTime("2-28-2016,23:59:59:99"); 
   System.out.println("testing string as input:" + dateTime1);
   System.out.println("Adding 26 hrs to"+dateTime1+" gives "+dateTime1.addHours(26));
   
   System.out.println("\n");
   System.out.println("-------- Testing hours-minutes-seconds 2--------");     
   DateTime dateTime2 = new DateTime("2-27-2017,23:59:00:00");
   System.out.println("Adding 24hrs to "+dateTime2+" gives "+dateTime2.addHours(24));
   System.out.println("Adding 7 min to "+dateTime2+ "gives "+dateTime2.addMinutes(7));
   
   System.out.println("\n");
   System.out.println("-------- Testing hours-minutes-seconds 3--------");
   dateTime2 = new DateTime("12-31-2013,11:30:00:00");
   System.out.println("Adding 13 hrs to "+dateTime2+" gives "+dateTime2.addHours(13));
   System.out.println("Adding 31 min to "+dateTime2+ "gives "+dateTime2.addMinutes(31));
   
   System.out.println("\n");
   System.out.println("-------- Testing hours-minutes-seconds 4--------");
   dateTime2 = new DateTime("1-1-2012,23:59:00:00");
   System.out.println("Adding 86400 sec to "+dateTime2+" gives "+dateTime2.addSeconds(86400));
   System.out.println("Adding 3600 sec to "+dateTime2+ "gives "+dateTime2.addSeconds(3600));
   
   System.out.println("\n");
   System.out.println("-------- Testing hours-minutes-seconds 5--------");        
   DateTime dateTime3 = new DateTime("1-11-2014,23:59:57:00");
   System.out.println("Adding 4 sec to "+dateTime3+" gives "+dateTime3.addSeconds(4));
   
   System.out.println("\n");
   System.out.println("-------- Testing hours-minutes-seconds 6--------");
   dateTime1 = new DateTime(2,28,2015,12,30,30,0);
   System.out.println("testing 7 arg constructor with initial date: "+dateTime1);
   System.out.println("Increasing day by 366 "+dateTime1.addDays(366));
   System.out.println("Increasing  month by 12 "+dateTime1.addMonths(12));
   System.out.println("Increasing  year by 2 "+dateTime1.addYears(2));
   
   System.out.println("\n");
   System.out.println("-------- Testing hours-minutes-seconds 7--------");
   dateTime1 = new DateTime(2,28,2016,12,30,30,0);
   System.out.println("Initial date is "+dateTime1);
   System.out.println("Increasing day by 365 "+dateTime1.addDays(365));
   System.out.println("Increasing  month by 11 "+dateTime1.addMonths(11));
   System.out.println("Increasing  year by 30 "+dateTime1.addYears(30));
        
   dateTime2 = new DateTime(12,31,2013,12,45,30,0);
   dateTime3 = new DateTime(12,31,2013,12,45,29,99);
   System.out.println("\n");
   System.out.println("--------------Test 1 isBefore isAfter ------------------");     
   System.out.println("checking "+dateTime2+" is before "+dateTime3+" gives "+dateTime2.isBefore(dateTime3));
   System.out.println("checking "+dateTime2+" is after "+dateTime3+" gives "+dateTime2.isAfter(dateTime3)); 
   System.out.println("checking "+dateTime3+" is before "+dateTime2+" gives "+dateTime3.isBefore(dateTime2));
   System.out.println("checking "+dateTime3+" is after "+dateTime2+" gives "+dateTime3.isAfter(dateTime2)); 
        
   System.out.println("\n");    
   System.out.println("--------------Test 2 isBefore isAfter ------------------");   
   dateTime2 = new DateTime(12,30,2013,12,45,30,0);
   dateTime3 = new DateTime(12,31,2013,12,45,29,99);           
   System.out.println("checking "+dateTime2+" is before "+dateTime3+" gives "+dateTime2.isBefore(dateTime3));
   System.out.println("checking "+dateTime2+" is after "+dateTime3+" gives "+dateTime2.isAfter(dateTime3));
   
   System.out.println("\n");
   System.out.println("--------------Test 3 isBefore isAfter ------------------"); 
   dateTime2 = new DateTime(12,31,2012,12,45,30,0);
   dateTime3 = new DateTime(8,31,2013,12,45,29,99);         
   System.out.println("checking "+dateTime2+" is before "+dateTime3+" gives "+dateTime2.isBefore(dateTime3));
   System.out.println("checking "+dateTime2+" is after "+dateTime3+" gives "+dateTime2.isAfter(dateTime3));
   
   System.out.println("\n");
   System.out.println("--------------Test 4 isBefore isAfter ------------------");
   dateTime2 = new DateTime(12,31,2013,11,45,29,99);
   dateTime3 = new DateTime(12,31,2013,12,45,29,99);        
   System.out.println("checking "+dateTime2+" is before "+dateTime3+" gives "+dateTime2.isBefore(dateTime3));
   System.out.println("checking "+dateTime2+" is after "+dateTime3+" gives "+dateTime2.isAfter(dateTime3));
   
   System.out.println("\n");
   System.out.println("--------------Test 5 isBefore isAfter ------------------");
   dateTime2 = new DateTime(12,31,2013,12,44,59,0);
   dateTime3 = new DateTime(12,31,2013,12,45,0,0);        
   System.out.println("checking "+dateTime2+" is before "+dateTime3+" gives "+dateTime2.isBefore(dateTime3));
   System.out.println("checking "+dateTime2+" is after "+dateTime3+" gives "+dateTime2.isAfter(dateTime3));
        
   System.out.println("--------------Test 6 isBefore isAfter ------------------");
   dateTime2 = new DateTime(12,31,2013,12,45,29,98);
   dateTime3 = new DateTime(12,31,2013,12,45,29,99);        
   System.out.println("checking "+dateTime2+" is before "+dateTime3+" gives "+dateTime2.isBefore(dateTime3));
   System.out.println("checking "+dateTime2+" is after "+dateTime3+" gives "+dateTime2.isAfter(dateTime3));
        
   System.out.println("\n");
   System.out.println("--------------Test 7 isBefore isAfter ------------------");
   System.out.println("\n");
   dateTime2 = new DateTime(12,31,2013,13,50,30,0);
   dateTime3 = new DateTime("12-29-2014,12:45:29:0");        
   System.out.println("checking "+dateTime2+" is before "+dateTime3+" gives "+dateTime2.isBefore(dateTime3));
   System.out.println("checking "+dateTime2+" is after "+dateTime3+" gives "+dateTime2.isAfter(dateTime3));

        
   System.out.println("Comparing dateTimes now");
   
   System.out.println("\n");
   System.out.println("--------------Test 1 compareTo() ------------------");
   dateTime2 = new DateTime(12,31,2013,13,50,30,0);
   dateTime3 = new DateTime("12-29-2014,12:45:29:0");        
   System.out.println("Comparing "+dateTime2+" with "+dateTime3+" gives "+dateTime2.compareTo(dateTime3));
   
   System.out.println("\n");
   System.out.println("--------------Test 2 compareTo() ------------------");
   dateTime2 = new DateTime(12,31,2013,13,50,30,0);
   dateTime3 = new DateTime("12-29-2013,12:45:29:0");        
   System.out.println("Comparing "+dateTime2+" with "+dateTime3+" gives "+dateTime2.compareTo(dateTime3));
   
   System.out.println("\n");
   System.out.println("--------------Test 3 compareTo() ------------------");
   dateTime2 = new DateTime(12,29,2014,12,45,29,0);
   dateTime3 = new DateTime("12-29-2014,12:45:29:0");        
   System.out.println("Comparing "+dateTime2+" with "+dateTime3+" gives "+dateTime2.compareTo(dateTime3));
   
   System.out.println("\n");
   System.out.println("--------------Test 4 compareTo() ------------------");
   dateTime2 = new DateTime(12,29,2014,12,45,31,0);
   dateTime3 = new DateTime("12-29-2014,12:45:29:0");        
   System.out.println("Comparing "+dateTime2+" with "+dateTime3+" gives "+dateTime2.compareTo(dateTime3));
   
   System.out.println("\n");
   System.out.println("--------------Test 5 compareTo() ------------------");
   dateTime2 = new DateTime(12,29,2014,12,44,31,0);
   dateTime3 = new DateTime("12-29-2014,12:45:29:0");        
   System.out.println("Comparing "+dateTime2+" with "+dateTime3+" gives "+dateTime2.compareTo(dateTime3));
        
 }
}

