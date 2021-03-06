package com.dukhin;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Robot;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class AgarIOController implements NativeKeyListener {
	static Robot robot;
    public void nativeKeyPressed(NativeKeyEvent e) {
        System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));

        if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
            try {
				GlobalScreen.unregisterNativeHook();
				System.out.println("testi");
			} catch (NativeHookException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            //WILL CREATE ACCELERATION ON AXIS TO ALLOW FOR MORE AGILE AND FLUID MOVEMENT
            
        }
         if (e.getKeyCode() == NativeKeyEvent.VC_A) {
            try {


int tempX =MouseInfo.getPointerInfo().getLocation().x;
				robot.mouseMove(tempX-30, MouseInfo.getPointerInfo().getLocation().y);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            
            
        }
         if (e.getKeyCode() == NativeKeyEvent.VC_W) {
             try {


 int tempY =MouseInfo.getPointerInfo().getLocation().y;
 				robot.mouseMove(MouseInfo.getPointerInfo().getLocation().x,tempY-30);
 			} catch (Exception e1) {
 				// TODO Auto-generated catch block
 				e1.printStackTrace();
 			}
             
             
         }
         if (e.getKeyCode() == NativeKeyEvent.VC_D) {
             try {


 int tempX =MouseInfo.getPointerInfo().getLocation().x;
 				robot.mouseMove(tempX+30,MouseInfo.getPointerInfo().getLocation().y);
 			} catch (Exception e1) {
 				// TODO Auto-generated catch block
 				e1.printStackTrace();
 			}
             
             
         }
         
         if (e.getKeyCode() == NativeKeyEvent.VC_S) {
             try {


 int tempY =MouseInfo.getPointerInfo().getLocation().y;
 				robot.mouseMove(MouseInfo.getPointerInfo().getLocation().x,tempY+30);
 			} catch (Exception e1) {
 				// TODO Auto-generated catch block
 				e1.printStackTrace();
 			}
             
             
         }
    }
    
    

    public void nativeKeyReleased(NativeKeyEvent e) {
        System.out.println("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
    }

    public void nativeKeyTyped(NativeKeyEvent e) {
     //   System.out.println("Key Typed: " + e.getKeyText(e.getKeyCode()));
    }
    public static void main(String[] args) throws AWTException {
        try {
        	robot = new Robot();
            GlobalScreen.registerNativeHook();
        }
        catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());

            System.exit(1);
        }

        GlobalScreen.addNativeKeyListener(new AgarIOController());
    }
}