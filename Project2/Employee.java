/*************************************************************************
 *  Compilation:  javac Employee.java <br>
 *  Execution:    java Employee <br>
 *************************************************************************/

/**
 * A simple data type Employees for use in Project 2 (CSE 1325, Fall 2010)
 * <br>
 * Useful for creating Customer objects to hold Infromation of a employee
 * Name, address, Gender, hire date, release date, salary, employee type
 * 
 * @author Scott Nidell 
 */
import java.math.*;
import java.text.*;
import java.util.*;
public class Employee implements Proj2Constants,DateConstants{
  
 private enum Gender{MALE,FEMALE};
 private enum EmployeeType{WD,ACCT,CUST_SUPPORT};
 
 private int empID;
 private EmployeeType empType; 
 private String firstName;
 private String lastName;
 private Date dob;
 private Gender empGender; 
 private Date hireDate;
 private Date releaseDate;
 private double salary;
 private NumberFormat dollars= NumberFormat.getCurrencyInstance(Locale.US);
 private DecimalFormat three= new DecimalFormat("#000");
 
/**
  * Constructor: Default constructor that builds a Customer
  * with default values.
  */ 
 public Employee(){
 
   this.empID=ZEROI;
   this.empType= EmployeeType.CUST_SUPPORT; 
   this.firstName=DEFAULT_FIRST_NAME;
   this.lastName=DEFAULT_LAST_NAME;
   this.dob= null;
   this.empGender=Gender.FEMALE;
   this.hireDate= new Date();
   this.releaseDate=null;
   this.salary= ZEROD;
   
 }
 
  /**
  * Constructor: creates a employee given correct objects
  * 
  * @param id     employee id
  * @param eType  Web Dev, Accountant, Customer Support
  * @param fn     first name of the employee
  * @param ln     last name of the employee
  * @param db     Date of Birth
  * @param g      String rep of gender
  * @param hd     Date of Hire
  * @param rd     Date of Release
  * @param es     Employee salary
  * @exception RuntimeException
  *                if the date is invalid
  */ 
 
 public Employee(String fn, String ln,String g, Date hd, String rd, double es, String eType,Date db){   
   
   this.firstName=fn;
   this.lastName=ln;
   this.dob=db;
   if(!validGender(g))
     throw new RuntimeException("Employee 2: error not valid Gender type");
   this.empGender= Gender.valueOf(g.toUpperCase());
   this.hireDate=hd;
   if(rd.equals("null")){
   this.releaseDate=null;
   }else{   
   this.releaseDate= new Date(rd); 
   }
   if(!validType(eType))
     throw new RuntimeException("Employee 1: error not valid employee type");
   this.empType= EmployeeType.valueOf(eType.toUpperCase());
   this.salary=es;
 }
 
  /**
  * Constructor: Does bounds-checking to ensure string represents a valid
  * date
  * 
  * @param customerInfo    represents a customer info by comma seperated
  * first, last, Gender, Hire Date, Release Date, salary, employee Type
  * @exception RuntimeException if the date is invalid
  */
 public Employee(String emp){
   
   String [] splitResult= emp.split(",");   
   
   if(!validType(splitResult[ZEROI]))
     throw new RuntimeException("Employee 3: error not valid employee type");
   
   this.empType= EmployeeType.valueOf(splitResult[ZEROI]);
   this.firstName= splitResult[ONEI];
   this.lastName= splitResult[TWOI]; 
   this.dob= new Date(splitResult[THREEI]);
   
   if(!validGender(splitResult[FOURI]))
     throw new RuntimeException("Employee 4: error not valid Gender type");
   System.out.println("split result: "+splitResult[FOURI].toUpperCase());
   this.empGender= Gender.valueOf(splitResult[FOURI].toUpperCase());
   this.hireDate= new Date(splitResult[FIVEI]);
   this.releaseDate= null;
   this.salary=Double.parseDouble(splitResult[SIXI]);
 }
 
 private static boolean validGender(String g){
   
   if(g.toUpperCase().equals("MALE"))
     return true;
   if(g.toUpperCase().equals("FEMALE"))
     return true;
   
   return false;
 }
 
 /**
  * Is the employee type valid?
  * 
  * @param String literal of the enum object
  * @return false if a valid string is not compared to enum type
  */
 
 private static boolean validType(String et){
   
   if(et.toUpperCase().equals(WEBDESIGNER)){
     return true;
   }else if(et.toUpperCase().equals(ACCOUNTANT)){
     return true;
   }else if(et.toUpperCase().equals(CUSTOMER_SUPPORT)){
     return true;
   }else
     return false;     
 }
 
 
 /**
  * Raise pay given if employee is still employed
  * 
  * @param amount to be increased
  * @return false if employee is not employed
  */
 public boolean raisePay(double p){
  
   double possibleSalary= this.salary+p;
   if(this.releaseDate!=null){
    System.out.println("**********Employee no longer on staff, cannot give raise");
    return false;
   }
   if(possibleSalary> MAX_SALARY){
     System.out.println("Salary limit reached");
     return false;
   }
   
   this.salary+=p;
   return true;
 }
 
 /**
  * releases Employee and sets salary to zero  * 
  * 
  */
 public void releaseEmp(){
  
   this.releaseDate= new Date();
   this.salary= ZEROD;
 }
 
 /**
  * changes first Name of Employee
  * 
  */
 public void changeFirstName(String first){
  
   this.firstName=first;
 }
 
 /**
  * Change last name of Employee
  * 
  */
 public void changeLastName(String last){
   
   this.lastName=last;
 }
 
 /**
  * Sets Employee ID to the new one passed in
  * 
  * @param id new id to be set to
  */
 public void setEmpID(int id){
  
   this.empID=id;
 }
 
 /**
  * Changes the Employee type of given the correct string representation
  * 
  * @param et new Employee Type
  */ 
 public void changeEmpType(String et){
   
   if(et.toUpperCase().equals(WEBDESIGNER)){
     this.empType= EmployeeType.WD;
   }else if(et.toUpperCase().equals(ACCOUNTANT)){
     this.empType= EmployeeType.ACCT; 
   }else if(et.toUpperCase().equals(CUSTOMER_SUPPORT)){
     this.empType= EmployeeType.CUST_SUPPORT;
   }else
       System.out.println("Invalid Selection for Employee Type");     
 }
 
 /**
  * Change Gender of Employee
  *  
  */
 public void changeGender(){
   if(this.empGender.equals(Gender.FEMALE)){
     this.empGender=Gender.MALE;
   }else if(this.empGender.equals(Gender.MALE))
     this.empGender=Gender.FEMALE;   
 }
 
 /**
  * Returns the employee ID of give employee
  * 
  * @return returns the ID of current employee
  */
 public int getEmpID(){
   
   return this.empID;
 }
 
 /**
  * Gets the release Date of current employee
  * 
  * @return date of the release else null
  */
 public Date getReleaseDate(){
   
   return this.releaseDate;
 }
 
 /**
  * A String representation of a Employee Object
  * 
  * @return returns a string of Employee object
  */
 public String toString(){
  String dollarSalary=dollars.format(this.salary);
  String empIDformat= three.format(this.empID);
  return "{ ID: "+empIDformat+"| Name: "+this.firstName+" "+this.lastName+"|Gender: "+
    this.empGender+"| HireDate: "+this.hireDate+"| Release Date: "+this.releaseDate+
    "|Salary: "+dollarSalary+"|Job: "+this.empType;
 }
 
 public static void main(String [] args){
   
   System.out.println("Start Test Employee 1 default Employee");
   Employee myEmp1= new Employee();
   System.out.println(myEmp1);
   myEmp1.raisePay(3000);
   System.out.println(myEmp1);
   myEmp1.releaseEmp();
   System.out.println(myEmp1);
   myEmp1.changeFirstName("Bill Do");
   System.out.println(myEmp1);
   myEmp1.changeLastName("Hey yall");
   System.out.println(myEmp1);
   myEmp1.changeGender();
   System.out.println(myEmp1);
   myEmp1.changeGender();
   System.out.println(myEmp1);
   myEmp1.setEmpID(2000);
   System.out.println(myEmp1);
   System.out.println("------------------EMP2----------------\n");
   Employee myEmp2= new Employee("WD,Scott,Nidell,08-28-1983,male,08-15-2002,4000");
   System.out.println(myEmp2);
   myEmp2.raisePay(3000);
   System.out.println(myEmp2);
   myEmp2.releaseEmp();
   myEmp2.raisePay(3000);
   System.out.println(myEmp2);
   myEmp2.changeFirstName("Bill Do");
   System.out.println(myEmp2);
   myEmp2.changeLastName("Hey yall");
   System.out.println(myEmp2);
   myEmp2.changeGender();
   System.out.println(myEmp2);
   myEmp2.changeGender();
   System.out.println(myEmp2);
   myEmp2.setEmpID(2);
   System.out.println(myEmp2);
 
   System.out.println("------------------EMP3----------------\n\n");
   
 int empID=002;
 String myEmpType="Acct";
 String firstName="Johnny";
 String lastName="Folizie";
 Date myDob= new Date("01-21-1991"); 
 String myMale= "MALe";
 Date hireDate= new Date("03-22-1998"); 
 String releaseDate= "null";
 double salary= 14000;
 
 Employee myEmp3= new Employee(firstName, lastName, myMale,hireDate, releaseDate, salary,myEmpType,myDob);
 System.out.println(myEmp3);
 myEmp3.raisePay(1000);
 System.out.println(myEmp3);
 myEmp3.changeEmpType("wd");
 System.out.println(myEmp3);
 myEmp3.releaseEmp();
 System.out.println(myEmp3);

 }  
}