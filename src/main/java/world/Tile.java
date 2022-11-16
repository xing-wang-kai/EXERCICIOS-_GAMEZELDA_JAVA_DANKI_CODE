package world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import zeldaclone.Game;

public class Tile {

	public static BufferedImage TILE_FLOOR = Game.spritesheet.setSprite(0, 0, 16, 16);
	public static BufferedImage TILE_WALL = Game.spritesheet.setSprite(16, 0, 16, 16);
	
	private BufferedImage sprite;
	private int x, y;
	
	public Tile(int x, int y, BufferedImage sprite)
	{
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}
	
	public void Render(Graphics graph)
	{
		graph.drawImage(sprite, this.x - Camera.x, this.y - Camera.y, 48, 48, null);
	}
}
