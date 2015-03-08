/**
 * Programmer:  Sharma Chakravarthy
 * Language: Java
 * date:        09/10/2013
 * Purpose: This program uses MayBayMgmtTest class to read data from a text file to initialize
 *   employees, items for sale, customers who bid, and actual bids
 *     
 *              It checks and recovers from some exceptions while reading the input file
 * 
 * USAGE:       You need to initialize your data structures (creation of objects) as the first step. 
 *              once the values are read into local variables, 
 *              it  is YOUR responsibility to add code at proper places to create objects and manage them!
 *
 *              filename is given as a command line argument (e.g, java MayBayMgmtTest dataFile-proj2.txt)
 *              for the naming convention used in this file. if you forget to give the data 
 *              file as a command line argument, it will prompt you for that. 
 *          
 *              you can remove or comment out print staatements once you are sure it is reading the input correctly
 *
 * MAKE SURE:   your program is completely case in-sensitive (for gender, employee type etc.)
 */

import java.io.*;
import java.util.Scanner;

/**
 * @param fileName
 *            as input data filename containing input items with  as item separators
 *  Note that multiple interfaces can be used with a class
 */

public class MavBayMgmtTest implements Proj2Constants, DateConstants {

 // define your (class and instance) attributes here
    // if you are not using a  separate Test driver class for that

 private static Scanner finput, cp;
 private String companyName;
 
 

 /**
  * @param fileName
  *            is the input data file name
  */

 public static Scanner openFile(String fileName) {
  Scanner sc = null;
  try {
   sc = new Scanner(new File(fileName));
  } catch (FileNotFoundException FNFE) {
   sc = null;
  } finally {
   return sc;
  }
 }

 // add here constructors and methods as needed for this class

 /**
  * @param takes
  *            fineName as command line argument. prompts if not given
  */
 public static void main(String[] args) {

  // declare variables used for input handling
  String enterprisename = EMPTY_STRING;
  String inputLine =  EMPTY_STRING, fName;

  // determine if correct input file is provided

  cp = new Scanner(System.in);
  if (args.length < 1) {
   System.out.println("Input Data file name was not supplied");
   System.out.printf("Please input data file name: ");
   fName = cp.nextLine();
  } else
   fName = args[ZEROI];

  // See HOW RECOVERY is done (will cover this in module II)

  finput = openFile(fName);
  while (finput == null) {
   System.out.printf("Error: FILE %s %s", fName,
     " does not exist.\nEnter correct input data file name: ");
   fName = cp.nextLine();
   finput = openFile(fName);
  }

  /* GET MavBay DETAILS */
  // start reading from data file
  // get name
  try {
   inputLine = finput.nextLine();
            System.out.println(inputLine);
   while (inputLine.charAt(BASE_INDEX) == '/'){
    inputLine = finput.nextLine();
                System.out.println(inputLine);
            }
   String enterpriseName = inputLine;
   System.out.printf("\n%s %s \n", "Enterprise name is: ",
     enterpriseName);
   
   // add code: you need to store enterprise name in some aobject!
   Enterprise myEnt= new Enterprise(enterpriseName);

   /* GET numbers of empoyees*/
   inputLine = finput.nextLine();
            System.out.println(inputLine);
   while (inputLine.charAt(BASE_INDEX) == '/'){
    inputLine = finput.nextLine();
                System.out.println(inputLine);
            }

   // reading 1 value from a single line
   
            int numEmployees = Integer.parseInt(inputLine);                      
   
   System.out.printf("\nnumEmployees: [%5d]\n",
       numEmployees);

   // add code here: use the above to initialize your arrays as needed;
            // arraylists do not need a size; they expand/contract
   
   /* GET EMPLOYEE DETAILS */

   // reading details for each employee from the data file
   System.out.printf("\nEmployees: %d\n", numEmployees);
   int employeeID=001;
   for (int i = 0; i < numEmployees; i++) {
    inputLine = finput.nextLine();
                System.out.println(inputLine);
    while (inputLine.charAt(BASE_INDEX) == '/'){
     inputLine = finput.nextLine();
                    System.out.println(inputLine);
                }
    String[] chopEmp = inputLine.split("!");

    // fill all fields for a single employee from a single line
    String empType = chopEmp[ZEROI].toUpperCase();
    String empFName = chopEmp[ONEI];
    String empLName = chopEmp[TWOI];
    String empBDate = chopEmp[THREEI];
    String empGender = chopEmp[FOURI].toLowerCase();
    String empHireDate = chopEmp[FIVEI];
                String empReleaseDate = chopEmp[SIXI];
    double empBaseSalary = Double.parseDouble(chopEmp[SEVENI]);

    // add code here: in order to convert a date string to a Date object,
    // invoke the appropriate constructor of the Date class (shown below)
    Date dob = new Date(empBDate); // dob is a local variable
    Date hireD = new Date(empHireDate);    
    Employee myEmp= new Employee(empFName,empLName,empGender,hireD,empReleaseDate, empBaseSalary,empType,dob);
    myEmp.setEmpID(employeeID);
    employeeID++;
    myEnt.addEmployee(myEmp);
    
    

    // also, empType is read as a string; if you are using a
    // enum, you need to convert it  using a switch 
                //or if statement or enum valueOf() method

                //create employee object here using Employee class constructors
                //make sure you set the release date properly (will be used later)
   }
   myEnt.printEmployees();
   /* GET ITEM to be sold DETAILS */

            // reading details for each item from the data file
            // note that numItems is computed, not input!

            System.out.printf("\nReading Items: \n");
            int numItems = 0;
            inputLine = finput.nextLine();
            System.out.println(inputLine);
            while (inputLine.charAt(BASE_INDEX) == '/'){
                inputLine = finput.nextLine();
                System.out.println(inputLine);
            } 
                   
            while ( (!inputLine.toLowerCase().equals("end"))){            
                String[] chopItems = inputLine.split("!");
            
            // get fields of a item from one line of input
   
                int  itemId = Integer.parseInt(chopItems[ZEROI]);
                String  itemCategory = chopItems[ONEI];
                String itemName = chopItems[TWOI];
                String itemSaleType = chopItems[THREEI];
                int itemQty = Integer.parseInt(chopItems[FOURI]);
                String itemCondition = chopItems[FIVEI];
                double itemMinStartBid = Double.parseDouble(chopItems[SIXI]);
                double itemBidIncrement = Double.parseDouble(chopItems[SEVENI]);
                double itemReserveAmt = Double.parseDouble(chopItems[EIGHTI]);
                String itemAuctionStartDate = chopItems[NINEI];
                int    itemAuctionDays      = Integer.parseInt(chopItems[TENI]);
                int    itemSellerId      = Integer.parseInt(chopItems[ELEVENI]);
                int    sellerFeedbackScore      = Integer.parseInt(chopItems[TWELVEI]);               
                String itemDescription      =chopItems[THIRTEENI];
                
                // added code to Build objects
                Item item= new Item(itemId,itemCategory,itemName,itemSaleType, itemQty, itemCondition,
                    itemMinStartBid,itemBidIncrement, itemReserveAmt, itemAuctionStartDate,
                    itemAuctionDays, itemSellerId, sellerFeedbackScore,itemDescription);
                myEnt.addItem(item);
               
                
                // convert strings to enum as needed
             
                inputLine = finput.nextLine();
                System.out.println(inputLine);
                while (inputLine.charAt(BASE_INDEX) == '/'){
      inputLine = finput.nextLine();
                    System.out.println(inputLine);
                }
                numItems += 1;
             }
              myEnt.printItems();    
              
                        
            /* GET CUSTOMER/USER DETAILS */

            // reading details for each user from the data file
            // note that numUsers is computed, not input!
   
            int numUsers =0;
            inputLine = finput.nextLine();
            System.out.println(inputLine);
            while (inputLine.charAt(BASE_INDEX) == '/'){
                inputLine = finput.nextLine();
                System.out.println(inputLine);
            }
            
            while ( (!inputLine.toLowerCase().equals("end"))){
                
                String[] chopUser = inputLine.split("!");
                // fill all fields for a single user/customer from a single line
                int userId = Integer.parseInt(chopUser[ZEROI]);
                String userFName = chopUser[ONEI];
                String userLName = chopUser[TWOI];
                String userDob = chopUser[THREEI];
                String userAddress = chopUser[FOURI];
                String userState = chopUser[FIVEI].toUpperCase();
                String userZipcode = chopUser[SIXI];
                
                // code added building customers
                Date dobUser = new Date(userDob);                
                Customer customer= new Customer(userId,userFName, userLName,dobUser, userAddress, userState, 
                    userZipcode);      
                myEnt.addCustomer(customer);
                

                inputLine = finput.nextLine();
                System.out.println(inputLine);
                while (inputLine.charAt(BASE_INDEX) == '/'){
        inputLine = finput.nextLine();
                    System.out.println(inputLine);
                }
                numUsers += 1;
   }
            myEnt.printCustomers();
                        
            //bid information for items
                        
            inputLine = finput.nextLine();
            System.out.println(inputLine);
   while (inputLine.charAt(BASE_INDEX) == '/'){
     inputLine = finput.nextLine();
              System.out.println(inputLine);
            }
            
            while ( (!inputLine.toLowerCase().equals("end"))){
                String[] chopBiddingInfo = inputLine.split("!");      
                            
                int itemId = Integer.parseInt(chopBiddingInfo[ZEROI]);
                int userId = Integer.parseInt(chopBiddingInfo[ONEI]);
                String bidDateTime = chopBiddingInfo[TWOI];
                double bidAmount = Double.parseDouble(chopBiddingInfo[THREEI]);
                int bidQty = Integer.parseInt(chopBiddingInfo[FOURI]);
                
                //code added
                DateTime bidTime= new DateTime(bidDateTime);
                Bid bid= new Bid(userId,itemId,bidTime,bidAmount,bidQty);                
                myEnt.addBid(bid);
                
                
                inputLine = finput.nextLine();
                System.out.println(inputLine);
                while (inputLine.charAt(BASE_INDEX) == '/'){                 
                    inputLine = finput.nextLine();
                    System.out.println(inputLine);  
                }
            } 
             
            Scanner scan= new Scanner(System.in);
             String menuScan=EMPTY_STRING;
             
             while(!menuScan.equals("0")){
               myEnt.printMenu();
                menuScan=scan.next();
               if(menuScan.equals("1")){
                 System.out.println("Listing All Employees...");
                 myEnt.printEmployees();
                 
               }else if(menuScan.equals("2")){
                 myEnt.itemsMenu();
                 
               }else if(menuScan.equals("3")){
                 System.out.println("Printing All Users...");
                 myEnt.printCustomers();
                 
               }else if(menuScan.equals("4")){                 
                 myEnt.soldItemsMenu();
                 
               }else if(menuScan.equals("5")){
                 myEnt.totalFees();               
                   
               }else if(menuScan.equals("6")){
                 myEnt.maxBid();
                 
               }else if(menuScan.equals("7")){
                 myEnt.releaseEmpMenu();
                 
               }else if(menuScan.equals("0")){
                System.out.println("Exiting System"); 
                System.exit(CLEAN_EXIT);
               }else{
                System.out.println("Invalid Selection\n"); 
               }
             }

            // you will be adding MOST of your code for menu and processing here
            // add code for menu display, accept input, and process commands
             // and display output
            // DO NOT REMOVE or DISTURB the REST OF THE CODE; 
            // it will not compile if you do!!!

  } catch (NumberFormatException NFE) {
   System.out.println("I/O Error in File: " + fName + "\ncheck for: "
     + NFE.getMessage() + " and correct it in: " + inputLine);
  } catch (RuntimeException RE) {
   System.out.println("Invalid Data error in File: " + fName
     + "\nPlease correct " + RE.getMessage() + " in the file!" + inputLine);
  } finally {
   finput.close();
  }
 }
}
