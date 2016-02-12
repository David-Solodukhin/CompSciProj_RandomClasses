package com.dukhin;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
public class HumanBenchmarkBot {

	private static boolean red = true;
	private static int delp = 0;
	private static int count = 1;
	private static volatile int length =0;
	 static WebDriver driver = new FirefoxDriver();
	public static void main(String[] args) throws AWTException, InterruptedException {
	
		   Robot robot = new Robot();

		      
		    //   driver.get("http://playtyprx.appspot.com/");
		       driver.get("http://www.humanbenchmark.com/tests/reactiontime");
		       WebElement link;
		       link = driver.findElement(By.className("reaction-time-test"));
		       link.click();
		      Thread.sleep(3000);
		      link.click();
		    	 
		      
		     
		    	 // robot.mouseMove(500, 500); 
			     
		      
		
		       

	}
	public static void actual() throws AWTException, InterruptedException
	{
		   Robot robot = new Robot();

		      
		    //   driver.get("http://playtyprx.appspot.com/");
		       driver.get("http://www.humanbenchmark.com/tests/reactiontime");
		       WebElement link;
		       link = driver.findElement(By.className("reaction-time-test"));
		       link.click();
		       delp = driver.getPageSource().length();
		 
		     /*  test();
		       while(count)
		       if(length==delp-15)
		    	  {
		    		  robot.mousePress(InputEvent.BUTTON1_MASK);
		    		  robot.mouseRelease(InputEvent.BUTTON1_MASK);
		    	count = false;
		    		 // System.out.println(driver.getPageSource().length());
		    		 // robot.mousePress(InputEvent.BUTTON1_MASK);
				      // robot.mouseRelease(InputEvent.BUTTON1_MASK);
				       
		    	  }
		       while(length>=delp)
		       {
		    	   length = driver.getPageSource().length();
		    	   if(length<delp)
		    	   {
		    		   robot.mousePress(InputEvent.BUTTON1_MASK);
		    	 		  robot.mouseRelease(InputEvent.BUTTON1_MASK);
		    	   }
		       }
		       robot.mousePress(InputEvent.BUTTON1_MASK);
	 		  robot.mouseRelease(InputEvent.BUTTON1_MASK);
	 		  */
		      while(red)
		      {
		    	
		    	  if(count%2!=0 && driver.getPageSource().length()+15==delp)
		    	  {
		    		link.click();
		    		//  robot.mousePress(InputEvent.BUTTON1_MASK);
		    		 // robot.mouseRelease(InputEvent.BUTTON1_MASK);
		    		  break;
		    		 // System.out.println(driver.getPageSource().length());
		    		 // robot.mousePress(InputEvent.BUTTON1_MASK);
				      // robot.mouseRelease(InputEvent.BUTTON1_MASK);
				       
		    	  }
		    	  count++;
		    	  }
		    	 
		      
		     
		    	 // robot.mouseMove(500, 500); 
			     
		      
		
		       
	}
	private static void test() {
		   new Thread(new Runnable() 
		    { 
		      public void run() 
		      { 
		    	  while(red)
			      {
			    	
			    	  length=driver.getPageSource().length();
			    	  
			    		 
			    		  
			    		 // System.out.println(driver.getPageSource().length());
			    		 // robot.mousePress(InputEvent.BUTTON1_MASK);
					      // robot.mouseRelease(InputEvent.BUTTON1_MASK);
					       
			    	  
			    	
			      }
		      } 
		    }).start(); 
		
	}

}
