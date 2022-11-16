package Entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import world.Camera;
import zeldaclone.Game;

public class Entity {
	
	public static BufferedImage ENEMY = Game.spritesheet.setSprite(48, 48, 16, 16);
	public static BufferedImage LIFE_PACKAGE = Game.spritesheet.setSprite(96, 32, 16, 16);
	public static BufferedImage WEAPON_PISTOL = Game.spritesheet.setSprite(112, 32, 16, 16);
	public static BufferedImage BULLET_PISTOL = Game.spritesheet.setSprite(128, 32, 16, 16);
	
	
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
						this.getX() - Camera.x, 
						this.getY() - Camera.y, 
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
