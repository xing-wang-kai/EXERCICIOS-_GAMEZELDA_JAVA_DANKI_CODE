package Entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import graphics.SpriteSheets;

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
				
	}
	
	
	@Override
	public void Update() 
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
		
		
		if(isRight)
		{
			this.x += this.speed;
		}
		else if (isLeft)
		{
			this.x -= this.speed;
		}
		
		if(isUp)
		{
			this.y -= this.speed;
		}
		else if(isDown)
		{
			this.y += this.speed;
		}
	}
	@Override
	public void Render(Graphics graph)
	{
		if(this.isRight)
		{
			graph.drawImage(this.rightPlayer.get(this.randomChoice), 
					this.getX(), 
					this.getY(), 
					this.getWIDTH(), 
					this.getHEIGHT(), 
					null
					);
		}
		else if(this.isLeft)
		{
			graph.drawImage(this.leftPlayer.get(this.randomChoice), 
					this.getX(), 
					this.getY(), 
					this.getWIDTH(), 
					this.getHEIGHT(), 
					null
					);
			
		}
		else if(this.isUp)
		{
			graph.drawImage(this.backPlayer.get(this.randomChoice), 
					this.getX(), 
					this.getY(), 
					this.getWIDTH(), 
					this.getHEIGHT(), 
					null
					);
		}
		else if(this.isDown)
		{
			graph.drawImage(this.frontPlayer.get(this.randomChoice), 
					this.getX(), 
					this.getY(), 
					this.getWIDTH(), 
					this.getHEIGHT(), 
					null
					);
		}
		else
		{
			graph.drawImage(this.frontPlayer.get(0), 
					this.getX(), 
					this.getY(), 
					this.getWIDTH(), 
					this.getHEIGHT(), 
					null
					);
		}
	}

}
