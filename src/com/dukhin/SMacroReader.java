package com.dukhin;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;


public class SMacroReader implements NativeKeyListener{
private static Integer lineNumber = 0;
static LineNumberReader br;
public static int loopVar = -1;
public static int labelGo = 0;
static Robot robot;
	public static void main(String[] args) throws AWTException {
		robot = new Robot();
		File daveCode = new File("recorder.txt");
		try {
			readFile2(daveCode);
		} catch (IOException e) {
			e.printStackTrace();
		}
		 try {
	        	robot = new Robot();
	            GlobalScreen.registerNativeHook();
	        }
	        catch (NativeHookException ex) {
	            System.err.println("There was a problem registering the native hook.");
	            System.err.println(ex.getMessage());

	            System.exit(1);
	        }

	        GlobalScreen.addNativeKeyListener(new SMacroReader());
	      

	        // Adds the appropriate listeners.
	       
	}
	private static void readFile2(File fin) throws IOException {
		FileInputStream fis = new FileInputStream(fin);
	 
		//Construct BufferedReader from InputStreamReader
		br = new LineNumberReader(new InputStreamReader(fis));
	 
		String line = null;
		//label1:
		while ((line = br.readLine()) != null) {
			if(line.contains("goto"))
			{
			
			}
			else
			{
			if(line.contains("point"))
			{
				robot.mouseMove(Integer.parseInt(untilSpaceQ(line.substring(line.indexOf("point")+5))),Integer.parseInt(line.substring(line.indexOf("\"")+1)));
			}
			else if(line.contains("pressL"))
			{
				robot.mousePress(InputEvent.BUTTON1_MASK);
				
			}
			else if(line.contains("releaseL"))
			{
				robot.mouseRelease(InputEvent.BUTTON1_MASK);
			}
			else if(line.contains("pressR"))
			{
				robot.mousePress(InputEvent.BUTTON3_MASK);
				
			}
			else if(line.contains("releaseR"))
			{
				robot.mouseRelease(InputEvent.BUTTON3_MASK);
			}
			else if(line.contains("Backspace"))
			{
				robot.keyPress(KeyEvent.VK_BACK_SPACE);
		        robot.keyRelease(KeyEvent.VK_BACK_SPACE);
		    	
			}
			else if(line.contains("Tab"))
			{
				robot.keyPress(KeyEvent.VK_TAB);
		        robot.keyRelease(KeyEvent.VK_TAB);
		    	
			}
			else if(line.contains("Control"))
			{
				robot.keyPress(KeyEvent.VK_CONTROL);
		        robot.keyRelease(KeyEvent.VK_CONTROL);
		    	
			}
			else if(line.contains("Colon"))
			{
				robot.keyPress(KeyEvent.VK_COLON);
		        robot.keyRelease(KeyEvent.VK_COLON);
		    	
			}
			else if(line.contains("SemiColon"))
			{
				robot.keyPress(KeyEvent.VK_SEMICOLON);
		        robot.keyRelease(KeyEvent.VK_SEMICOLON);
		    	
			}
			else if(line.contains("Period"))
			{
				robot.keyPress(KeyEvent.VK_SEMICOLON);
		        robot.keyRelease(KeyEvent.VK_SEMICOLON);
		    	
			}
			else if(line.contains("enter"))
			{
				robot.keyPress(KeyEvent.VK_ENTER);
		        robot.keyRelease(KeyEvent.VK_ENTER);
		    	
			}
			else if(line.contains("scroll"))
			{
				robot.mouseWheel(Integer.parseInt(line.substring(line.indexOf("scroll")+6)));
			}
			else if(line.contains("type"))
			{
				type(line.substring(line.indexOf("type")+5));
			}
			
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			}
			
		}
	 
		br.close();
	}
	 private static void type(int i)
	  {
	    robot.delay(40);
	    robot.keyPress(i);
	    robot.keyRelease(i);
	  }
	 
	  private static void type(String s)
	  {
	    byte[] bytes = s.getBytes();
	    for (byte b : bytes)
	    {
	      int code = b;
	      // CREDIT TO STACKOVERFLOW FOR BYTE DECODING
	      if (code > 96 && code < 123) code = code - 32;
	     
	      robot.keyPress(code);
	      robot.keyRelease(code);
	    }
	  }
	  public static String untilSpaceQ(String megaArg) {
			String resu = "";
			for (int i = 0; i < megaArg.length() && megaArg.charAt(i) != '"'; i++) {
				resu += megaArg.charAt(i) + "";
			}
			return resu;
		}

	@Override
	public void nativeKeyPressed(NativeKeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyCode() == NativeKeyEvent.VC_ESCAPE)
    	{
    		  try {
  				GlobalScreen.unregisterNativeHook();
  				System.out.println("Macro Executed Exited");
  				System.exit(1);
  			} catch (NativeHookException e1) {
  				// TODO Auto-generated catch block
  				e1.printStackTrace();
  			}
    
    		
    	}
		
	}
	@Override
	public void nativeKeyReleased(NativeKeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void nativeKeyTyped(NativeKeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
