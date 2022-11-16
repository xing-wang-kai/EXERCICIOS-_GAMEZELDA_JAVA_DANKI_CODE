package Entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Entity {
	
	protected int x;
	protected int y;
	protected int WIDTH;
	protected int HEIGHT;
	protected BufferedImage entityImage;
	
	public Entity(int x, int y, int WIDTH, int HEIGHT, BufferedImage bufferImage)
	{
		this.x = x;
		this.y = y;
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
		this.entityImage = bufferImage; 
	}
	
	public void Update()
	{
		
	}
	public void Render(Graphics graph)
	{
		graph.drawImage(this.getEntityImage(), 
						this.getX(), 
						this.getY(), 
						this.getWIDTH(), 
						this.getHEIGHT(), 
						null
		);
	}
	
	
	public int getX() 
	{
		return x;
	}
	public int getY() 
	{
		return y;
	}
	public int getHEIGHT() 
	{
		return HEIGHT;
	}
	public int getWIDTH() 
	{
		return WIDTH;
	}
	public BufferedImage getEntityImage() {
		return entityImage;
	}
	
	public void setX(int newX)
	{
		this.x = newX;
	}
	public void setY(int newY)
	{
		this.y = newY;
	}
	public void setWIDTH(int newWIDTH)
	{
		this.WIDTH = newWIDTH;
	}
	public void setHEIGHT(int newHEIGHT)
	{
		this.HEIGHT = newHEIGHT;
	}

}
