package com.dukhin;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.ShapeRenderer;
import org.newdawn.slick.geom.Transform;

public class Sanity implements GameObj{
float x = Display.getWidth()-200;
float y = 50;
float sanity = 100f;
//float[] barPoints = {x,y,x,y+16,x+sanity,y+16,x+sanity,y};
float[] barPoints = {Display.getWidth()-sanity-10,50,Display.getWidth()-sanity-10,66,Display.getWidth()-10,66,Display.getWidth()-10,50};
float doop = Display.getWidth();
Shape test = new Rectangle(100,100,200,200);
Shape bar = new Polygon(barPoints);
private float temp;
private float temp2;
public Sanity()
{
	temp = test.getCenterX();
	temp2 = test.getCenterY();
	//System.out.println(temp);
}
public void update()
{
	//System.out.println(sanity);
	if(sanity>0){
	sanity-=0.06f;}
	
	barPoints[0]=Display.getWidth()-sanity-10;
	barPoints[2]=Display.getWidth()-sanity-10;
	bar =  new Polygon(barPoints);
	if(sanity<=10)
	{
		StringBuilder noob = new StringBuilder(One.greeting);
		One.greeting = noob.reverse().toString();
		One.crazy = true;
	}
	if(sanity>100)
	{
		sanity=100;
	}
}
public void render()
{
	
	One.font.drawString(x, 44, "Sanity",Color.green); //apparently the only way i can set color :(
	
   // test = test.transform(Transform.createScaleTransform(1.005f, 1.005f));
	//test = test.transform(Transform.createRotateTransform((float) (2*3.14),temp,temp2));
	ShapeRenderer.fill(bar);
	ShapeRenderer.draw(bar);
	//ShapeRenderer.draw(test);
	
	
}
}
