/*************************************************************************
 *  Compilation:  javac Enterprise.java <br>
 *  Execution:    java Enterprise <br>
 *************************************************************************/

/**
 * A simple data type Enterprise for use in Project 2 (CSE 1325, Fall 2010)
 * <br>
 * Useful for creating Enterprise Objects  to hold Infromation of a Customers, employees
 * bids, name of enterprise, 
 * 
 * @author Scott Nidell 
 */

import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
public class Enterprise implements Proj2Constants, DateConstants{
  
 private String name;
 private ArrayList<Item> items;
 private ArrayList<Employee> employees;
 private ArrayList<Customer> customers;
 private NumberFormat dollars= NumberFormat.getCurrencyInstance(Locale.US);
 private enum SaleType{FIX_PRICE,AUCTION,BOTH};
 private int numEmployees=ZEROI;
 
 
 /**
  * Constructor: Creates a enterprise object that holds customers, employees, items, and bids
  * 
  * @param nm The name of the Enterprise
  */
 Enterprise(String nm){
   this.name=nm; 
   items=new ArrayList<Item> ();
   employees= new ArrayList<Employee>();
   customers= new ArrayList<Customer>();  
   
 }
 
 /**
  * Adds an employee to ArrayList
  * 
  * @param e Employee to be added
  */
 public void addEmployee(Employee e){
   
  employees.add(e);
  if(e.getReleaseDate()==null){
    numEmployees++;
  }
 }
 
 /**
  * Adds an item to the ArrayList
  * 
  * @param it Item to be added to the list
  */
 public void addItem(Item it){
  
   items.add(it);
 }  
  /**
  * Adds customers to ArrayList
  * 
  * @param c The Customer to be added
  */
 public void addCustomer(Customer c){
   
  customers.add(c); 
 }
 
 /**
  * Releases a employee if it is not already released or exists
  * 
  * @param eid Employees ID to be released
  */
 public void releaseEmployee(int eid){   
   int flag=0;
    for(int i=0;i<items.size();i++){
      
      if(employees.get(i).getEmpID()==eid){
        if(employees.get(i).getReleaseDate()==null){
          employees.get(i).releaseEmp();
          System.out.println("Employee has been found and released: "+employees.get(i));
          numEmployees--;
          return;
        }else{
         System.out.println("Employee Has already been released");
         System.out.println(employees.get(i));
         return;
        }
      }
    }      
      System.out.println("Employee ID not found");  
 }
   
 /**
  * Prints all Employees past and present
  * 
  */
 public void printEmployees(){
   
  for(int i=0;i<employees.size();i++)
    System.out.println(employees.get(i));
   
 }
 
 /**
  * Prints all Items of the Enterprise
  * 
  */
 public void printItems(){
  
   for(int i=0;i<items.size();i++)    
     System.out.println(items.get(i));
   }
 
 /**
  * Prints all items given a lower bound range
  * 
  * @param val the lower bound range value
  */
 public void printItemsRange(int val){
   
   for(int i=0;i<items.size();i++){ 
     if(val<=items.get(i).getReserveAmount())
     System.out.println(items.get(i).printByReserve());
   }   
 }
   
 /**
  * Prints all customers in the Enterprise
  *
  */
 public void printCustomers(){
    
     for(int i=0;i<customers.size();i++)
       System.out.println(customers.get(i));    
 }
 
 /**
  * Adds a bid to item in the list
  * 
  * @param bid Bid to be added to Item List
  */
 public void addBid(Bid bid){
    
    int flag=0;
    for(int i=0;i<items.size();i++){
      if(items.get(i).getItemID()==bid.getItemID()){
        items.get(i).addBid(bid);
        flag++;
        return;
      }      
    }   
    if(flag==0)
      System.out.println("Item ID not found");
  }
 
 /**
  * Prints total Fees taken in dollar format
  * 
  */
 public void totalFees(){
    double totalCost=0;
    
    for(int i=0;i<items.size();i++){
     totalCost+= items.get(i).checkSold();
    }
    String dollarCost=dollars.format(totalCost);
    System.out.println("Total Fees collected: "+dollarCost);
  }
 
 /**
  * Print all bids given an Item ID
  * 
  * @param id Item Id to be printed bids
  */
 public void printBids(int id){
   
    for(int i=0;i<items.size();i++){
      
      if(items.get(i).getItemID()==id){
        items.get(i).printBids();
        return;
      }
    }
  }
    
 /**
  * prints all Bids for all Items
  * 
  */ 
 public void printAllBids(){
   
    for(int i=0;i<items.size();i++){ 
      
        items.get(i).printBids();        
    }    
  }
 
 /**
  * Prints Main menu for User Interface
  *
  */
 public void printMenu(){
    System.out.println(this);
    System.out.println("Welcome to MavBay!");
    System.out.println("Main Menu:");
    System.out.println(  "1) List all employees");
    System.out.println(  "2) List items");
    System.out.println(  "3) List all customers");
    System.out.println(  "4) Display items sold");
    System.out.println(  "5) Display total fee collection");
    System.out.println(  "6) Display Items that received max bid");
    System.out.println(  "7) Release an employee");
    System.out.println(  "0) Exit Program");    
  }
  /**
  * Prints a submenu for items
  * 
  */
  public void itemsMenu(){
    
    Scanner scan= new Scanner(System.in);
    String menuScan=EMPTY_STRING;
    
    System.out.println("Which items do you want to Display?");
    System.out.println("Type:* for all items");
    System.out.println("Give an amount and auction equal to or over that amount will be printed");
    
    menuScan=scan.next();
    int itemValue;
    
    if(menuScan.equals("*")){
      System.out.println("Printing All Items...");  
      this.printItems();
                 
    }else{
        try {
          itemValue = Integer.parseInt(menuScan);
          printItemsRange(itemValue);
          
          
        }catch (Exception e){       
          this.itemsMenu();
        }
    }
  }
  /**
  * Prints the Max Number of bids and the Item with that max bid
  * 
  */
  public void maxBid(){
   int maxBid=ZEROI;
   int index=ZEROI;
    for(int i=0;i<items.size();i++){     
      if(items.get(i).getNumBids()>maxBid){
        maxBid=items.get(i).getNumBids();
        index=i;
      }
    } 
   System.out.println("Item with most amount of Bids: "+items.get(index));
   System.out.println("Number of Bids: "+maxBid);
  }
  
  /**
  * Sub Menu to release and Employee
  *
  */
  public void releaseEmpMenu(){
   
   Scanner scan= new Scanner(System.in);
   String menuScan=EMPTY_STRING;
   int empIDSelection=ZEROI;
   
   System.out.println("Please enter an Emplyee ID to release");
   menuScan=scan.next();
   try {
          empIDSelection = Integer.parseInt(menuScan);
          this.releaseEmployee(empIDSelection);
          
        }catch (Exception e){       
          System.out.println("Invalid input");
          this.releaseEmpMenu();
        }
    
  }
  
  /**
  * Prints the Items sold in the Enterprise
  *
  */
  public void soldItemsMenu(){
   
   Scanner scan= new Scanner(System.in);
   String menuScan=EMPTY_STRING;
   
   for(int i=0;i<items.size();i++){//time has eslapsed check to see if anything new has been sold
     items.get(i).checkSold(); 
   }
   System.out.println("To print All prices type: *");
   System.out.println("To print fixed amount prices type: FIX_PRICE");
   System.out.println("To print auction only items type: AUCTION");
   System.out.println("To print all sold items type: BOTH"); 
   menuScan=scan.next();
   
   if(menuScan.equals("*")){
     
     for(int i=0;i<items.size();i++){    
       if(this.items.get(i).getQtySold()>=ONEI){//if an item has been sold print it
         System.out.println(this.items.get(i));
       }
     }
     
   }else if(menuScan.toUpperCase().equals(FIXED)){
     for(int i=0;i<items.size();i++){    
       if(this.items.get(i).getQtySold()>=ONEI && this.items.get(i).getType().equals(FIXED)){
         //if the item has been sold and its a Fixed Price. Print it.
         System.out.println(this.items.get(i));
       }
     }
     
   }else if(menuScan.toUpperCase().equals(AUCTION)){
     for(int i=0;i<items.size();i++){    
       if(this.items.get(i).getQtySold()>=ONEI && this.items.get(i).getType().equals(AUCTION)){
       //if the item has been sold and its a Auction Price. Print it.
        System.out.println(items.get(i));
        }
      }     
   }else if(menuScan.toUpperCase().equals(BOTH_ITEM)){
     for(int i=0;i<items.size();i++){
       if(this.items.get(i).getQtySold()>=ONEI && this.items.get(i).getType().equals(BOTH_ITEM)){
         System.out.println(items.get(i));
       }       
     }    
   }else{
    System.out.println("Invalid selection");
    this.soldItemsMenu();
   }
  }
                         
  /**
  * A String method that represents a Enterprise Object
  * 
  * @return Returns a string of Enterprise Object
  */
  public String toString(){
    
    return "Enterprise: "+name+"Employees: "+numEmployees;     
  }
 
 
 public static void main(String [] args){

   /***************Create EMPLOYEES***************/ 
 Employee myEmp1= new Employee("WD,Bill,Compton,06-28-1981,male,08-15-2004,1000");
 Employee myEmp2= new Employee("WD,Scott,Nidell,08-28-1983,male,08-15-2002,4000"); 
 Employee myEmp3= new Employee("WD,Sookie,Stackhouse,01-26-1979,female,08-16-2006,900");
 Employee myEmp4= new Employee("WD,Jessica,Hamby,02-26-1976,female,01-11-2006,1900");
   
 
   
 int empID=002;
 String myEmpType="Acct";
 String firstName="Johnny";
 String lastName="Folizie";
 Date myDob= new Date("01-21-1991"); 
 String myMale= "MALe";
 Date hireDate= new Date("03-22-1998"); 
 String releaseDate= "null";
 double salary= 14000;
 
 Employee myEmp5= new Employee(firstName, lastName, myMale,hireDate, releaseDate, salary,myEmpType,myDob);
 
 /***********************Adding Employees to Enterprise****************/
 Enterprise myent= new Enterprise("Yo dog");  
 myent.addEmployee(myEmp1);
 myent.addEmployee(myEmp2);
 myent.addEmployee(myEmp3);
 myent.addEmployee(myEmp4);
 myent.addEmployee(myEmp5);
 myent.printEmployees();
 System.out.println(myent);
 
 /***********************Create Bids******************/
  int userID1=011;
  int itemID1=003;
  DateTime bidDate1= new DateTime("2-12-2014,00:00:45");
  double bidAmount1=200.00;
  int bidQTY1= 3;
  
  int userID2=012;
  int itemID2=120;
  DateTime bidDate2= new DateTime("2-13-2014,13:45:00");
  double bidAmount2=203.00;
  int bidQTY2= 3;
  
  int userID3=013;
  int itemID3=110;
  DateTime bidDate3= new DateTime("2-11-2014,11:00:00");
  double bidAmount3=900.00;
  int bidQTY3= 1;
  
  int userID4=014;
  int itemID4=190;
  DateTime bidDate4= new DateTime("2-11-2014,14:00:00");
  double bidAmount4=233.00;
  int bidQTY4= 9;
  
  Bid bid1= new Bid(userID1,itemID1,bidDate1,bidAmount1,bidQTY1);
  Bid bid2= new Bid(userID2,itemID2,bidDate2,bidAmount2,bidQTY2);
  Bid bid3= new Bid(userID3,itemID3,bidDate3,bidAmount3,bidQTY3);
  Bid bid4= new Bid(userID4,itemID4,bidDate4,bidAmount4,bidQTY4);
  
  /******************Create Items************************************/
  
    int itemID01= 003;
    String itemCat1= "Antique";
    String itName1="Camera";
    String itemType1= "FIX_PRICE";
    int itemQty1= 5;
    String condition1= "USED";
    double minStart1= 30.00;
    double bidInc1= 5.00;
    double reserveAmt1= 400.00;
    String startD1= "1-1-2014,21:49:00";
    int days1= 71;
    int sellerID1= 200;
    int feedback1=1000;
    String desc1= "Old Ass Camera"; 
    
    Item myItem1= new Item(itemID01,itemCat1,itName1,itemType1,itemQty1,condition1,minStart1,bidInc1,reserveAmt1,
                           startD1,days1,sellerID1,feedback1,desc1);
    
    
    int itemID02= 004;
    String itemCat2= "Antique";
    String itName2="Camera";
    String itemType2= "AUCTION";
    int itemQty2= 1;
    String condition2= "NEW";
    double minStart2= 35.00;
    double bidInc2= 5.00;
    double reserveAmt2= 450.00;
    String startD2= "1-5-2010,21:49:00";
    int days2= 2;
    int sellerID2= 200;
    int feedback2=1000;
    String desc2= "Old Ass Camera"; 
    
    Item myItem2= new Item(itemID02,itemCat2,itName2,itemType2,itemQty2,condition2,minStart2,bidInc2,reserveAmt2,
                           startD2,days2,sellerID2,feedback2,desc2);
   
   }
   
 
 
 
 
 
}