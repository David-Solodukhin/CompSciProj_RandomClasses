package com.dukhin;

public class Employee extends Person{
Manager manager;
int salary;
	public Employee(String name, int age, String id,int salary,Manager manager) {
		super(name, age, id);
		this.salary = salary;
		this.manager = manager;
	}
	public Employee(String id)
	{
	super("Barry", 29, id);
	salary = 4200;
	manager = EmployeeSystem.manager;
	}
	public String toString()
	{
		return name + " " + age + " " + id + " " + salary +  " " + manager;
	}

}
