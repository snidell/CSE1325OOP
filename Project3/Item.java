/*************************************************************************
 *  Compilation:  javac Item.java <br>
 *  Execution:    java Item <br>
 *************************************************************************/

/**
 * A simple data type Item for use in Project 2 (CSE 1325, Fall 2010)
 * <br>
 * Useful for creating Item objects to hold Infromation of an Item
 * Item ID, category, name, description, sale type, quanity, condition, minimum bid,
 * Increment, Reserve amount, Start date, number of days, seller ID, Feedback score
 * 
 * @author Scott Nidell 
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
public class Item implements Proj3Constants,DateConstants{
  
  private enum Condition{NEW,USED};
  private enum SaleType{FIX_PRICE,AUCTION,BOTH};
  
  private int itemID;
  private String category;
  private String name;
  private SaleType type;
  private int qty;
  private int qtySold=ZEROI;
  private Condition condition;
  private double minStart;
  private double increment;
  private double reserveAmount;
  private DateTime startD;
  private int numDays;
  private int sellerID;
  private int feedback;
  private int shipID;
  private double finalValueFee=0;
  private String description;
  private ArrayList<Bid> bids = new ArrayList<Bid>();
  private DateTime endDate;
  private double insertionFee=0;  
  private double costCollected;
  
  /**
  * Constructor: Default constructor that builds a Item
  * with default values.
  */
  
  public Item(){
   this.itemID=ZEROI;
   this.category=null;
   this.name=DEFAULT_ITEM;
   this.qty=ZEROI;
   this.condition= Condition.NEW;
   this.minStart= ZEROD;
   this.increment= ZEROD;
   this.reserveAmount=ZEROD;
   this.startD= new DateTime();
   this.numDays= ZEROI;
   this.sellerID=ZEROI;
   this.feedback=ZEROI;
   this.description=null;
  }
  
  /**
  * Constructs Item given data
  * 
  * @param id       id of the item
  * @param cat      category of item
  * @param n        name of item
  * @param ty       sale type of Item
  * @param qt       quanity of items to be sold
  * @param cond     condition in terms of  a string
  * @param minS     minimum start of auction
  * @param inc      increment of bids
  * @param reserveA amount of item
  * @param sd       Start Date
  * @param sid      Seller ID
  * @param fb       Seller Feedback
  * @param desc     Description of Item
  * 
  */
  public Item(int id, String cat, String n, String ty, int qt, String cond, double minS,
               double inc, double reserveA, String sd, int nd,int sid, int fb,String desc){
    
   
    this.itemID=id;
    this.category=cat;
    this.name=n;
    if(!isValidType(ty))
      throw new RuntimeException("Item 1: error not valid item type");
    this.type= SaleType.valueOf(ty.toUpperCase());
    this.qty=qt;
    if(!isValidCond(cond))
      throw new RuntimeException("Item 2: error not valid item condition");
    this.condition= condition.valueOf(cond.toUpperCase());
    this.minStart= minS;
    this.increment=inc;
    this.reserveAmount=reserveA;
    this.startD= new DateTime(sd);
    this.numDays=nd;
    this.sellerID=sid;
    this.feedback=fb;
    this.description=desc;
    this.endDate=new DateTime(sd);
    this.endDate.addDays(nd);
  }
  
  /**
  * checks if a Sale type is valid
  * 
  * @return false if item does not fall in Enum sale type    
  * 
  */
  
  private static boolean isValidType(String t){
    
    if(t.toUpperCase().equals(FIXED)){
     return true;
   }else if(t.toUpperCase().equals(AUCTION)){
     return true;
   }else if(t.toUpperCase().equals(BOTH_ITEM)){
     return true;
   }else
     return false; 
  }
  
  
  
  /**
  * checks if a condition type is valid
  * 
  * @return false if item does not fall in Enum condition    
  */
  private static boolean isValidCond(String c){
    
    if(c.toUpperCase().equals(NEW)){
     return true;
   }else if(c.toUpperCase().equals(USED)){
     return true;
   }
     return false;
  }
  /**
  * returns the final value feee
  * 
  * @return final fee 
  */
  public double getFinalValueFee() {
  return this.finalValueFee;
 }
    
  /**
  * creates new description for Item
  * 
  * @param d new description of the item  
  * 
  */
  public void setDescription(String d){
    
    this.description=d;    
  }
  
  /**
  * Change quantity of Item
  * 
  * @param q new quanitity for the item
  *                
  */
  public void setQuanity(int q){
    
    this.qty=q;    
  }
  
  /**
  * Increase feedback of Item
  * 
  * @param fb amount of rating to be increased
  *                
  */
  public void increaseFeedback(int fb){
    
    this.feedback+=fb;  
  }
  
  /**
  * Decrease feedback of Item
  * 
  * @param fb amount of rating to be decreased
  *                
  */
  
  public void decreaseFeedback(int fb){
    
    this.feedback-=fb;    
  }
  
  /**
  * Returns the Quanity sold of this Item
  *
  * 
  * @return 0 to Max of the quanity sold of this item
  */
  public int getQtySold(){
    
   return qtySold; 
  }
  
  /**
  * Returns the String representation of a SaleType object of this Item
  * 
  * @param b DateTime object
  * @return String of the SaleType 
  */
  public String getType(){
    String typeConversion=EMPTY_STRING;
        
    if(this.type==SaleType.FIX_PRICE){
      typeConversion=FIXED1;
    }else if(this.type==SaleType.AUCTION){
      typeConversion=AUCTION;     
    }else if(this.type==SaleType.BOTH){
      typeConversion=BOTH_ITEM;     
    }    
    return typeConversion;    
  }
  
   /**
  * Returns a arrayList of Bids used for calculating revnue
  * 
  * @return   returns array list of bids
  */
 public ArrayList getBids() {
  return this.bids;
 }
 
 /**
  *Returns reserve amount used for revenue calculation
  * 
  * @return   returns the reserve price of an item
  */
 public double getReserve() {
  return this.reserveAmount;
 }
 /**
  *Returns reserve amount used in rev calculation
  * 
  * @return   returns the reserve price
  */
 public void setFinalValueFee(double fee) {
  this.finalValueFee = fee;
 }
 
 /**
  * Gets quanity of items being sold
  * 
  * @return   returns the quanity of item
  */
 public int getQuantity() {
  return this.qty;
 }
 
 /**
  * Returns last bid of the bids array
  * 
  * @return  returns the last bid in the bids array list
  */
 public Bid getLastBid() {
  int lastIndex = (this.bids.size() - ONEI);
  return this.bids.get(lastIndex);
 }
 
 /**
  *returns start date of item 
  * 
  * @returnreturns the DateTime an items auction started
  */
 public DateTime getStartDate() {
  return this.startD;
 }
 /**
  *returns insertionFee collctd
  * 
  * @return insertionFee
  */
 public double getInsertionFee() {
  return this.insertionFee;
 }
  
  /**
  * Adds a bid to the Arraylist
  * 
  * @param bid is the bid trying to be added
  */
  public void addBid(Bid bid){
    if(bids.size()>=MAX_BIDS){ //is bid less than 500?
      System.out.println("Max bids reached for this item");
      return;
    }else if(isValid(bid)){ //Is it valid?
      bids.add(bid);
    }else{
    System.out.println("Not a Valid Bid");
    //System.out.println("Bid Given: "+bid+" Previous bid: "+bids.get(i-1));
    }
    
  }
  
  /**
  * Returns the ID of the Item
  *
  * @return Int of the current item ID
  */
  public int getItemID(){
   
    return this.itemID;    
  }
  /**
  * Returns the ID of the Seller
  *
  * @return Int of the current sellerID
  */
  public int getSellerID(){
     return this.sellerID;
  }
  
  /**
  * Checks to see if a Bid is Valid
  * 
  * @param b Bid object beiung validated
  * @return true DateTime,QTY,and TYPE correct
  */
  public  boolean isValid(Bid b){
    int lastIndex=this.bids.size()-ONEI;
    if(!(b.getBidQty()<=this.qty))
        return false;
    if(this.bids.size()==ZEROI){//if this is the first bid going in
      if(b.getBidDate().isBefore(this.endDate)&& b.getBidDate().isAfter(this.startD)){
        //then check to see if the date is within the start and end date
        return true;        
      }
    }else if(this.bids.size()>=ONEI){//if there is at least one active bid
      if(b.getBidDate().isAfter(this.bids.get(lastIndex).getBidDate()) && b.getBidDate().isBefore(this.endDate)){
        //check to see if the date is after last bid
        return true;
      }else{
         return false; 
        }
    }
      
    if(this.type==SaleType.FIX_PRICE){
      if(b.getBidAmount()==this.reserveAmount && b.getBidQty()<=this.qty){        
        this.qty-=b.getBidQty();//because it is a valid item remove qty immediately
        this.qtySold=b.getBidQty(); //add to sold itemsQty        
        return true;
      }else{
        System.out.println("Invalid price or bid qty for FIXED item");
      }
    }else if(this.type==SaleType.AUCTION){ //if auction type
      if(this.bids.size()==ZEROI && b.getBidAmount()>=this.minStart){//if this is the first bid&its higher than min bid
        return true;
      }else if(this.bids.size()>ZEROI && b.getBidAmount()>=(this.bids.get(lastIndex).getBidAmount())+this.increment){
        //If bids>0 and new bid amount is higher than last bid +icnrement
        return true;
      }else{
       System.out.println("Invalid price for Auction item"); 
      }      
    }else if(this.type==SaleType.BOTH){//has attributes of fixed and auction type
      if(b.getBidAmount()==this.reserveAmount && b.getBidQty()<=this.qty){        
        this.qty-=b.getBidQty();//because it is a valid item remove qty immediately
        this.qtySold=b.getBidQty();         
        return true;
      }else if(this.bids.size()==ZEROI && b.getBidAmount()>=this.minStart){//if this is the first bid&its higher than min bid
        return true;
      }else if(this.bids.size()>ZEROI && b.getBidAmount()>=(this.bids.get(lastIndex).getBidAmount())+this.increment){
        //If bids>0 and new bid amount is higher than last bid +icnrement
        return true;
      }else{        
        System.out.println("Invalid price for Both item"); 
      }
      
    }else{     
      System.out.println("Invalid Type");
    }    
    return false;    
  }
  
  /**
  * Prints all bids in the Current Item
  * 
  */
  public void printBids(PrintWriter foutput){
   
    foutput.println("ItemID: "+this.itemID+"|Description: "+this.description);
    foutput.println("   CurrentBids:");
    for(int i=0;i<bids.size();i++){
     foutput.println("   "+bids.get(i));
    }
  }
  
  /**
  * Returns the ReserveAmount
  * 
  * @param double reserve amount
  */
  public double getReserveAmount(){
    return this.reserveAmount;
  }
  
  /**
  * Gets the End Date of the given Item
  *
  * @return String representation of End Date
  */
  public String getEndDate(){
    return this.endDate.toString();
  }
  
  /**
  * Check to See if any items have been sold in the time lapsed
  *
  * @return cost of fees collected
  */
  public double checkSold(){
   int lastIndex=this.bids.size()-ONEI;
    DateTime today= new DateTime();
   if(today.isAfter(this.endDate) && bids.get(lastIndex).getBidAmount()>= (this.reserveAmount*.95)){
     this.qty-=bids.get(lastIndex).getBidQty();
     this.qtySold+=bids.get(lastIndex).getBidQty();     
   }
    
   return this.calcCost(qtySold);
  }
  
  /**
  * Calculates the cost of bids processed in the Item
  * 
  * @param it quanity of of this item sold
  * @return Cost calculation of the quanity passed
  */
  public double calcCost(int it){
    int lastIndex=this.bids.size()-ONEI;
    double cost=ZEROI;
    double insertionFee= this.minStart*ONE_PERCENT;
    double finalValFee=  this.bids.get(lastIndex).getBidAmount()*TEN_PERCERNT;//fee if item is sold
    
    if(this.qtySold==ONEI){//fee for 1 item sold
      cost+= (SHIPPING_COST +finalValFee);      
    }else if(this.qtySold>ONEI){//fee for multiple items sold
      cost+= (SHIPPING_COST +finalValFee+((this.qtySold-ONEI)*MULTIPLE_FEE));
    }
      cost+=insertionFee;//everyone gets an insertion fee
      
   return cost; 
  }
  
  /**
  * Returns the number of bids in the current Item
  * 
  * @return int number of bids
  */
  public int getNumBids(){    
   return bids.size(); 
  }
  
  /**
  * A helper method that prints by Reserve price for easier reading
  *
  * @return String representation more easily read with reserveAmount in Front
  */
  public String printByReserve(){
   return "ReserveAmount: "+this.reserveAmount+" ID: "+this.itemID+" Description: "+this.description;
  }
  
  /**
  * A helper method that gets minStartBid
  *
  * @return minStart bid
  */
  public double getMinStart(){    
   return this.minStart; 
  }
  
  

  /**
  * Prints the current attributes of the object
  *   
  * @return string that describes the Item object.
  *                
  */
  public String toString(){
    
    return "{ID: "+this.itemID+"| Name: "+this.name+"| Description: "+this.description+"| QTY: "
      +this.qty+"|Condition: "+this.condition+" Auction TYPE: "+this.type+"|Min Start: "+this.minStart+"|Seller Feedback: "+this.feedback;   
  }
  
  public static void main(String []args){
    
    
    Item myItem= new Item();
    System.out.println(myItem);
   
    //Testing bids FIXED Auction
   /* int itemID= 003;
    String itemCat= "Antique";
    String itName="Camera";
    String itemType= "FIX_PRICE";
    int itemQty= 5;
    String condition= "USED";
    double minStart= 30.00;
    double bidInc= 5.00;
    double reserveAmt= 400.00;
    String startD= "1-1-2014,21:49:00";
    int days= 7;
    int sellerID= 200;
    int feedback=1000;
    String desc= "Old Ass Camera"; 
    
    Item myItem3= new Item(itemID,itemCat,itName,itemType,itemQty,condition,minStart,bidInc,reserveAmt,startD,days,sellerID,feedback,desc);
    
    System.out.println(myItem3);
    
    
  int userID1=120;
  int itemID1=003;
  DateTime bidDate1= new DateTime("2-12-2014,00:00:45");
  double bidAmount1=200.00;
  int bidQTY1= 3;
  
  int userID2=112;
  int itemID2=003;
  DateTime bidDate2= new DateTime("2-13-2014,13:45:00");
  double bidAmount2=207.00;
  int bidQTY2= 3;
  
  int userID3=111;
  int itemID3=003;
  DateTime bidDate3= new DateTime("2-11-2014,11:00:00");
  double bidAmount3=400.00;
  int bidQTY3= 1;
  
  int userID4=154;
  int itemID4=003;
  DateTime bidDate4= new DateTime("2-11-2014,14:00:00");
  double bidAmount4=401.00;
  int bidQTY4= 1;
  
  Bid bid1= new Bid(userID1,itemID1,bidDate1,bidAmount1,bidQTY1);
  Bid bid2= new Bid(userID2,itemID2,bidDate2,bidAmount2,bidQTY2);
  Bid bid3= new Bid(userID3,itemID3,bidDate3,bidAmount3,bidQTY3);
  Bid bid4= new Bid(userID4,itemID4,bidDate4,bidAmount4,bidQTY4);
  
  System.out.println("Adding bid1");
  myItem3.addBid(bid1);//not valid
  myItem3.addBid(bid2);//not valid
  myItem3.addBid(bid3);//valid
  myItem3.addBid(bid4);//not valid
  
  myItem3.printBids();
  System.out.println("QTY: "+myItem3.qty+" Sold Item: "+myItem3.qtySold);*/
  
     //*****************Testing bids Auction type****************
    /*
    int itemID= 003;
    String itemCat= "Antique";
    String itName="Camera";
    String itemType= "AUCTION";
    int itemQty= 5;
    String condition= "USED";
    double minStart= 30.00;
    double bidInc= 5.00;
    double reserveAmt= 400.00;
    String startD= "1-1-2014,21:49:00";
    int days= 7;
    int sellerID= 200;
    int feedback=1000;
    String desc= "Old Ass Camera"; 
    
    Item myItem3= new Item(itemID,itemCat,itName,itemType,itemQty,condition,minStart,bidInc,reserveAmt,startD,days,sellerID,feedback,desc);
    
    System.out.println(myItem3);
    
  
  int userID1=120;
  int itemID1=003;
  DateTime bidDate1= new DateTime("2-12-2014,00:00:45");
  double bidAmount1=20.00;
  int bidQTY1= 2;
  
  int userID2=112;
  int itemID2=003;
  DateTime bidDate2= new DateTime("2-13-2014,13:45:00");
  double bidAmount2=35.00;
  int bidQTY2= 2;
  
  int userID3=111;
  int itemID3=003;
  DateTime bidDate3= new DateTime("2-11-2014,11:00:00");
  double bidAmount3=32.00;
  int bidQTY3= 2;
  
  int userID4=154;
  int itemID4=003;
  DateTime bidDate4= new DateTime("2-11-2014,14:00:00");
  double bidAmount4=30.00;
  int bidQTY4= 3;
  
  int userID5=136;
  int itemID5=003;
  DateTime bidDate5= new DateTime("2-18-2014,14:00:00");
  double bidAmount5=40.00;
  int bidQTY5= 1;
  
  System.out.println("Size of bids list: "+myItem3.bids.size());
  Bid bid1= new Bid(userID1,itemID1,bidDate1,bidAmount1,bidQTY1);
  Bid bid2= new Bid(userID2,itemID2,bidDate2,bidAmount2,bidQTY2);
  Bid bid3= new Bid(userID3,itemID3,bidDate3,bidAmount3,bidQTY3);
  Bid bid4= new Bid(userID4,itemID4,bidDate4,bidAmount4,bidQTY4);
  Bid bid5= new Bid(userID5,itemID5,bidDate5,bidAmount5,bidQTY5);
  System.out.println("Number of bids: "+myItem3.bids.size());
  System.out.println("Adding bid1");
  
  myItem3.addBid(bid1);//invalid
  myItem3.addBid(bid2);//valid
  myItem3.addBid(bid3);//invalid
  myItem3.addBid(bid4);//invalid
  myItem3.addBid(bid5);//invalid
  
  System.out.println("Number of bids: "+myItem3.bids.size());
  
  
  myItem3.printBids();
  System.out.println("QTY: "+myItem3.qty+" Sold Item: "+myItem3.qtySold);  */
    
   int itemID= 003;
    String itemCat= "Antique";
    String itName="Camera";
    String itemType= "BOTH";
    int itemQty= 5;
    String condition= "USED";
    double minStart= 30.00;
    double bidInc= 5.00;
    double reserveAmt= 400.00;
    String startD= "1-1-2014,21:49:00";
    int days= 7;
    int sellerID= 200;
    int feedback=1000;
    String desc= "Old Ass Camera"; 
    
    Item myItem3= new Item(itemID,itemCat,itName,itemType,itemQty,condition,minStart,bidInc,reserveAmt,startD,days,sellerID,feedback,desc);
    
    System.out.println(myItem3);
    
  
  int userID1=120;
  int itemID1=003;
  DateTime bidDate1= new DateTime("2-12-2014,00:00:45");
  double bidAmount1=20.00;
  int bidQTY1= 2;
  
  int userID2=112;
  int itemID2=003;
  DateTime bidDate2= new DateTime("2-13-2014,13:45:00");
  double bidAmount2=35.00;
  int bidQTY2= 2;
  
  int userID3=111;
  int itemID3=003;
  DateTime bidDate3= new DateTime("2-11-2014,11:00:00");
  double bidAmount3=400.00;
  int bidQTY3= 2;
  
  int userID4=154;
  int itemID4=003;
  DateTime bidDate4= new DateTime("2-11-2014,14:00:00");
  double bidAmount4=55.00;
  int bidQTY4= 3;
  
  int userID5=136;
  int itemID5=003;
  DateTime bidDate5= new DateTime("2-18-2014,14:00:00");
  double bidAmount5=500.00;
  int bidQTY5= 1;
  
  System.out.println("Size of bids list: "+myItem3.bids.size());
  Bid bid1= new Bid(userID1,itemID1,bidDate1,bidAmount1,bidQTY1);
  Bid bid2= new Bid(userID2,itemID2,bidDate2,bidAmount2,bidQTY2);
  Bid bid3= new Bid(userID3,itemID3,bidDate3,bidAmount3,bidQTY3);
  Bid bid4= new Bid(userID4,itemID4,bidDate4,bidAmount4,bidQTY4);
  Bid bid5= new Bid(userID5,itemID5,bidDate5,bidAmount5,bidQTY5);
  System.out.println("Number of bids: "+myItem3.bids.size());
  System.out.println("Adding bid1");
  
  myItem3.addBid(bid1);//invalid
  myItem3.addBid(bid2);//valid auction
  myItem3.addBid(bid3);//valid Fixed
  myItem3.addBid(bid4);//invalid
  myItem3.addBid(bid5);//valid
  
  System.out.println("Number of bids: "+myItem3.bids.size());
  
  
  
  System.out.println("QTY: "+myItem3.qty+" Sold Item: "+myItem3.qtySold);
  System.out.println("StartDate:"+myItem3.startD+" End Date: "+myItem3.getEndDate());
  double cost= myItem3.checkSold();
  System.out.println("cost: "+cost);
  System.out.println("QTY: "+myItem3.qty+" Sold Item: "+myItem3.qtySold);
  }
  
}