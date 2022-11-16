package graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheets {
	
	public BufferedImage Sprite;
	
	public SpriteSheets(String path) throws IOException 
	{
		this.Sprite = ImageIO.read(getClass().getResource(path));
	}
	
	public BufferedImage setSprite(int x, int y, int WIDTH, int HEIGHT) 
	{
		return this.Sprite.getSubimage(x, y, WIDTH, HEIGHT);
	}

}
