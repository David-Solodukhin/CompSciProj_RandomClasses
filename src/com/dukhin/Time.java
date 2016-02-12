package com.dukhin;

public class Time implements GameObj{
	public String fTime = "11:00PM";
	int mins = 0;
	int hours = 11;
	float counter = 0;
	String areacode = "PM";
	public Time() {

	}
	public void update()
	{
		if(counter==60)
		{
			if(mins<59){
			mins+=1;}
			else
			{
				mins = 0;
				if(hours==12)
				{
					hours=1;
				}
				else{
				hours+=1;}
			}
		counter = 0;
		}
		if(mins<10)
		{
			fTime = hours+":0"+mins;
		}
		else
		{
			fTime = hours+":"+mins;
		}
		
		counter++;
	}
	public void render()
	{
		
	}
	public String getTime()
	{
		return fTime;
	}
}
