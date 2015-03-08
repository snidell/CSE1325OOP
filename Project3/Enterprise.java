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
import java.util.Scanner;
public class Enterprise implements Proj3Constants, DateConstants{
  
 private String name; //name of enterprise
 private ArrayList<Item> items=new ArrayList<Item>(); //items currently up for auction
 private ArrayList<Item> soldItems=new ArrayList<Item>(); //items sold
 private ArrayList<SalariedEmp> employees= new ArrayList<SalariedEmp>(); //stores employees currently employed
 private ArrayList<SalariedEmp> releasedEmployees = new ArrayList<SalariedEmp>();//stores canned employees
 private ArrayList<Customer> customers; //stores customers of company
 private NumberFormat dollars= NumberFormat.getCurrencyInstance(Locale.US); //to make money pretty
 private enum SaleType{FIX_PRICE,AUCTION,BOTH};
 private int numEmployees=ZEROI; //current number of employees
 private int totalItemsSold=0;  //items sold by enterprise
 private double totalFees; //total fees collected 
 private double revenue;
 
 
 
 /**
  * Constructor: Creates a enterprise object that holds customers, employees, items, and bids
  * 
  * @param nm The name of the Enterprise
  */
 Enterprise(String nm){
   this.name=nm; 
   items=new ArrayList<Item> ();
   employees= new ArrayList<SalariedEmp>();
   customers= new ArrayList<Customer>();  
   
 }
 /**
  * Adds an employee to ArrayList
  * 
  * @param e Employee to be added
  */
 public void addEmployee(SalariedEmp e){
   
  
  if(e.getReleaseDate()==null){
    numEmployees++;
    employees.add(e);
  }else{
    System.out.println("Employee has a non-null release date, employee has been added releaseEmployees");
    releasedEmployees.add(e);
  }
 }
 
 /**
  * Adds an item to the ArrayList
  * 
  * @param it Item to be added to the list
  */
 public boolean addItem(Item it){
  
   for(int i=0;i<items.size();i++){
     if(it.getItemID()==items.get(i).getItemID()){
       System.out.println("ID currently in use choose a unique ID");
       return false;
     }
   }
   items.add(it);
   return true;   
 } 
 
 /**
  * Adds customers to ArrayList
  * 
  * @param c The Customer to be added
  */
 public void addCustomer(Customer c){
  
   //check for redundant ID
   for(int i=0;i<customers.size();i++){
     if(c.getID()==customers.get(i).getID()){
       System.out.println("ID currently in use choose a unique ID");       
     }
   }
   customers.add(c); 
 }
 
 /**
  * Releases a employee if it is not already released or exists
  * 
  * @param eid Employees ID to be released
  */
 public void releaseEmployee(int eid, String rdate){   
   int flag=0;
   SalariedEmp temp;
   //check active employees 
   
   for(int i=0;i<employees.size();i++){       
      if(employees.get(i).getID()==eid){
        if(employees.get(i).getReleaseDate()==null){
          employees.get(i).release(rdate);
          System.out.println("Employee has been found and released: "+employees.get(i));
          temp=employees.get(i);
          releasedEmployees.add(temp);//move this employee to releasedEmployees
          employees.remove(i);//remove from active employees
          numEmployees--;
          return;
        }                
      }      
    }
      //Check released Employees this person may have been released already
      for(int i=0;i<releasedEmployees.size();i++){
        if(releasedEmployees.get(i).getID()==eid){
        System.out.println("Employee Has already been released");
         System.out.println(releasedEmployees.get(i));
         return; 
        }
      }
      //Finally if its not in either let the user know
      System.out.println("Employee ID not found");  
 }
 /**
  * Prints the employee info with length of service
  * @param foutput PrintWriter to create new lines in file
  */
 public void printLenghtOfService(SalariedEmp e, PrintWriter foutput){
   foutput.println("------Newly Added Employee----------");
   foutput.println(e+" Age: "+e.age()+" Length of Service "+e.lengthOfService());
   foutput.println("\n");
 }
 
 /**
  * Prints the employee info with length of service
  * @param eid employee ID
  * @param foutput PrintWriter to create new lines in file
  */
 public void printLenghtOfService(int eid,PrintWriter foutput){
   if(eid==ZEROI){
    for(int i=0;i<employees.size();i++){
    foutput.println(employees.get(i)+" Age: "+employees.get(i).age()+" Length of Service "
                         +employees.get(i).lengthOfService());
    }
   }else{
    for(int i=0;i<employees.size();i++){
      if(eid==employees.get(i).getID()){
        foutput.println(employees.get(i)+" Age: "+employees.get(i).age()+" Length of Service "
                         +employees.get(i).lengthOfService());
      }
    }
   }
 }
 
 
 /**
  * Prints all current Employees
  * @param foutput PrintWriter to create new lines in file
  * 
  */
 public void printEmployees(PrintWriter foutput){
  foutput.println("\n"); 
   foutput.println("-----Employees currently employed"); 
  for(int i=0;i<employees.size();i++)
    foutput.println(employees.get(i));
   
 }
 
 /**
  * Prints all past Employees
  * @param foutput PrintWriter to create new lines in file
  * 
  */
 public void printPastEmployees(PrintWriter foutput){
  foutput.println("\n"); 
   foutput.println("----Employees who have been terminated"); 
  for(int i=0;i<releasedEmployees.size();i++)
    foutput.println(releasedEmployees.get(i));
   
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
  * Prints all items given a lower bound range cost amount
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
  * Given Seller ID print all items associated with
  * seller ID
  * 
  * @param id Item Id to be printed bids
  * @param foutput PrintWriter to create new lines in file
  */
 public void printSellerItems(Item it, PrintWriter foutput){
   int occurence=ZEROI;
   foutput.println("\n----------");
   foutput.println("Current Items being sold by Seller:"+ it.getSellerID());
   for(int i=0;i<items.size();i++){
     if(items.get(i).getSellerID()==it.getSellerID()){
       occurence++;
       foutput.println("  Item "+occurence+"| "+items.get(i));
     }
   }
   foutput.println("\n\n");
   if(occurence==ZEROI){
     foutput.println("No items found for seller: "+it.getSellerID());
   }
 }
 
 /**
  * Prints all customers in the Enterprise
  *@param foutput PrintWriter to create new lines in file
  */
 public void printCustomers(PrintWriter foutput){
    foutput.println("----Printing All Current Customers----");
     for(int i=0;i<customers.size();i++)
       foutput.println(customers.get(i));
     foutput.println("\n\n");
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
  * @param foutput Printwriter to print to file
  */
 public void printBids(int id, PrintWriter foutput){
   
    for(int i=0;i<items.size();i++){
      
      if(items.get(i).getItemID()==id){
        items.get(i).printBids(foutput);
        return;
      }
    }
  }
 
/**
  * Checks if an item was sold
  * if sold it moves item to itemsSold arraylist
  * 
  
  */
 
public void checkSold() {
  
  
//loop through items
  for(int i=0; i<items.size(); i++) {
    
   //if there is are no bids in item
    if(items.get(i).getBids().size() != 0){ 
      
    if("FIXED".equals(items.get(i).getType().toUpperCase())) { //if type is fixed
      
     if(items.get(i).getLastBid().getAmount() >= items.get(i).getReserve()) {       
       //get amount of last bid and see if it is more than reserve amount
      if(items.get(i).getQuantity() >= items.get(i).getLastBid().getQuantity()) {
        //if the quanity is greater than or equal to quanity of last bid
        
       totalFees += (items.get(i).getLastBid().getAmount() * items.get(i).getLastBid().getQuantity());       
       items.get(i).setFinalValueFee(items.get(i).getLastBid().getAmount() * items.get(i).getLastBid().getQuantity());       
       totalItemsSold += items.get(i).getLastBid().getQuantity();       
       soldItems.add(items.get(i));
       
      }
      else {
        
       totalFees += (items.get(i).getLastBid().getAmount() * items.get(i).getQuantity());
       items.get(i).setFinalValueFee(items.get(i).getLastBid().getAmount() * items.get(i).getQuantity());
       totalItemsSold += items.get(i).getQuantity();
       soldItems.add(items.get(i));
      }
       
     }
    }
    else if("BOTH".equals(items.get(i).getType())) {//if Both item
     if(items.get(i).getLastBid().getAmount() >= items.get(i).getReserve()) {
      if(items.get(i).getQuantity() >= items.get(i).getLastBid().getQuantity()) {
       totalFees += (items.get(i).getLastBid().getAmount() * items.get(i).getLastBid().getQuantity());
       items.get(i).setFinalValueFee(items.get(i).getLastBid().getAmount() * items.get(i).getLastBid().getQuantity());
       totalItemsSold += items.get(i).getLastBid().getQuantity();
       soldItems.add(items.get(i));
      }
      else {
       totalFees += (items.get(i).getLastBid().getAmount() * items.get(i).getQuantity());
       items.get(i).setFinalValueFee(items.get(i).getLastBid().getAmount() * items.get(i).getQuantity());
       totalItemsSold += items.get(i).getQuantity();
       soldItems.add(items.get(i));
      }
      
     }
    else {
     if(items.get(i).getLastBid().getAmount() >= ((items.get(i).getReserve()) * .95)) {
      totalFees += (items.get(i).getLastBid().getAmount() * items.get(i).getLastBid().getQuantity());
      items.get(i).setFinalValueFee(items.get(i).getLastBid().getAmount() * items.get(i).getLastBid().getQuantity());
      totalItemsSold += items.get(i).getLastBid().getQuantity();
      soldItems.add(items.get(i));
     }
    //else
     //System.out.println("Bid: " + items.get(i).getLastBid() + " \nReserve Not Met");
     }
    }    
    else if("AUCTION".equals(items.get(i).getType())) {//If it is an Auction item process this way
     if(items.get(i).getLastBid().getAmount() >= ((items.get(i).getReserve()) * .95)) {
      totalFees += (items.get(i).getLastBid().getAmount() * items.get(i).getLastBid().getQuantity());
      items.get(i).setFinalValueFee(items.get(i).getLastBid().getAmount() * items.get(i).getLastBid().getQuantity());
      totalItemsSold += items.get(i).getLastBid().getQuantity();
      soldItems.add(items.get(i));
     }
    //else
     //System.out.println("Bid: " + items.get(i).getLastBid() + " \nReserve Not Met");
    }
    }
    
   }
  
 }
 
/**
  * gets revenue after the items have been checked
  *  
  * @param foutput Printwriter to print to file
  */
public void getRevenue(int year, String type,PrintWriter foutput){
  String num = dollars.format(revenue);
  revenue = 0;
  
  if(type.equals("*")) {
   for(int i=0; i<soldItems.size(); i++){
    if((soldItems.get(i).getStartDate().getDate().getYear()) == year) {
      revenue += (soldItems.get(i).getInsertionFee() + soldItems.get(i).getFinalValueFee());
    }
   }
   for(int i=0; i<soldItems.size(); i++){
    if((soldItems.get(i).getStartDate().getDate().getYear()) == year)
     foutput.println("All items sold: " + soldItems.get(i));
   }
   foutput.println("Revenue total for all items sold in the year " + year + ": " + num + "\n");
  }
  else if(type.toUpperCase().equals("FIXED")) {
    foutput.println("----------Fixed Items Sold-------------");
   for(int i=0; i<soldItems.size(); i++){
    if((soldItems.get(i).getStartDate().getDate().getYear()) == year) {
      revenue += (soldItems.get(i).getInsertionFee() + soldItems.get(i).getFinalValueFee());
    }
   }
   for(int i=0; i<soldItems.size(); i++){
    if((soldItems.get(i).getStartDate().getDate().getYear()) == year)
     foutput.println("    " + soldItems.get(i));
   }
   foutput.println("Total revenue for fixed items sold in year " + year + ": " + num + "\n");
  }
  else if(type.toUpperCase().equals("AUCTION")) {
    foutput.println("----------Auction Items Sold-------------");
   for(int i=0; i<soldItems.size(); i++){
    if((soldItems.get(i).getStartDate().getDate().getYear()) == year) {
      revenue += (soldItems.get(i).getInsertionFee() + soldItems.get(i).getFinalValueFee());
    }
   }
   for(int i=0; i<soldItems.size(); i++){
    if((soldItems.get(i).getStartDate().getDate().getYear()) == year)
     foutput.println("    " + soldItems.get(i));
   }
   foutput.println("Total revenue for auction items sold in year " + year + ": " + num + "\n");
  }
  else if(type.toUpperCase().equals("BOTH")) {
    foutput.println("----------Both Items Sold-------------");
   for(int i=0; i<soldItems.size(); i++){
    if((soldItems.get(i).getStartDate().getDate().getYear()) == year) {
      revenue += (soldItems.get(i).getInsertionFee() + soldItems.get(i).getFinalValueFee());
    }
   }
   for(int i=0; i<soldItems.size(); i++){
    if((soldItems.get(i).getStartDate().getDate().getYear()) == year)
     foutput.println("    " + soldItems.get(i));
   }
   foutput.println("Total revenue for both auction and fixed items sold in year " + year + ": " + num + "\n");
  }
  
  foutput.println("\n\n");
 }
/**
  * Gets the items sold by person ID and that year
  *   
  * @param year  the year that items were sold
  * @param sid the seller ID
  * @param foutput Printwriter to print to file
  */
  public void soldItemsBySeller(int year, int sid,PrintWriter foutput){
   String revDollars = dollars.format(revenue);
   revenue = 0;
  foutput.println("---------------Sellers Details for ID: "+ sid+"---------------");
  foutput.println("-----Items put up for Auction for Year: "+year);
  for(int i=0; i<items.size(); i++) {
   if(items.get(i).getSellerID() == sid)
    if(items.get(i).getStartDate().getDate().getYear() == year)
     foutput.println(items.get(i));
  }
  for(int i=0; i<soldItems.size(); i++) {
   if(soldItems.get(i).getSellerID() == sid)
    if(soldItems.get(i).getStartDate().getDate().getYear() == year)
     foutput.println(soldItems.get(i));
  }
  foutput.println("------Items sold in Auction for Year: "+year);
  for(int i=0; i<soldItems.size(); i++) {
   if(soldItems.get(i).getSellerID() == sid) 
    if(soldItems.get(i).getStartDate().getDate().getYear() == year) {
     revenue += soldItems.get(i).getFinalValueFee();     
     foutput.println(soldItems.get(i) + "\n   Qty sold: " + 
      soldItems.get(i).getLastBid().getQuantity() +"\n    Item sold for: " + soldItems.get(i).getFinalValueFee());
   }
  }
  foutput.println("Total revenue " + sid + ": " + revDollars);
  }
  

 /**
  * prints all Bids for all Items
  * @param foutput PrinterWriter to write to file
  */ 
 public void printAllBids(PrintWriter foutput){
   
    for(int i=0;i<items.size();i++){ 
      
        items.get(i).printBids(foutput);        
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
          this.releaseEmployee(empIDSelection, "*");
          
        }catch (Exception e){       
          System.out.println("Invalid input");
          this.releaseEmpMenu();
        }
    
  }
  
  
 
  /**
  * Prints items sold for proj2
  *
  */
  public void soldItemsMenu(String type, int year){
   
   
   System.out.println("1");
   //for(int i=0;i<items.size();i++){//time has eslapsed check to see if anything new has been sold
     //items.get(i).checkSold(); 
   //}   
   System.out.println("2");
   if(type.equals("*")){
     
     for(int i=0;i<items.size();i++){    
       if(this.items.get(i).getQtySold()>=ONEI){//if an item has been sold print it
         System.out.println(this.items.get(i));
       }
     }
     
   }else if(type.toUpperCase().equals(FIXED)){
     for(int i=0;i<items.size();i++){    
       if(this.items.get(i).getQtySold()>=ONEI && this.items.get(i).getType().equals(FIXED)){
         //if the item has been sold and its a Fixed Price. Print it.
         System.out.println(this.items.get(i));
       }
     }
     
   }else if(type.toUpperCase().equals(AUCTION)){
     for(int i=0;i<items.size();i++){    
       if(this.items.get(i).getQtySold()>=ONEI && this.items.get(i).getType().equals(AUCTION)){
       //if the item has been sold and its a Auction Price. Print it.
        System.out.println(items.get(i));
        }
      }     
   }else if(type.toUpperCase().equals(BOTH_ITEM)){
     for(int i=0;i<items.size();i++){
       if(this.items.get(i).getQtySold()>=ONEI && this.items.get(i).getType().equals(BOTH_ITEM)){
         System.out.println(items.get(i));
       }       
     }    
   }else{
    System.out.println("Invalid selection");    
   }
  }
  /**
  * Given Employee ID and overtime rate calculate salary of given Employee
  * 
  * @param eid Employee ID
  * @param otr overTime rate 
  */
  public void getSalary(int eid, int otr,int month,PrintWriter foutput){
    foutput.println("-----Calculating Salary");
    for(int i=0;i<employees.size();i++){       
      if(employees.get(i).getID()==eid){
        foutput.println(employees.get(i).printCalc(month)+"Monthly Salary: "+employees.get(i).computeSalary(otr));
        
        }                
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
 //Accountant(String fn,String ln, Date db, String gender, Date hd, String rd,double base, double hr)
 Date db1= new Date(3,14,1969);
 Date hd1= new Date(1,12,1999);
 Accountant acct1= new Accountant("Bill","Nye",db1,"Male",hd1,"08-01-2010",2100,21);
 Date db2= new Date(7,7,1977);
 Date hd2= new Date(3,15,2002);
 Accountant acct2= new Accountant("Soookie","Stackhouse",db2,"FeMale",hd2,"null",2800,21);
 
 Date db3= new Date(8,28,1983);
 Date hd3= new Date(2,3,2017);
 WebDesigner wd1= new WebDesigner("Scott","Nidell",db3,"Male",hd3,"null",2800,49);
 Date db4= new Date(1,7,1998);
 Date hd4= new Date(9,9,2009);
 WebDesigner wd2= new WebDesigner("Jessica","Hamby",db4,"FeMale",hd4,"09-02-2011",1100,22);
 
 Date db5= new Date(8,01,1988);
 Date hd5= new Date(1,3,2012);
 CSupport cs1= new CSupport("Dan","Peachtree",db5,"Male",hd5,"null",900,12);
 Date db6= new Date(1,7,1998);
 Date hd6= new Date(9,9,2009);
 CSupport cs2= new CSupport("Haley","Berry",db6,"FeMale",hd6,"null",900,12);
 
  /***********************Adding Employees to Enterprise****************/
 Enterprise myent= new Enterprise("Yo dog");  
 myent.addEmployee(acct1);
 myent.addEmployee(acct2);
 myent.addEmployee(wd1);
 myent.addEmployee(wd2);
 myent.addEmployee(cs1);
 myent.addEmployee(cs2);
 //myent.printEmployees();
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
 
 
 
 
 
 
 
 
 
 