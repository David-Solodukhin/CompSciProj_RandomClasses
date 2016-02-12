package com.dukhin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class SyntaxHandler {
	private static Robot robot;
	private static WebDriver driver = new FirefoxDriver();
	private static WebElement currentElement;
	private static HashMap<String, WebElement> variables = new HashMap<String, WebElement>();
	public static HashMap<String, Integer> labels = new HashMap<String, Integer>();

	// private static ArrayList<Var> = new ArrayList<Var>();
	public static void compile(String args, Integer lineNumber) {
		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (args.startsWith("//")) {
			
		} else if (args.contains("goto") && args.contains("http")) // just a bunch of conditionals for
											// now
		{
			driver.get(args.substring(args.indexOf("goto") + 4));
		} else if (args.contains("find") && !args.contains("Element")) {
			if (args.contains("byText")) {
				//
				currentElement = driver.findElement(By.xpath("//*[@id='popover-search']/div/div/ul/li[1]/a/span[contains(.,'"
						+ args.substring(args.indexOf("byText") + 8, args.contains("click") ? args.indexOf("click") - 2 : args.length() - 1) + "')]"));
				if (args.contains("click")) {
					currentElement.click();
				} else if (args.contains("type") && args.indexOf('"') == -1) {
					currentElement.sendKeys(untilSpace(args.substring(args.indexOf("type") + 5)));
				} else if (args.contains("type") && args.indexOf('"') != -1) {
					currentElement.sendKeys(untilSpaceQ(args.substring(args.indexOf("type") + 6)));
				}
			} else if (args.contains("byId")) {
				currentElement = driver.findElement(By.name(untilSpace(args.substring(args.indexOf("byId") + 6))));
				if (args.contains("type") && !args.endsWith("\"")) {

					currentElement.sendKeys(untilSpace(args.substring(args.indexOf("type") + 5)));
				} else if (args.contains("type") && args.indexOf('"') != -1) {
					currentElement.sendKeys(untilSpaceQ(args.substring(args.indexOf("type") + 6)));
				}
			} else {
				currentElement = driver.findElement(By.linkText(args.substring(args.indexOf("find") + 6, args.contains("click") ? args.indexOf("click") - 2 : args.length() - 1)));
				if (args.contains("click")) {
					currentElement.click();
				}
				if (args.contains("type") && !args.endsWith("\"")) {

					currentElement.sendKeys(untilSpace(args.substring(args.indexOf("type") + 5)));
				} else if (args.contains("type") && args.indexOf('"') != -1) {
					currentElement.sendKeys(untilSpaceQ(args.substring(args.indexOf("type") + 6)));
				}

			}

		} else if (args.contains("point")) {
			String x = untilSpace(args.substring(args.indexOf("point") + 6));
			String y = untilSpace(args.substring(args.indexOf("point") + 6 + x.length() + 1));
			robot.mouseMove(Integer.parseInt(x), Integer.parseInt(y));
			if (args.contains("click")) {
				robot.mousePress(InputEvent.BUTTON1_MASK);
			}

		} else if (args.contains("reload")) {
			driver.navigate().refresh();
		} else if (args.contains("stop") || args.contains("exit")) {
			driver.close();
		} else if (args.contains("wait")) {
			try {
				Thread.sleep(Long.parseLong(untilSpace(args.substring(args.indexOf("wait") + 5))));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (args.contains("Element")) {
			if (args.contains("new")) {
				variables.put(untilSpace(args.substring(args.indexOf("Element") + 9)), currentElement);
				System.out.println("kdjhfkfj");
			} else if (args.contains("delete")) {
				currentElement = variables.get(untilSpace(args.substring(args.indexOf("Element") + 9)));
				currentElement.clear();
				variables.remove(untilSpace(args.substring(args.indexOf("Element") + 9)));
			} else if (args.contains("click")) {
				currentElement = variables.get(untilSpace(args.substring(args.indexOf("Element") + 9)));
				// System.out.println("kdjhfkfj");
				currentElement.click();
			} else if (args.contains("=")) {
				System.out.println("kdjhfkfj");
				if (args.contains("find")) {
					if (args.contains("byText")) {
						//
						currentElement = driver.findElement(By.xpath("//*[@id='popover-search']/div/div/ul/li[1]/a/span[contains(.,'"
								+ args.substring(args.indexOf("byText") + 8, args.length() - 1) + "')]"));
						variables.replace(untilSpace(args), currentElement);
					} else if (args.contains("byId")) {
						currentElement = driver.findElement(By.name(untilSpace(args.substring(args.indexOf("byId") + 6))));
						variables.replace(untilSpace(args.substring(args.indexOf("Element") + 9)), currentElement);
					} else {
						System.out.println(untilSpace(args.substring(args.indexOf("Elements") + 9)));
						currentElement = driver.findElement(By.linkText(untilSpaceQ(args.substring(args.indexOf("find") + 6))));
						variables.replace(untilSpace(args.substring(args.indexOf("Element") + 9)), currentElement);

					}

				}
			}
		}
		else if(args.endsWith(":"))
		{
			labels.put(args.substring(0,args.length()-1), WebInjector.br.getLineNumber()+1);
			//System.out.println(args.substring(0,args.length()-1));
		}
		
		if(args.contains("back"))
		{
			driver.navigate().back();
		}
		else if(args.contains("forward"))
		{
			driver.navigate().forward();
		}

	}

	public static String untilSpace(String megaArg) {
		String resu = "";
		for (int i = 0; i < megaArg.length() && megaArg.charAt(i) != ' ' && megaArg.charAt(i) != '"'; i++) {
			resu += megaArg.charAt(i) + "";
		}
		return resu;
	}

	public static String untilSpaceQ(String megaArg) {
		String resu = "";
		for (int i = 0; i < megaArg.length() && megaArg.charAt(i) != '"'; i++) {
			resu += megaArg.charAt(i) + "";
		}
		return resu;
	}
}
