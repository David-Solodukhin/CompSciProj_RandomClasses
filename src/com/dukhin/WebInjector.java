package com.dukhin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
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
public class WebInjector {
private static Integer lineNumber = 0;
static LineNumberReader br;
public static int loopVar = -1;
public static int labelGo = 0;
	public static void main(String[] args) {
		File daveCode = new File("C:/Users/David/Desktop/daveCode.dave");
		try {
			readFile2(daveCode);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static void readFile2(File fin) throws IOException {
		FileInputStream fis = new FileInputStream(fin);
	 
		//Construct BufferedReader from InputStreamReader
		br = new LineNumberReader(new InputStreamReader(fis));
	 
		String line = null;
		//label1:
		while ((line = br.readLine()) != null) {
			if(line.contains("goto") && !line.contains("http") && SyntaxHandler.labels.containsKey(line.substring(line.indexOf("goto")+5)))
			{
				
				if(SyntaxHandler.labels.containsKey(line.substring(line.indexOf("goto")+5)));
				{
					System.out.println(SyntaxHandler.labels.toString());
					System.out.println(line.substring(line.indexOf("goto")+5));
			//br.setLineNumber(SyntaxHandler.labels.get(line.substring(line.indexOf("goto")+5)));
					fis = new FileInputStream(fin);
					br = new LineNumberReader(new InputStreamReader(fis));
					int lineNumbe = SyntaxHandler.labels.get(line.substring(line.indexOf("goto")+5));
					 SyntaxHandler.labels.remove(line.substring(line.indexOf("goto")+5));
					for(int i = 1;i<lineNumbe;i++)
					{
						//br.readLine();
						line = br.readLine();
						System.out.println(line);
					}
					//System.out.println(line.substring(line.indexOf("goto")+5));
		   
			//System.out.println(br.getLineNumber());
			//	br.mark(100); previous iteration
			//	br.reset();
			//break label1;
			}
			}
			else
			{
			SyntaxHandler.compile(line, lineNumber);
			//lineNumber++;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			}
		}
	 
		br.close();
	}

}
