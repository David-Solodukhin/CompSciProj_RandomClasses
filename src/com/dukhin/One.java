package com.dukhin;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex2f;

import java.awt.Font;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.openal.AL;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.ShapeRenderer;
//DEMO FOR ONe, by TheR3d
//it's basically all in one class
//DEMO FOR ONe, by TheR3d
//it's basically all in one class

public class One {

	public static TrueTypeFont font;
	public static TrueTypeFont font2;
	public static TrueTypeFont font3;
	public static Color color = new Color(Color.white);
	public static Color color2 = new Color(Color.blue);
	public static Color textcolor = new Color(Color.white);
	float fade = 1;
	Color fader = new Color(1f, 0, 0, fade);
	String test = "_";
	int currentLine = 0;
	long time1 = System.currentTimeMillis();
	long scareTime = 1000;
	int cooldown = 0;
	static ArrayList<String> lines = new ArrayList<String>();
	public static boolean crazy = false;
	static String greeting = "";
	String helpmsg = "";
	Queue<String> myQueue = new LinkedList<String>();
	int repeat = 0;
	private Music music;
	private SpriteSheet walker;
	private Animation wAnimation;
	ArrayList<Polygon> drawings = new ArrayList<Polygon>();
	float[] polygon;
	int drawn = 0;
	int typeSpeed = 100;
	ArrayList<GameObj> objs = new ArrayList<GameObj>();
	private Sanity sanity;
	Time time = new Time();
	public One() {

		System.out.println("askfj");
		String temp = "welcome... start typing and we'll take care of the rest";
		for (int i = 0; i < temp.length(); i++) {
			myQueue.add(temp.charAt(i) + "");
		}

		
	}

	public void start() throws InterruptedException, MalformedURLException {
		try {
			Display.setDisplayMode(new DisplayMode(1280, 720));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		Font awtFont = new Font("hacked", Font.BOLD, 24);
		Font awtFont2 = new Font("Calibri", Font.BOLD, 70);
		Font awtFont3 = new Font("Lucida Console", Font.PLAIN, 15);

		font = new TrueTypeFont(awtFont, true);
		font2 = new TrueTypeFont(awtFont2, true);
		font3 = new TrueTypeFont(awtFont3, true);
		lines.add(test);
		polygon = new float[2];
		polygon[0] = Mouse.getX();
		polygon[1] = Mouse.getY();
		// wavEffect = AudioLoader.getStreamingAudio("OGG",
		// ResourceLoader.getResource("sound.ogg"));
		try {
			music = new Music("sound.ogg");
			music.setVolume(0.5f);
			music.loop();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			walker = new SpriteSheet("commins.png", 633, 398);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wAnimation = new Animation(walker, 300);
		//////////////////////GAME STUFF
		sanity = new Sanity();
		objs.add(sanity);
		objs.add(time);
		
		
		
		
		///////////////////////
		initGL(); // init OpenGL
		// while (!Display.isCloseRequested()) {
		while (Display.isActive()) {
			int delta = 0;

			update(delta);
			renderGL();

			Display.update();
			Display.sync(60); // cap fps to 60fps
		}
		System.out.println("always watching");
		Display.destroy();
		AL.destroy();
	}

	public void update(int delta) throws InterruptedException {
		////////////
		for(GameObj obj: objs)
		{
			obj.update();
		}
		
		////////////
		
		fader = new Color(1f, 0, 0, fade);
		if (fade > 0) {
			fade -= 0.01f;
		}
		Display.setTitle("They're watching");
		if (Display.isCloseRequested()) {
			greeting = "You can't leave now... we're just getting started";
			myQueue.clear();
			drawn = 0;
			sanity.sanity-=2f;
			System.out.println("oh no you don't");

		}
		if (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)
				&& Keyboard.isKeyDown(Keyboard.KEY_C)) {
			String clear = "";
			lines.set(0, lines.get(lines.size() - 1));

			for (int i = 1; i < lines.size(); i++) {
				lines.remove(i);

			}
			myQueue.clear();
			currentLine = 0;
		}
		if (Mouse.isButtonDown(0) && drawn == 0 && greeting.contains("circle")) {

			System.out.println(Arrays.toString(polygon));
			float tempX = Mouse.getX();
			float tempY = Display.getHeight() - Mouse.getY();
			int size = polygon.length;
			float[] temp = new float[size + 2];
			for (int i = 0; i < size; i++) {
				temp[i] = polygon[i];
			}

			polygon = temp;
			polygon[size] = tempX;
			polygon[size + 1] = tempY;
			polygon[0] = polygon[size];
			polygon[1] = polygon[size + 1];
		} else if (Mouse.isButtonDown(0) == false && drawn == 0
				&& polygon.length > 4) {
			drawn = 1;
		}

	}

	public void initGL() {
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDisable(GL11.GL_LIGHTING);

		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		// GL11.glClearDepth(1);

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		// GL11.glViewport(0,0,800,600);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);

		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		glOrtho(0, Display.getWidth(), Display.getHeight(), 0, -1, 1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);

	}

	public void renderGL() {
		// ///////////////////////////////////////////////// graphical setup
		glClear(GL_COLOR_BUFFER_BIT);
		glLoadIdentity(); // so it does it once
		// GL11.glViewport(0, 0, Display.getWidth(), Display.getHeight());
		// wAnimation.draw(350,100);
		
		// ///////////////////////////////////////////////////
		////////
		Polygon draw = new Polygon(polygon);
		ShapeRenderer.draw(draw);
		for(GameObj obj: objs)
		{
			obj.render();
		}
		if (fade > 0) {
			wAnimation.draw(350, 100, fader);
		}
		
		///////
		if (!textcolor.equals(color.white)) {
			cooldown++;
		}
		if (cooldown == 60) {
			cooldown = 0;
			textcolor = color.white;
			greeting = "";
			myQueue.clear();
			typeIt("I see you're acting like a child So... why not play a game with me?");
		}

		for (int i = 0; i < lines.size(); i++) {
			if(i*20+10<=Display.getHeight())
			{
			font.drawString(0, (i * 20) + 10, lines.get(i), textcolor);
			}
			else
			{
				greeting = "Press CTRL+C to clear console";
				
			}
		}
		long time2 = System.currentTimeMillis();

		if (Math.abs(time1 - time2) >= typeSpeed && myQueue.size() != 0) {

			greeting += myQueue.poll();
			time1 = time2;
		}
		font.drawString(Display.getWidth() / 2 - 100, 300, greeting, color);
		font3.drawString(0, 600, helpmsg);

		while (Keyboard.next()) {
			if (Keyboard.getEventKeyState()) {
				{
					long temp = System.currentTimeMillis();
					if (Math.abs(scareTime - temp) <= 10) {
						test += "RUN";
						font2.drawString(Display.getWidth() / 2,
								Display.getHeight() / 2, "I C U");
						if(sanity.sanity-10>=0){
						sanity.sanity-=10f;}
						textcolor = color.red;
						lines.set(currentLine, test);
					}
					if(crazy)
					{
						font2.drawString((float)Math.random() * ( Display.getWidth()  ),(float)Math.random() *  (Display.getWidth()  ),"HE C'S ALL");
					}
					if (Keyboard.getEventKey() == Keyboard.KEY_SPACE) {
						if (test.charAt(test.length() - 1) == '_') {
							test = test.substring(0, test.length() - 1);
							lines.set(currentLine, test);
						}
						test += " ";
						lines.set(currentLine, test);
					} else if (Keyboard.getEventKey() == Keyboard.KEY_RETURN) {
						
						if (test.length()!=0 && test.charAt(test.length() - 1) == '_') {
							test = test.substring(0, test.length() - 1);}
						System.out.println(test);
						reciteQuery(test);
						lines.set(currentLine, test);
						currentLine++;
						test = "";
						test += "_";

						lines.add(test);
					} else if (Keyboard.getEventKey() == Keyboard.KEY_BACK) {
						System.out.println(test.length());
						if (test.length() > 0) {
							test = test.substring(0, test.length() - 1);
							lines.set(currentLine, test);
						}
					} else if (Keyboard.getEventKey() == Keyboard.KEY_APOSTROPHE) {
						if (test.charAt(test.length() - 1) == '_') {
							test = test.substring(0, test.length() - 1);
							lines.set(currentLine, test);
						}
						test += "'";
						lines.set(currentLine, test);
					} else if (Keyboard.getEventKey() == Keyboard.KEY_COMMA) {
						if (test.charAt(test.length() - 1) == '_') {
							test = test.substring(0, test.length() - 1);
							lines.set(currentLine, test);
						}
						test += ",";
						lines.set(currentLine, test);
					} else if (Keyboard.getEventKey() == Keyboard.KEY_UP) {
						if(lines.size()>1){
						test = lines.get(currentLine-1)+"";
						lines.set(currentLine,lines.get(currentLine-1));
						
						}
					} else if (Keyboard.getEventKey() == Keyboard.KEY_PERIOD) {
						if (test.charAt(test.length() - 1) == '_') {
							test = test.substring(0, test.length() - 1);
							lines.set(currentLine, test);
						}
						test += ".";
						lines.set(currentLine, test);
					} else {
						scareTime = System.currentTimeMillis();

						if (test.length() != 0
								&& test.charAt(test.length() - 1) == '_') {
							test = test.substring(0, test.length() - 1);
							lines.set(currentLine, test);
						}
						test += (Keyboard.getKeyName(Keyboard.getEventKey()))
								+ "_";

						lines.set(currentLine, test);

					}
				}
			} else {
				{
					// System.out.println("A Key Released");
					// test+="_";
					// lines.set(currentLine, test); //put this back if
					// something goes wrong or i have an unidentifiable text
					// problem
				}
			}
		}

	}

	public static void main(String[] argv) throws InterruptedException,
			MalformedURLException {
		One Game = new One();
		Game.start();
	}

	public static void rect(float x, float y, float width, float height) {
		glPushMatrix();// happens in a unique matrix- sets the matrix for the
						// current objects. to make less relative and more
						// absolute
		{

			glTranslatef(x, y, 0);
			GL11.glColor3f(0.5f, 0.5f, 1.0f);

			glBegin(GL_QUADS); // square
			{
				glVertex2f(0, 0);
				glVertex2f(0, height);
				glVertex2f(width, height);
				glVertex2f(width, 0);
			}
			glEnd();
		}
		glPopMatrix();
	}

	public void reciteQuery(String query) {
		// a long list of conditional trees
		while(!myQueue.isEmpty())
		{
			greeting+=myQueue.poll();
		}
		myQueue.clear();
		sanity.sanity+=5;
		if (query.equals("HELLO") || query.equals("HI")) {
			greeting = "";
			typeIt("hey....    ");
			greeting = "";
			typeIt("want to play a game?");

		} else if ((query.equals("OK") || query.equals("YES")
				|| query.equals("SURE") || query.equals("FINE"))
				&& greeting.contains("game")) {
			greeting = "";

			typeIt("good, good, all in good time");
			color = new Color(color.red);
		} else if ((query.equals("OK") || query.equals("YES") || query
				.equals("SURE")) && greeting.contains("won't we")) {
			greeting = "";

			typeIt("Very good, I look forward to it.");
			color = new Color(color.red);
		} else if ((query.equals("NO") || query.equals("NEVER"))
				&& greeting.contains("game")) {
			greeting = "";
			typeIt("Aww that's too bad...");
			greeting = "";
			color = new Color(color.red);
			typeIt("We'll try AGAIN next time, won't we?");
		} else if ((query.equals("NO") || query.equals("NEVER"))
				&& greeting.contains("won't we")) {
			greeting = "";
			sanity.sanity+=10f;
			typeIt("Sometimes what you think is best for you, isn't.");
			greeting = "";
			color = new Color(color.red);

		} else if ((query.equals("TIME") || query.equals("T"))) {
			
			helpmsg = time.getTime();

		} else if ((query.equals("I DON'T WANT TO PLAY") || query
				.equals("NEVER")) && greeting.contains("CIRCLE")) {
			greeting = "";
			typeIt("Too Bad, We all have to make sacrifices");
			greeting = "";
			color = new Color(color.red);

		} else if (query.equals("WHO ARE YOU") || query.equals("WHAT ARE YOU")
				|| query.equals("WHAT IS THIS")
				|| query.equals("WHAT IS YOUR NAME")) {
			greeting = "";
			color = color2;
			try {
				String computername = InetAddress.getLocalHost().getHostName();
				if (computername.contains("PC")) {
					computername = computername.substring(0,
							computername.indexOf("PC"));
				}
				typeIt("You decide... " + computername);
			} catch (UnknownHostException e) {

				e.printStackTrace();
			}
		} else if (query.contains("LSHIFT") || query.contains("RSHIFT")) {
			greeting = "";
			color = color.red;
			typeIt("NO QUESTIONS - ONLY STATEMENTS. AFTER ALL You're still a child... ");
		} else if (query.contains("EXIT") || query.contains("LET ME")) {
			greeting = "";
			color = color.red;
			typeIt("WHY? SO YOU CAN LIVE A LITTLE LONGER?");
		} else if (query.contains("FUNNY") || query.contains("HAHA")) {
			greeting = "";
			color = color.green;
			typeIt("Thank's, I do try.");
		} else if (query.contains("PLAY A GAME")
				|| query.contains("PLAY YOUR GAME")) {
			greeting = "";
			color = color.green;
			typeIt("LIKE YOU HAD A CHOICE...");
		} else if (query.equals("WHAT DO YOU MEAN")
				|| query.contains("UNDERSTAND") || query.contains("WHAT")) {
			String temp = greeting;
			if (temp.contains("good")) {
				greeting = "";
				typeIt("I want you to draw me a circle, If i like it, i'll give you something");
			} else if (temp.contains("T.A.R.S")) {
				greeting = "";
				typeIt("Transcendental Assistance Robotic Service");
			} else {
				greeting = "What part of: " + temp + " do you not get?";
			}

		} else if (query.equals("HELP")) {
			if (repeat == 0) {
				greeting = "WELCOME TO T.A.R.S. CONSOLE";
				helpmsg = "Missing MySQL DATABASE FILE :  ERR xx3983";
				repeat = 1;
			} else {
				greeting = "he's watching, you know";
				repeat = 0;
			}
		}
	}

	public void typeIt(String answer) {
		for (int i = 0; i < answer.length(); i++) {
			myQueue.add(answer.charAt(i) + "");
		}
	}

	public boolean isTyped() {
		return myQueue.isEmpty();
	}
}