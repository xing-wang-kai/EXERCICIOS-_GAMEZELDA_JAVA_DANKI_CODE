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
	public static int WIDTH, HEIGHT;
	
	public World(String path) 
	{
		try 
		{
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			
			World.WIDTH = map.getWidth();
			World.HEIGHT = map.getHeight();
			
			int[] pixels = new int[World.WIDTH*World.HEIGHT];
			tiles = new Tile[World.WIDTH*World.HEIGHT];
			
			map.getRGB(0, 0, World.WIDTH, World.HEIGHT, pixels, 0, World.HEIGHT);
			
			for(int xx = 0; xx < World.WIDTH; xx ++)
			{
				for(int yy = 0; yy < World.HEIGHT; yy ++)
				{
					int dimensionX = xx*48;
					int dimensionY = yy*48;
					
					int currentPixel = pixels[xx + (yy * World.WIDTH)];
					if(currentPixel == 0xFFFF0000)
					{
						tiles[xx + (yy * World.WIDTH)] = new Tile(dimensionX, dimensionY, Tile.TILE_FLOOR);
						Game.entity.add(new Enemy(dimensionX, dimensionY, 48, 48, Entity.ENEMY));
					}
					else if(currentPixel == 0xFFFFFFFF)
					{
						tiles[xx + (yy * World.WIDTH)] = new Tile(dimensionX, dimensionY, Tile.TILE_FLOOR);
						tiles[xx + (yy * World.WIDTH)] = new Tile(dimensionX, dimensionY, Tile.TILE_WALL);
					}
					else if(currentPixel == 0xFF0000FF)
					{
						//ammo
						tiles[xx + (yy * World.WIDTH)] = new Tile(dimensionX, dimensionY, Tile.TILE_FLOOR);
						Game.entity.add(new BulletAmmo(dimensionX, dimensionY, 48, 48, Entity.BULLET_PISTOL));
					}
					else if(currentPixel == 0xFF00FF00)
					{
						tiles[xx + (yy * World.WIDTH)] = new Tile(dimensionX, dimensionY, Tile.TILE_FLOOR);
						
						Game.player.setX(dimensionX-Camera.x);
						Game.player.setY(dimensionY-Camera.y);
					
					}
					else if(currentPixel == 0xFFFFD400)
					{
						//medickit
						tiles[xx + (yy * World.WIDTH)] = new Tile(dimensionX, dimensionY, Tile.TILE_FLOOR);
						Game.entity.add(new Weapon(dimensionX, dimensionY, 48, 48, Entity.WEAPON_PISTOL));
					}
					else if(currentPixel == 0xFF000000)
					{
						tiles[xx + (yy * World.WIDTH)] = new Tile(xx*48, yy*48, Tile.TILE_FLOOR);
					}
					else if(currentPixel == 0xFFFF00FA)
					{
						tiles[xx + (yy * World.WIDTH)] = new Tile(xx*48, yy*48, Tile.TILE_FLOOR);
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
		int xStart = Camera.x / 48;
		int yStart = Camera.y / 48;
		
		int xFinal = xStart +(Game.WIDTH*Game.SCALE/48);
		int yFinal = yStart + (Game.HEIGHT*Game.SCALE/48);
				
		for(int xx = xStart; xx <= xFinal; xx++)
		{
			for(int yy = yStart; yy <= yFinal; yy++)
			{
				if( xx < 0 || yy < 0 || xx >= World.WIDTH || yy >= World.HEIGHT)
					continue;
				tiles[xx+(yy*World.WIDTH)].Render(graph);
			}
		}
		
	}

}
