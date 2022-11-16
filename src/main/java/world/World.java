package world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class World {
	
	private Tile[] tiles;
	public int WIDTH, HEIGHT;
	
	public World(String path) 
	{
		try 
		{
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			
			int[] pixels = new int[map.getWidth()*map.getHeight()];
			tiles = new Tile[map.getWidth()*map.getHeight()];
			this.WIDTH = map.getWidth();
			this.HEIGHT = map.getHeight();
			
			map.getRGB(0, 0, map.getWidth(), map.getHeight(), pixels, 0, map.getWidth());
			
			for(int xx = 0; xx < map.getWidth(); xx ++)
			{
				for(int yy = 0; yy < map.getHeight(); yy ++)
				{
					int currentPixel = pixels[xx + (yy * map.getWidth())];
					if(currentPixel == 0xFFFF0000)
					{
						//enemy
						tiles[xx + (yy * this.WIDTH)] = new TileFloor(xx*45, yy*27, Tile.TILE_FLOOR);
					}
					else if(currentPixel == 0xFFFFFFFF)
					{
						tiles[xx + (yy * this.WIDTH)] = new TileFloor(xx*45, yy*27, Tile.TILE_FLOOR);
						tiles[xx + (yy * this.WIDTH)] = new TileFloor(xx*45, yy*27, Tile.TILE_WALL);
					}
					else if(currentPixel == 0xFF0000FF)
					{
						//ammo
						tiles[xx + (yy * this.WIDTH)] = new TileFloor(xx*45, yy*27, Tile.TILE_FLOOR);
					}
					else if(currentPixel == 0xFF00FF00)
					{
						//player
						tiles[xx + (yy * this.WIDTH)] = new TileFloor(xx*45, yy*27, Tile.TILE_FLOOR);
					}
					else if(currentPixel == 0xFFFFD400)
					{
						//medickit
						tiles[xx + (yy * this.WIDTH)] = new TileFloor(xx*45, yy*27, Tile.TILE_FLOOR);
					}
					else if(currentPixel == 0xFF000000)
					{
						tiles[xx + (yy * this.WIDTH)] = new TileFloor(xx*45, yy*27, Tile.TILE_FLOOR);
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
