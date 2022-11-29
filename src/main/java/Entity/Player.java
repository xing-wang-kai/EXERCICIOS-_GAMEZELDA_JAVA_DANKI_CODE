package Entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import graphics.SpriteSheets;
import world.Camera;
import world.World;
import zeldaclone.Game;

public class Player extends Entity{
	
	public boolean isRight, isLeft, isUp, isDown;
	public double speed;
	
	private List<BufferedImage> leftPlayer;
	private List<BufferedImage> rightPlayer;
	private List<BufferedImage> backPlayer;
	private List<BufferedImage> frontPlayer;
	
	private SpriteSheets spriteSheet = new SpriteSheets("/spriteGame.png");
	
	private int randomChoiceFrame = 0;
	private int randomChoice = 0;
	
	public double healingPoint = 100, maxHealingPoint = 100;
	public boolean isLive;

	public Player(int x, int y, int WIDTH, int HEIGHT, BufferedImage bufferImage) {
		super(x, y, WIDTH, HEIGHT, bufferImage);
		this.speed = 3;
		
		this.leftPlayer = new ArrayList<BufferedImage>();
		this.leftPlayer.add(this.spriteSheet.setSprite(32, 16, 16, 16));
		this.leftPlayer.add(this.spriteSheet.setSprite(48, 16, 16, 16));
		this.leftPlayer.add(this.spriteSheet.setSprite(64, 16, 16, 16));
		this.leftPlayer.add(this.spriteSheet.setSprite(80, 16, 16, 16));
		
		this.rightPlayer = new ArrayList<BufferedImage>();
		this.rightPlayer.add(this.spriteSheet.setSprite(32, 0, 16, 16));
		this.rightPlayer.add(this.spriteSheet.setSprite(48, 0, 16, 16));
		this.rightPlayer.add(this.spriteSheet.setSprite(64, 0, 16, 16));
		this.rightPlayer.add(this.spriteSheet.setSprite(80, 0, 16, 16));
				
		this.backPlayer = new ArrayList<BufferedImage>();
		this.backPlayer.add(this.spriteSheet.setSprite(96, 0, 16, 16));
		this.backPlayer.add(this.spriteSheet.setSprite(112, 0, 16, 16));
		this.backPlayer.add(this.spriteSheet.setSprite(128, 0, 16, 16));
		this.backPlayer.add(this.spriteSheet.setSprite(144, 0, 16, 16));
		
		this.frontPlayer = new ArrayList<BufferedImage>();
		this.frontPlayer.add(this.spriteSheet.setSprite(96, 16, 16, 16));
		this.frontPlayer.add(this.spriteSheet.setSprite(112, 16, 16, 16));
		this.frontPlayer.add(this.spriteSheet.setSprite(128, 16, 16, 16));
		this.frontPlayer.add(this.spriteSheet.setSprite(144, 16, 16, 16));
		
		this.isLive = true;
				
	}
	
	
	@Override
	public void Update() 
	{
		
		this.SetAnimatorSprite();
		this.setMoviment();
		this.setCameraMoviment();
		this.setLifeDamager();
		
	}
	@Override
	public void Render(Graphics graph)
	{
		if(this.isRight)
		{
			graph.drawImage(this.rightPlayer.get(this.randomChoice), 
					this.getX() - Camera.x, 
					this.getY() - Camera.y, 
					this.getWIDTH(), 
					this.getHEIGHT(), 
					null
					);
		}
		else if(this.isLeft)
		{
			graph.drawImage(this.leftPlayer.get(this.randomChoice), 
					this.getX() - Camera.x, 
					this.getY() - Camera.y,  
					this.getWIDTH(), 
					this.getHEIGHT(), 
					null
					);
			
		}
		else if(this.isUp)
		{
			graph.drawImage(this.backPlayer.get(this.randomChoice), 
					this.getX() - Camera.x, 
					this.getY() - Camera.y,  
					this.getWIDTH(), 
					this.getHEIGHT(), 
					null
					);
		}
		else if(this.isDown)
		{
			graph.drawImage(this.frontPlayer.get(this.randomChoice), 
					this.getX() - Camera.x, 
					this.getY() - Camera.y,  
					this.getWIDTH(), 
					this.getHEIGHT(), 
					null
					);
		}
		else
		{
			graph.drawImage(this.frontPlayer.get(0), 
					this.getX() - Camera.x, 
					this.getY() - Camera.y,  
					this.getWIDTH(), 
					this.getHEIGHT(), 
					null
					);
		}
	}
	
	public void SetAnimatorSprite()
	{
		this.randomChoiceFrame ++;
		if(this.randomChoiceFrame >= this.leftPlayer.size())
		{
			this.randomChoiceFrame = 0;
			this.randomChoice ++;
			if(this.randomChoice >= this.leftPlayer.size())
			{
				this.randomChoice = 0;
			}
		}
	}
	
	public void setMoviment()
	{
		if(isRight && World.IsFree(this.getX() + (int) this.speed, this.getY()))
		{
			this.x += this.speed;
		}
		else if (isLeft && World.IsFree(this.getX() - (int) this.speed, this.getY()) )
		{
			this.x -= this.speed;
		}
		
		if(isUp && World.IsFree(this.getX(), this.getY()- (int) this.speed))
		{
			this.y -= this.speed;
		}
		else if(isDown && World.IsFree(this.getX(), this.getY()+ (int) this.speed))
		{
			this.y += this.speed;
		}
	}
	
	public void setCameraMoviment()
	{
		Camera.x = Camera.Clamp(this.x - ((Game.WIDTH*Game.SCALE)/2), 0,  Game.WIDTH*Game.SCALE - World.WIDTH);
		Camera.y = Camera.Clamp(this.y - ((Game.HEIGHT*Game.SCALE)/2), 0, Game.HEIGHT*Game.SCALE - World.HEIGHT);
	}
	
	public void setLifeDamager()
	{
		if(this.healingPoint <= 0 )
		{
			this.healingPoint = 0;
			this.isLive = false;
			if(!this.isLive) 
			{
				System.out.println("VOCE MORREU");
			}
		}
	}
	
	public void getDamager(int Damager)
	{
		if(this.isLive)
		{
			this.healingPoint -= Damager;
			System.out.println("LIFEPOINT: " + this.healingPoint);
		}
		
	}

}
