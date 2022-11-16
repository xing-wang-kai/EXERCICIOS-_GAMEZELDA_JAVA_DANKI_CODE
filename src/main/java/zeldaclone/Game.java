package zeldaclone;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import Entity.Entity;
import Entity.Player;
import graphics.SpriteSheets;

public class Game extends Canvas implements Runnable{
	
	
	private static final long serialVersionUID = 1L;
	public boolean isRunning;
	public Thread thread;
	
	public JFrame frame;
	public final static int WIDTH = 300;
	public final static int HEIGHT = 180;
	public final static int SCALE = 3;
	
	public BufferedImage layout;
	
	public List<Entity> entity;
	public SpriteSheets spritesheet;
	
	
	public Game() throws IOException
	{
		
		this.layout = new BufferedImage(Game.WIDTH*Game.SCALE, Game.HEIGHT*Game.SCALE, BufferedImage.TYPE_INT_RGB);
		
		this.setPreferredSize(new Dimension(Game.WIDTH*Game.SCALE, Game.HEIGHT*Game.SCALE));
		this.SetFrame();
		
		this.entity = new ArrayList<Entity>();
		this.spritesheet = new SpriteSheets("/spriteGame.png");
		Player player = new Player(0, 0, 64, 64, this.spritesheet.setSprite(32, 0, 16, 16) );
		this.entity.add(player);
				
	}
	public static void main(String[] args) throws IOException
	{
		Game game = new Game();
		game.Start();
		
	}
	public synchronized void Start()
	{
		this.isRunning = true;
		this.thread = new Thread(this);
		this.thread.start();
	}
	public synchronized void Stop()
	{
		
	}
	
	public void Update()
	{
		entity.stream().forEach(enty -> enty.Update());
	}
	public void Render()
	{
		BufferStrategy bs = this.getBufferStrategy();
		while(bs == null)
		{
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics graph = layout.getGraphics();
		
		graph.setColor(new Color(255, 255, 255));
		graph.fillRect(0, 0, Game.WIDTH*Game.SCALE, Game.HEIGHT*Game.SCALE);
				
		graph = bs.getDrawGraphics();
					
		graph.drawImage(this.layout, 0, 0, Game.WIDTH*Game.SCALE, Game.HEIGHT*Game.SCALE, null);
		
		for(Entity en : entity)
		{
			en.Render(graph);
		}
				
		bs.show();
	}

	public void run() {
		
		long lastTime = System.nanoTime();
		double amountUpdate = 60.0f;
		double sn = 1000000000/amountUpdate;
		double delta = 0;
		
		int countFrame = 0;
		double countTimer = System.currentTimeMillis();
		
		while(isRunning)
		{
			long nowTime = System.nanoTime();
			delta+=(nowTime - lastTime)/sn;
			lastTime = nowTime;
			
			if(delta >= 1)
			{
				this.Update();
				this.Render();
				delta --;
				countFrame ++;
			}
			
			if(System.currentTimeMillis() - countTimer >= 1000)
			{
				System.out.println("FRAMES: " + countFrame);
				
				countFrame = 0;
				countTimer += 1000;
			}
						
		}
		
	}
	
	public void SetFrame() 
	{
		this.frame = new JFrame("Game Zelda Clone # by Kai Wang");
		this.frame.add(this);
		this.frame.setResizable(false);
		this.frame.pack();
		this.frame.setLocationRelativeTo(null);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setVisible(true);
	}

}
