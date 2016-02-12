package com.dukhin;

import java.util.*;

/**
 * Write a description of class Manager here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Manager extends Person
{
   public ArrayList<Employee> employees = new ArrayList<Employee>();
	/**
 	* Constructor for objects of class Manager
 	*/
	public Manager(String name, int age, String id)
	{
   	super(name,age,id);
	 
	}
	public void printInfo()
	{
    	for(int i = 0;i<employees.size();i++)
    	{
        	System.out.println(employees.get(i).toString());
    	}
	}
	public void fireEmployee(String id)
	{
    	for(int i = 0;i<employees.size();i++)
    	{
        	if(employees.get(i).id.equals(id))
        	{
            	employees.remove(i);
        	}
       	 
    	}
	}
	public void giveRaise(String id,int raise)
	{
    	for(int i = 0;i<employees.size();i++)
    	{
        	if(employees.get(i).id.equals(id))
        	{
            	employees.get(i).salary+=raise;
        	}
       	 
    	}
	}
	public void addEmployee(String id)
	{
    	Employee delp = new Employee(id);
    	delp.manager = this;
    	employees.add(delp);
    	EmployeeSystem.people.add(delp);
	}
	public String toString()
	{
    	return name + age + id;
	}
}


