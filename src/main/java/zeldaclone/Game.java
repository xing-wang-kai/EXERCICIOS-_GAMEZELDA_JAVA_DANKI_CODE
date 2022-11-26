package zeldaclone;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import Entity.Entity;
import Entity.Player;
import graphics.SpriteSheets;
import world.World;

public class Game extends Canvas implements Runnable, KeyListener
{
	
	private static final long serialVersionUID = 1L;
	public boolean isRunning;
	public Thread thread;
	
	public JFrame frame;
	public final static int WIDTH = 200;
	public final static int HEIGHT = 150;
	public final static int SCALE = 4;
	
	public BufferedImage layout;
	
	public static List<Entity> entity;
	public static SpriteSheets spritesheet;
	public static Player player;
	private World world;
	
	
	public Game() throws IOException
	{
		addKeyListener(this);
		this.layout = new BufferedImage(Game.WIDTH*Game.SCALE, Game.HEIGHT*Game.SCALE, BufferedImage.TYPE_INT_RGB);
		
		this.setPreferredSize(new Dimension(Game.WIDTH*Game.SCALE, Game.HEIGHT*Game.SCALE));
		this.SetFrame();
		
		Game.spritesheet = new SpriteSheets("/spriteGame.png");
		
		Game.entity = new ArrayList<Entity>();
		Game.player = new Player(0, 0, 48, 48, Game.spritesheet.setSprite(32, 0, 16, 16));
		Game.entity.add(Game.player);
		
		this.world = new World("/mapa.png");
		
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
		for(Entity enty : entity)
		{
			enty.Update();
		}
		
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
		
		graph.setColor(new Color(0, 0, 0));
		graph.fillRect(0, 0, Game.WIDTH*Game.SCALE, Game.HEIGHT*Game.SCALE);
				
		graph = bs.getDrawGraphics();
					
		graph.drawImage(this.layout, 0, 0, Game.WIDTH*Game.SCALE, Game.HEIGHT*Game.SCALE, null);
		
		this.world.Render(graph);
		
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
		
		requestFocus();
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
		this.Stop();
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
	
	
	public void keyTyped(KeyEvent e) {
		// 
		
	}
	
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT
			|| e.getKeyCode() == KeyEvent.VK_D)
		{
			Game.player.isRight = true;
			Game.player.isLeft = false;
			
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT
				|| e.getKeyCode() == KeyEvent.VK_A)
		{
			Game.player.isLeft = true;
			Game.player.isRight = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP
				|| e.getKeyCode() == KeyEvent.VK_W)
		{
			Game.player.isUp = true;
			Game.player.isDown = false;
			
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN
				|| e.getKeyCode() == KeyEvent.VK_S)
		{
			Game.player.isDown = true;
			Game.player.isUp = false;
		}
		
	}
	
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT
				|| e.getKeyCode() == KeyEvent.VK_D)
			{
				Game.player.isRight = false;
				Game.player.isLeft = false;
				
			}else if(e.getKeyCode() == KeyEvent.VK_LEFT
					|| e.getKeyCode() == KeyEvent.VK_A)
			{
				Game.player.isLeft = false;
				Game.player.isRight = false;
			}
			
		if(e.getKeyCode() == KeyEvent.VK_UP
				|| e.getKeyCode() == KeyEvent.VK_W)
		{
			Game.player.isUp = false;
			Game.player.isDown = false;
			
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN
				|| e.getKeyCode() == KeyEvent.VK_S)
		{
			Game.player.isDown = false;
			Game.player.isUp = false;
		}
	}
}
