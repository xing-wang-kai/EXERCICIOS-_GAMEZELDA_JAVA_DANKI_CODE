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
	
	private static Tile[] tiles;
	public static int WIDTH, HEIGHT;
	public static int TILE_SIZE = 48;
	
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
					int dimensionX = xx*World.TILE_SIZE;
					int dimensionY = yy*World.TILE_SIZE;
					
					int currentPixel = pixels[xx + (yy * World.WIDTH)];
					if(currentPixel == 0xFFFF0000)
					{
						tiles[xx + (yy * World.WIDTH)] = new TileFloor(dimensionX, dimensionY, Tile.TILE_FLOOR);
						Enemy enemy = new Enemy(dimensionX, dimensionY, World.TILE_SIZE, World.TILE_SIZE, Entity.ENEMY);
						Game.entity.add(enemy);
						Game.enemy.add(enemy);
					}
					else if(currentPixel == 0xFFFFFFFF)
					{
						tiles[xx + (yy * World.WIDTH)] = new TileFloor(dimensionX, dimensionY, Tile.TILE_FLOOR);
						tiles[xx + (yy * World.WIDTH)] = new TileWall(dimensionX, dimensionY, Tile.TILE_WALL);
					}
					else if(currentPixel == 0xFF0000FF)
					{
						//ammo
						tiles[xx + (yy * World.WIDTH)] = new TileFloor(dimensionX, dimensionY, Tile.TILE_FLOOR);
						Game.entity.add(new BulletAmmo(dimensionX, dimensionY, World.TILE_SIZE, World.TILE_SIZE, Entity.BULLET_PISTOL));
					}
					else if(currentPixel == 0xFF00FF00)
					{
						tiles[xx + (yy * World.WIDTH)] = new TileFloor(dimensionX, dimensionY, Tile.TILE_FLOOR);
						
						Game.player.setX(dimensionX-Camera.x);
						Game.player.setY(dimensionY-Camera.y);
					
					}
					else if(currentPixel == 0xFFFFD400)
					{
						//medickit
						tiles[xx + (yy * World.WIDTH)] = new TileFloor(dimensionX, dimensionY, Tile.TILE_FLOOR);
						Game.entity.add(new Weapon(dimensionX, dimensionY, World.TILE_SIZE, World.TILE_SIZE, Entity.WEAPON_PISTOL));
					}
					else if(currentPixel == 0xFF000000)
					{
						tiles[xx + (yy * World.WIDTH)] = new TileFloor(xx*World.TILE_SIZE, yy*World.TILE_SIZE, Tile.TILE_FLOOR);
					}
					else if(currentPixel == 0xFFFF00FA)
					{
						tiles[xx + (yy * World.WIDTH)] = new TileFloor(xx*World.TILE_SIZE, yy*World.TILE_SIZE, Tile.TILE_FLOOR);
						Game.entity.add(new MedicPackage(dimensionX, dimensionY, World.TILE_SIZE, World.TILE_SIZE, Entity.LIFE_PACKAGE));
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
		int xStart = Camera.x / World.TILE_SIZE;
		int yStart = Camera.y / World.TILE_SIZE;
		
		int xFinal = xStart +(Game.WIDTH*Game.SCALE/World.TILE_SIZE);
		int yFinal = yStart + (Game.HEIGHT*Game.SCALE/World.TILE_SIZE);
				
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
	public static boolean IsFree(int Xnext, int Ynext)
	{
		int x1 = Xnext/ World.TILE_SIZE;
		int y1 = Ynext/ World.TILE_SIZE;
		
		int x2 = (Xnext + World.TILE_SIZE - 1) /World.TILE_SIZE;
		int y2 = (Ynext + World.TILE_SIZE -1) /World.TILE_SIZE;;
		
		return !(tiles[x1 + (y1 * World.WIDTH)] instanceof TileWall ||
				 tiles[x1 + (y2 * World.WIDTH)] instanceof TileWall ||
				 tiles[x2 + (y1 * World.WIDTH)] instanceof TileWall ||
				 tiles[x2 + (y2 * World.WIDTH)] instanceof TileWall
				 );
	}

}
