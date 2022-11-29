package graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import zeldaclone.Game;

public class UserInterface {
	
	private int HPMaxWidth = 200;
	private int HPMaxHeight = 32;
	private int HPPositionX = 20;
	private int HPPositionY = 20;
	
	private BufferedImage heart;
	
	public UserInterface()
	{
		this.heart = Game.spritesheet.setSprite(144, 32, 16, 16);
	}
	
	public void Render(Graphics graph)
	{
		this.setLifeGraph(graph);
	}
	
	public void setLifeGraph(Graphics graph)
	{
		graph.setColor(Color.red);
		graph.fillRect(this.HPPositionX, this.HPPositionY, this.HPMaxWidth, this.HPMaxHeight);
		graph.setColor(Color.green);
		graph.fillRect(this.HPPositionX, this.HPPositionY, (int) ((Game.player.healingPoint/Game.player.maxHealingPoint)*this.HPMaxWidth), this.HPMaxHeight);
		
		graph.setColor(Color.white);
		graph.setFont(new Font("Arial", Font.BOLD, 26));
		graph.drawString((int) Game.player.healingPoint + " / " + (int) Game.player.maxHealingPoint, 120, 80);
		
		if(Game.player.healingPoint >= 30 && Game.player.isLive)
		{
			graph.drawImage(this.heart, (this.HPPositionX *2), 6, 48, 48 , null);
		}
		if(Game.player.healingPoint >= 60 && Game.player.isLive)
		{
			graph.drawImage(this.heart, (this.HPPositionX *2)+40, 6, 48, 48 , null);
			
		}
		if(Game.player.healingPoint >= 90 && Game.player.isLive)
		{
			graph.drawImage(this.heart, (this.HPPositionX *2)+80, 6, 48, 48 , null);
		}
		
		
	}

}
