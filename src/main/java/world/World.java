package world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Entity.BulletAmmo;
import Entity.Enemy;
import Entity.Entity;
import Entity.MedicPackage;
import Entity.Weapon;
import zeldaclone.Game;

public class World {
	
	private Tile[] tiles;
	public int WIDTH, HEIGHT;
	
	public World(String path) 
	{
		try 
		{
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			
			this.WIDTH = map.getWidth();
			this.HEIGHT = map.getHeight();
			
			int[] pixels = new int[this.WIDTH*this.HEIGHT];
			tiles = new Tile[this.WIDTH*this.HEIGHT];
			
			map.getRGB(0, 0, this.WIDTH, this.HEIGHT, pixels, 0, this.HEIGHT);
			
			for(int xx = 0; xx < this.WIDTH; xx ++)
			{
				for(int yy = 0; yy < this.HEIGHT; yy ++)
				{
					int dimensionX = xx*48;
					int dimensionY = yy*48;
					
					int currentPixel = pixels[xx + (yy * this.WIDTH)];
					if(currentPixel == 0xFFFF0000)
					{
						tiles[xx + (yy * this.WIDTH)] = new Tile(dimensionX, dimensionY, Tile.TILE_FLOOR);
						Game.entity.add(new Enemy(dimensionX, dimensionY, 48, 48, Entity.ENEMY));
					}
					else if(currentPixel == 0xFFFFFFFF)
					{
						tiles[xx + (yy * this.WIDTH)] = new Tile(dimensionX, dimensionY, Tile.TILE_FLOOR);
						tiles[xx + (yy * this.WIDTH)] = new Tile(dimensionX, dimensionY, Tile.TILE_WALL);
					}
					else if(currentPixel == 0xFF0000FF)
					{
						//ammo
						tiles[xx + (yy * this.WIDTH)] = new Tile(dimensionX, dimensionY, Tile.TILE_FLOOR);
						Game.entity.add(new BulletAmmo(dimensionX, dimensionY, 48, 48, Entity.BULLET_PISTOL));
					}
					else if(currentPixel == 0xFF00FF00)
					{
						tiles[xx + (yy * this.WIDTH)] = new Tile(dimensionX, dimensionY, Tile.TILE_FLOOR);
						
						Game.player.setX(dimensionX-Camera.x);
						Game.player.setY(dimensionY-Camera.y);
					
					}
					else if(currentPixel == 0xFFFFD400)
					{
						//medickit
						tiles[xx + (yy * this.WIDTH)] = new Tile(dimensionX, dimensionY, Tile.TILE_FLOOR);
						Game.entity.add(new Weapon(dimensionX, dimensionY, 48, 48, Entity.WEAPON_PISTOL));
					}
					else if(currentPixel == 0xFF000000)
					{
						tiles[xx + (yy * this.WIDTH)] = new Tile(xx*48, yy*48, Tile.TILE_FLOOR);
					}
					else if(currentPixel == 0xFFFF00FA)
					{
						tiles[xx + (yy * this.WIDTH)] = new Tile(xx*48, yy*48, Tile.TILE_FLOOR);
						Game.entity.add(new MedicPackage(dimensionX, dimensionY, 48, 48, Entity.LIFE_PACKAGE));
					}
				}
			}
						
			
		} catch (IOException e) 
		{
			e.printStackTrace();
		}		
	}
	public void Update()
	{
		
	}
	public void Render(Graphics graph)
	{
		for (Tile tile : tiles)
		{
			tile.Render(graph);
		}
	}

}
