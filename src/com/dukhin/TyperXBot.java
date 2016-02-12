package com.dukhin;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;



/*import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.Cookie;
import com.thoughtworks.selenium.SeleniumException;
*/
public class TyperXBot {

	public static void main(String[] args) throws InterruptedException, IOException, AWTException {
		
	        Robot robot = new Robot();

	       WebDriver driver = new FirefoxDriver();

	     //  driver.get("http://playtyprx.appspot.com/#RacePlace:race");
	       driver.get("http://app.typrx.com/#RacePlace:race");
	       WebElement link;
	    //   link = driver.findElement(By.linkText("English"));
	       Thread.sleep(2000);
	       
	       WebElement searchBox;
	       searchBox = driver.findElement(By.className("GC3VYQCCNJ"));
	       searchBox.click();
	       WebElement newBox;
	       newBox = driver.findElement(By.className("gwt-TextBox"));
	       newBox.sendKeys("test@test123.com");
	       WebElement passtaBox;
	       passtaBox = driver.findElement(By.className("gwt-PasswordTextBox"));
	       passtaBox.sendKeys("password");
	       
	       robot.keyPress(KeyEvent.VK_ENTER);
	       robot.keyRelease(KeyEvent.VK_ENTER);
	       robot.keyPress(KeyEvent.VK_ENTER);
	       robot.keyRelease(KeyEvent.VK_ENTER);
	       Thread.sleep(6000);
	       driver.navigate().refresh();
	       Thread.sleep(7000);
	       String pageAsXml = driver.getPageSource();
		   int counter = 0;   
		   int words = 0;
		       ArrayList<String> dump = new ArrayList<String>();
		     //  pageAsXml = pageAsXml.replaceAll("[^a-zA-Z]+", " ");
		       String huge = "";
		       float constant = 0;
		       pageAsXml = pageAsXml.replaceAll("\\s+","");
		       for(int x = 0;x<pageAsXml.length()-5;x++)
		       {
		    	   if(pageAsXml.charAt(x)=='w' && pageAsXml.charAt(x+1)=='o' && pageAsXml.charAt(x+2)=='r' && pageAsXml.charAt(x+3)=='d')
		        	{
		        	words++;
		        	}
		       }
		       constant = (float)words/.0038f;
		       System.out.println(constant);
		       System.out.println(words);
		        for(int i = 0;i<pageAsXml.length()-5;i++)
		        {
		        	if(pageAsXml.charAt(i)=='w' && pageAsXml.charAt(i+1)=='o' && pageAsXml.charAt(i+2)=='r' && pageAsXml.charAt(i+3)=='d')
		        	{
		        		//System.out.println(pageAsXml.substring(i+12,i+25));
		        		
		        		String temp = "";
		        		int x;
		        		if(huge=="")
		        		{
		        			x = i+54;
		        		}
		        		else if(counter==1)
		        		{
		        			counter++;
		        			continue;
		        		}
		        		else
		        		{
		        			x = i+42;	
		        		}
		        				while(pageAsXml.charAt(x)!='<')
		        				{
		        					temp+=pageAsXml.charAt(x)+"";
		        					
		        					x++;
		        				}
		        				dump.add(temp+" ");
		        				huge+=temp+" ";
		        				//String tmemp = pageAsXml.substring(tmemp.lastIndexOf("word")+42,5);
		        				if(i==pageAsXml.lastIndexOf("word")){ //x
		        				//	Thread.sleep(120);
		        					 driver.findElement(By.className("cw-TypedTextBox")).sendKeys(temp.substring(0,temp.length()-1));
		        					 
		        				}
		        				else
		        				{
		        					 driver.findElement(By.className("cw-TypedTextBox")).sendKeys(temp+" ");
		        					 Thread.sleep((long)(words/240)*1000);  //milliseconds per individual word determined by rate and avg word count(5 characters rule)
		        					// Thread.sleep(60+temp.length());
		        					 //System.out.println(driver.findElement(By.className("gwt-HTML")).getText());
		        				}
		        				
		        		System.out.println(temp);
		        		counter++;
		        				//by David Solodukhin
		        	}
		        }
		      //  driver.findElement(By.className("cw-TypedTextBox")).sendKeys(huge);
	     // Thread.sleep(100);
	      
	    //  etnerButton.click();
	   Thread.sleep(11000);
	      driver.close();
	       driver.quit();
	       
	       // quotePenetration();
	        

	}
	
	    public static void quotePenetration() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
	        java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(java.util.logging.Level.OFF); /* comment out to turn off annoying htmlunit warnings */
	        WebClient webClient = new WebClient();
	    //    String url = "http://app.typrx.com/#RacePlace:race";
	        String url = "http://app.typrx.com/#PrivateRace:6v2cc";
	        System.out.println("Loading page now: "+url);
	        HtmlPage page = webClient.getPage(url);
	        webClient.waitForBackgroundJavaScript(5 * 1000); /* will wait JavaScript to execute up to 5s */

	        String pageAsXml = page.asXml();
	      

	   //     System.out.println("#FULL source after JavaScript execution:\n "+pageAsXml);
	       ArrayList<Character> dump = new ArrayList<Character>();
	     //  pageAsXml = pageAsXml.replaceAll("[^a-zA-Z]+", " ");
	       pageAsXml = pageAsXml.replaceAll("\\s+","");
	        for(int i = 0;i<pageAsXml.length()-5;i++)
	        {
	        	if(pageAsXml.charAt(i)=='w' && pageAsXml.charAt(i+1)=='o' && pageAsXml.charAt(i+2)=='r' && pageAsXml.charAt(i+3)=='d')
	        	{
	        		//System.out.println(pageAsXml.substring(i+12,i+25));
	        		String temp = "";
	        				int x = i+12;
	        				while(pageAsXml.charAt(x)!='<')
	        				{
	        					temp+=pageAsXml.charAt(x)+"";
	        					dump.add(pageAsXml.charAt(x));
	        					x++;
	        				}
	        		System.out.println(temp);
	        				//by David Solodukhin
	        	}
	        }
	    
	}

	    public static void seleniumTesting()
	    {
	    	
	    }

}

