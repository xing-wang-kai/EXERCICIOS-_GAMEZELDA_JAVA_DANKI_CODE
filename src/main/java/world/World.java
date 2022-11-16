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
			//Pega A imagem do Mapa em resources
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			
			//define a altura e largura conforme o tamanho total do mapa
			this.WIDTH = map.getWidth();
			this.HEIGHT = map.getHeight();
			
			//constroe dois array que buscam os valores em cada local do mapa para renderizar o terrain
			int[] pixels = new int[map.getWidth()*map.getHeight()];
			tiles = new Tile[map.getWidth()*map.getHeight()];
			
			map.getRGB(0, 0, map.getWidth(), map.getHeight(), pixels, 0, map.getWidth());
			
			for(int xx = 0; xx < map.getWidth(); xx ++)
			{
				for(int yy = 0; yy < map.getHeight(); yy ++)
				{
					int dimensionX = xx*48;
					int dimensionY = yy*48;
					
					int currentPixel = pixels[xx + (yy * map.getWidth())];
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
		for(int xx = 0; xx < this.WIDTH; xx ++)
		{
			for(int yy = 0; yy < this.HEIGHT; yy ++)
			{
				Tile currentTile = tiles[xx + (yy * this.WIDTH)];
				currentTile.Render(graph);
			}
		}
	}

}
