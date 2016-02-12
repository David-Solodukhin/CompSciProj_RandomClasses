package com.dukhin;

import java.util.*;

/**
 * Write a description of class EmployeeSystem here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class EmployeeSystem
{
	public static ArrayList<Person> people  = new ArrayList<Person>();
	public static Manager manager = new Manager("J.D. Salinger",53,"M1");
	public static Employee employee1 = new Employee("John C. Calhoun", 44, "E1",34000,manager);
	public static boolean isManager;
	public static boolean running;
	public static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args)
	{
    	running = true;
    	people.add(manager);
    	people.add(employee1);
    	manager.employees.add(employee1);
    	String doop = "";
    	String id = "";
    	boolean inEpi = false;
  	while(running){
    	 
      	if(id.equals("") && !inEpi){
      	System.out.println("Enter your id or 'q' to quit: ");
      	doop = scanner.next();
      	id = doop;
      	if(id.equals("q"))
    	{
        	System.exit(1);
    	}
      	System.out.println(findPerson(doop));
    	}
    	else if(id.equals("q"))
    	{
        	System.exit(1);
    	}
   	 
    	else{
	if(!findPerson(doop).contains("ID") && !inEpi){
    	inEpi = true;
   	// System.out.println(findPerson(doop));
	}
	else if(findPerson(doop).contains("ID") && !inEpi)
	{id="";}
	if((doop).contains("M") && inEpi)
	{
    	System.out.println("1 Print out list of employees");
    	System.out.println("2 Add an employee");
    	System.out.println("3 Fire an employee");
    	System.out.println("4 Give an employeea raise");
    	System.out.println("5 End session and return to main menu");
    	String nut = scanner.next();
    	if(nut.equals("5")){
    	inEpi = false;
    	id = "";
    	doop = "";
    	}
    	else if(nut.equals("1")){
   	manager.printInfo();
    	}
    	else if(nut.equals("2")){
        	System.out.println("give a new id");
        	String idEmployee = scanner.next();
        	manager.addEmployee(idEmployee);

    	}
    	else if(nut.equals("3"))
    	{
        	System.out.println("enter an id");
        	String idEmployee = scanner.next();
        	manager.fireEmployee(idEmployee);
    	}
     	else if(nut.equals("4"))
    	{
        	System.out.println("give the id");
        	String idEmployee = scanner.next();
        	System.out.println("enter a raise");
        	String raise = scanner.next();
        	manager.giveRaise(idEmployee,Integer.parseInt(raise));
    	}
   	 
	}
	else if((doop).contains("E") && !(doop).contains("M") &&inEpi)
	{
    	System.out.println("1 View Salary");
    	System.out.println("2 View Managerial Info");
     	String nut = scanner.next();
    	if(nut.equals("1")){
    	System.out.println(employee1.salary);
    	}
    	else if(nut.equals("2")){
        System.out.println(employee1.manager);
        }
    	else if(nut.equals("q")){
        System.exit(1);
        }
	}
    
    
	}
	}
	}
   
	public static String findPerson(String id)
	{
    	for(int i = 0;i<people.size();i++)
    	{
        	if(people.get(i).id.equals(id))
        	{
            	return people.get(i).toString();
        	}
       	 
    	}
    	return "No People By that ID";
	}
 	public static void removePerson(String id)
	{
    	for(int i = 0;i<people.size();i++)
    	{
        	if(people.get(i).id.equals(id))
        	{
            	people.remove(i);
        	}
       	 
    	}
	}
}
