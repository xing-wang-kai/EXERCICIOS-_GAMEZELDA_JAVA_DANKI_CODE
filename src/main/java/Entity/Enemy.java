package Entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import world.Camera;
import world.World;
import zeldaclone.Game;

public class Enemy extends Entity{
	
	public double speed = 1;
	
	private int animatorIndex = 0;
	private int timerToAnimator =0;
	private ArrayList<BufferedImage> leftMoveAnimator;
	private ArrayList<BufferedImage> rightMoveAnimator;
	private ArrayList<BufferedImage> upMoveAnimator;
	private ArrayList<BufferedImage> downMoveAnimator;
	
	private boolean isMoveRight, isMoveLeft, isMoveUp, isMoveDown;
	

	public Enemy(int x, int y, int WIDTH, int HEIGHT, BufferedImage bufferImage) {
		super(x, y, WIDTH, HEIGHT, bufferImage);
		
		this.leftMoveAnimator = new ArrayList<BufferedImage>();
		this.rightMoveAnimator = new ArrayList<BufferedImage>();
		this.upMoveAnimator = new ArrayList<BufferedImage>();
		this.downMoveAnimator = new ArrayList<BufferedImage>();
		
		this.leftMoveAnimator.add(Game.spritesheet.setSprite(32, 32, 16, 16));
		this.leftMoveAnimator.add(Game.spritesheet.setSprite(48, 32, 16, 16));
		this.leftMoveAnimator.add(Game.spritesheet.setSprite(64, 32, 16, 16));
		this.leftMoveAnimator.add(Game.spritesheet.setSprite(5*16, 32, 16, 16));
		
		this.rightMoveAnimator.add(Game.spritesheet.setSprite(32, 48, 16, 16));
		this.rightMoveAnimator.add(Game.spritesheet.setSprite(48, 48, 16, 16));
		this.rightMoveAnimator.add(Game.spritesheet.setSprite(64, 48, 16, 16));
		this.rightMoveAnimator.add(Game.spritesheet.setSprite(80, 48, 16, 16));
		
		this.upMoveAnimator.add(Game.spritesheet.setSprite(96, 48, 16, 16));
		this.upMoveAnimator.add(Game.spritesheet.setSprite(112, 48, 16, 16));
		this.upMoveAnimator.add(Game.spritesheet.setSprite(128, 48, 16, 16));
		this.upMoveAnimator.add(Game.spritesheet.setSprite(144, 48, 16, 16));
		
		this.downMoveAnimator.add(Game.spritesheet.setSprite(32, 64, 16, 16));
		this.downMoveAnimator.add(Game.spritesheet.setSprite(48, 64, 16, 16));
		this.downMoveAnimator.add(Game.spritesheet.setSprite(64, 64, 16, 16));
		this.downMoveAnimator.add(Game.spritesheet.setSprite(80, 64, 16, 16));
		
		
		
	}
	
	@Override
	public void Update()
	{
		if(Game.rand.nextInt(100) < 35)
		{
			if((int) this.x < Game.player.getX()
					&& World.IsFree((int) (this.x + this.speed), this.getY())
					&& this.IsCollider((int) (this.x + this.speed), this.getY()))
			{
				this.x+= this.speed;
				this.isMoveLeft = true;
				this.isMoveRight = false;
				this.isMoveUp = false;
				this.isMoveDown = false;
			}
			
			else if ((int) this.x > Game.player.getX()
					&& World.IsFree((int) (this.x - this.speed), this.getY())
					&& this.IsCollider((int) (this.x - this.speed), this.getY()))
			{
				this.x-= this.speed;
				this.isMoveLeft = false;
				this.isMoveRight = true;
				this.isMoveUp = false;
				this.isMoveDown = false;
			}
			
			if((int) this.y < Game.player.getY()
					&& World.IsFree(this.x, (int) (this.y+this.speed))
					&& this.IsCollider(this.x, (int) (this.y+this.speed)))
			{
				this.y += this.speed;
				this.isMoveLeft = false;
				this.isMoveRight = false;
				this.isMoveUp = false;
				this.isMoveDown = true;
				
			}
			else if((int) this.y > Game.player.getY()
					&& World.IsFree(this.x, (int) (this.y-this.speed))
					&& this.IsCollider(this.x, (int) (this.y-this.speed)))
			{
				this.y -= this.speed;
				this.isMoveLeft = false;
				this.isMoveRight = false;
				this.isMoveUp = true;
				this.isMoveDown = false;
			}
		}
		
		
		this.timerToAnimator ++;
		if(this.timerToAnimator > 4)
		{
			this.animatorIndex ++;
			if(this.animatorIndex >= this.leftMoveAnimator.size())
			{
				this.animatorIndex = 0;
			}
			this.timerToAnimator = 0;
		}
		
	}
	
	@Override
	public void Render(Graphics graph)
	{
		if( this.isMoveRight )
		{
			graph.drawImage(this.rightMoveAnimator.get(animatorIndex), 
					this.getX() - Camera.x, 
					this.getY() - Camera.y, 
					this.getWIDTH(), 
					this.getHEIGHT(), 
					null);
		}
		else if( this.isMoveLeft )
		{
			graph.drawImage(this.leftMoveAnimator.get(animatorIndex), 
					this.getX() - Camera.x, 
					this.getY() - Camera.y, 
					this.getWIDTH(), 
					this.getHEIGHT(), 
					null);
		}
		else if( this.isMoveUp)
		{
			graph.drawImage(this.upMoveAnimator.get(animatorIndex), 
					this.getX() - Camera.x, 
					this.getY() - Camera.y, 
					this.getWIDTH(), 
					this.getHEIGHT(), 
					null);
		}
		else if( this.isMoveDown)
		{
			graph.drawImage(this.downMoveAnimator.get(animatorIndex), 
					this.getX() - Camera.x, 
					this.getY() - Camera.y, 
					this.getWIDTH(), 
					this.getHEIGHT(), 
					null);
		}
	}
	
	public boolean IsCollider(int xNext, int yNext)
	{
		Rectangle current = new Rectangle(xNext, yNext, World.TILE_SIZE/2, World.TILE_SIZE/2);
		for(int i = 0; i < Game.enemy.size(); i++)
		{
			Enemy enemy= Game.enemy.get(i);
			if(enemy == this)
				continue;

			Rectangle verifyObjectCollider = new Rectangle(enemy.getX(), enemy.getY(), World.TILE_SIZE/2, World.TILE_SIZE/2);
			if(verifyObjectCollider.intersects(current))
			{
				return false;
			}
			
		}
		return true;
	}

}
