package graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheets {
	
	public BufferedImage Sprite;
	
	public SpriteSheets(String path) 
	{
		try {
			this.Sprite = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public BufferedImage setSprite(int x, int y, int WIDTH, int HEIGHT) 
	{
		return this.Sprite.getSubimage(x, y, WIDTH, HEIGHT);
	}

}
