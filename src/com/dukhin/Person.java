package com.dukhin;

/**
 * Write a description of class Person here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Person
{
	// instance variables - replace the example below with your own
   public String name;
   public int age;
   public String id;

	/**
 	* Constructor for objects of class Person
 	*/
	public Person(String name, int age, String id)
	{
    	this.name = name;
    	this.age = age;
    	this.id = id;
    
	}
	public String toString()
	{
    	return name + "\n " + " Age: " + age + " ID: " + id;
	}
   
}
