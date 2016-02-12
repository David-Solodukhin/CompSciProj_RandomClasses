package com.dukhin;

 

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Robot;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;
import org.jnativehook.mouse.NativeMouseWheelEvent;
import org.jnativehook.mouse.NativeMouseWheelListener;

public class RecorderTest implements NativeKeyListener,  NativeMouseInputListener,NativeMouseWheelListener { 
	static Robot robot;
	static PrintWriter out;
	static boolean continuing = true;
    public void nativeKeyPressed(NativeKeyEvent e) {
    	if(e.getKeyCode() == NativeKeyEvent.VC_ENTER)
    	{
    		out.println("enter");
    	
    	}
    	else if(e.getKeyCode() == NativeKeyEvent.VC_ESCAPE)
    	{
    		  try {
  				GlobalScreen.unregisterNativeHook();
  				System.out.println("Macro Successfully Created");
  				continuing = false;
  			  out.close();
  			} catch (NativeHookException e1) {
  				// TODO Auto-generated catch block
  				e1.printStackTrace();
  			}
    		
    		out.println("stop");
    		
    	}
    	else if(e.getKeyCode() == NativeKeyEvent.VC_COMMA)
    	{
    	
    		out.println("type ,");
    		
    	}
    	else if(e.getKeyCode() == NativeKeyEvent.VC_SPACE)
    	{
    		
    		out.println("type  ");
    		
    	}
    	
    	else
    	{
    	out.println("type " + NativeKeyEvent.getKeyText(e.getKeyCode())+"");
        System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
    	}

        
             
             
         
    }
    
    

    public void nativeKeyReleased(NativeKeyEvent e) {
       // System.out.println("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
    }

    public void nativeKeyTyped(NativeKeyEvent e) {
     //   System.out.println("Key Typed: " + e.getKeyText(e.getKeyCode()));
    }
    public static void main(String[] args) throws AWTException, IOException {
    	File daveCode = new File("recorder.txt");
    	out = new PrintWriter(new BufferedWriter(new FileWriter(daveCode, true)));
    	   
    	
        try {
        	robot = new Robot();
            GlobalScreen.registerNativeHook();
        }
        catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());

            System.exit(1);
        }

        GlobalScreen.addNativeKeyListener(new RecorderTest());
      

        // Adds the appropriate listeners.
        GlobalScreen.addNativeMouseListener(new RecorderTest());
        GlobalScreen.addNativeMouseWheelListener(new RecorderTest());
       
        while(continuing)
        {
        	 int tempX =MouseInfo.getPointerInfo().getLocation().x;
        	 int tempY =MouseInfo.getPointerInfo().getLocation().y;
        	out.println("point" +tempX +"\""+tempY ); //get mouse drawing info
        	
        	
        	try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        out.close();
        }



	@Override
	public void nativeMouseClicked(NativeMouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void nativeMousePressed(NativeMouseEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getButton()==1)
		{
		out.println("pressL");
		System.out.println("dkjgkgjkgj");
		}
		else if(arg0.getButton()==2)
		{
			out.println("pressR");
		}
	}



	@Override
	public void nativeMouseReleased(NativeMouseEvent arg0) {
		// TODO Auto-generated method stub
	
		if(arg0.getButton()==1)
		{
			out.println("releaseL");
		}
		else if(arg0.getButton()==2)
		{
			out.println("releaseR");
		}
	}



	@Override
	public void nativeMouseDragged(NativeMouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void nativeMouseMoved(NativeMouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void nativeMouseWheelMoved(NativeMouseWheelEvent arg0) {
		// TODO Auto-generated method stub
		
		out.println("scroll"+arg0.getWheelRotation());
	}
    }
