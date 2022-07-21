//import javafx.scene.Parent;

import java.awt.*;
import java.awt.image.BufferStrategy;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.JFrame;
//import javax.xml.soap.Text;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class Display extends Canvas implements Runnable {
	//create global variables
	private Thread thread;
	private JFrame frame;
	private static String title = "Neymar Run";
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	private static boolean running = false;
	private double ns;
	private boolean w = false;
	private boolean a = false;
	private boolean s = false;
	private boolean d = false;
	private BufferedImage background = null;
	private BufferedImage psg = null;
	private BufferedImage background1 = null;
	Graphics g;
	private EntityManager entityManager;
	private Mouse mouse;
	private Game game;
	private Menu menu;
	private Back shop;
	public STATE state;
	
	private boolean regularBG = true;

	//iniitalize global variables
	public Display() {
		menu = new Menu();
		shop = new Back();
		state = STATE.Menu;
		this.frame = new JFrame();
		Dimension size = new Dimension (WIDTH, HEIGHT);
		this.setPreferredSize(size);
		this.addListeners();
		this.entityManager = new EntityManager();
		this.game = new Game(entityManager, this);
	}


	public void clickShop(int mx, int my) {
		shop.click(mx, my);
	}

	//start thread 
	public synchronized void start() {
		running = true;
		this.thread = new Thread(this, "Display");
		this.thread.start();

	}
	//stop thread (unused but necessary)
	public synchronized void stop() {
		running = false;
		try {
			this.thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	//runs as often as possible
	public void run() {
		//calculations to run every 1/120 of a second
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		ns = 1000000000.0 / 120;
		double delta = 0;
		int frames = 0;
		//create game
		this.game.init();

		while (running) {
			//continuation of calculation to run every 1/120 of a second, no more, no less
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;

			while (delta >= 0) {
				update();
				this.entityManager.calculateHit();
				delta--;
			}
			//render as often as possible
			render();
			frames++;
			//write out fps in title
			if (System.currentTimeMillis() - timer > 1000) {
				timer+= 1000;
				this.frame.setTitle(title + " | " + frames + " fps");
				frames = 0;
			}

		}

	}
	//changes the speed of the game.
	//buggy so I removed all calls of this
	public void changeSpeed(int speed) {
		ns = speed;
	}

	//renders the game as often as possible
	private void render() {
		//gets buffer strategy and draws if it exists, else return
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();

		//if in menu print menu stuff
		if (state == STATE.Menu) {


			//BufferedImageLoader loader = new BufferedImageLoader();
			//background = loader.loadImage("goat.png");
			//else {background = loader.loadImage("[other image].png");}
			g.setColor(new Color(100,149,237));
			g.fillRect(-20, -20, WIDTH+40, HEIGHT+40);
			//g.drawImage(background, 0, 0, this);
			int highscore = Reader.get(1);
			String scoreText = highscore + "";
			g.setColor(Color.WHITE);
			g.setFont(new Font("Consolas",Font.BOLD, 30));
			FontMetrics metrics = getFontMetrics(g.getFont());
			g.drawString(scoreText + "", 675, 590);
			g.drawString("HIGHSCORE:", 500, 590);
			menu.render(g);
		}

		if (state == STATE.Shop){
			BufferedImageLoader loader = new BufferedImageLoader();
			background = loader.loadImage("suiiineymar.jpg");
			psg = loader.loadImage("jersey.png");
			// background1 = loader.loadImage("goat.png");
			g.setColor(new Color(100,149,237));
			g.fillRect(-20, -20, WIDTH+40, HEIGHT+40);
			g.drawImage(background, WIDTH/2 + 43, 372 , this);
			// g.drawImage(background1, 525, 372, this);
			g.setColor(new Color(255, 255, 255));
			g.fillRect(Display.WIDTH / 2 + 120, 142, 125, 125);
			g.drawImage(psg, 520, 142, this);
			int balance = Reader.get(0);
			// System.out.println(Reader.getBalance());
			FontMetrics metrics = getFontMetrics(g.getFont());
			g.setColor(Color.YELLOW);
			g.setFont(new Font("Consolas",Font.BOLD, 35));
			metrics = getFontMetrics(g.getFont());
			g.drawString(balance + "", (WIDTH - metrics.stringWidth(balance + "")-50), 50);
			shop.render(g);
		}
		//if in game print game stuff
		if (state == STATE.Game) {
			g.setColor(new Color(73, 126, 191));
			g.fillRect(-20, -20, WIDTH+40, HEIGHT+40);
			g.setColor(new Color(92,158,58));
			g.fillRect(-20, HEIGHT/2, WIDTH + 40, HEIGHT/2 +40);

			//render objects
			this.entityManager.render(g);

			//print score and coins on top
			int score = game.getScore();
			String scoreText = score + "";
			if (score > 1000000) {
				scoreText = (score/1000000) + "." + (score%1000000/10000) + "m";
			}
			else if (score > 1000) {
				scoreText = (score/1000) + "." + (score%1000/100) + "k";
			}
			g.setColor(Color.BLACK);
			g.setFont(new Font("",Font.BOLD, 50));
			FontMetrics metrics = getFontMetrics(g.getFont());
			g.drawString(scoreText, (WIDTH - metrics.stringWidth(scoreText))/2, HEIGHT/2-200);

			int coins = game.getCoins();
			g.setColor(Color.YELLOW);
			g.setFont(new Font("",Font.BOLD, 35));
			metrics = getFontMetrics(g.getFont());
			g.drawString(coins + "", (WIDTH - metrics.stringWidth(coins + "")-50), 50);
		}

 		



		g.dispose();
		bs.show();

	}

	//update game state if in game
	private void update() {
		if (state == STATE.Game) {
			game.update();
			checkKB();
		}
	}

	//check if key is pressed and move character if so
	private void checkKB() {
		if (game.getEnd()) return;
		if (a) {
			this.game.move(false);
		}
		if (d) {
			this.game.move(true);
		}
	}

	//sets frame up
	public static void main(String[] args) {
		Display display = new Display();
		display.frame.setTitle(title);
		display.frame.add(display);
		display.frame.pack();
		display.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		display.frame.setLocationRelativeTo(null);
		display.frame.setResizable(false);
		display.frame.setVisible(true);

		display.start();
	}

	//check if you dragged across the screen and rotate object. Originally used for testing so no longer needed
	/*private void checkMouse() {
		int x = this.mouse.getX();
		int y = this.mouse.getY();
		if (this.mouse.getButton() == CType.LEFT) {
			int xDif = x - initialX;
			int yDif = y - initialY;
			
			this.entityManager.rotate(true, 0.0, -yDif/1.5, -xDif/1.5);
		}	
		initialX = x;
		initialY = y;
	}*/

	//adds listeners
	private void addListeners() {
		this.mouse = new Mouse(this);
		this.addMouseListener(this.mouse);
		this.addMouseMotionListener(this.mouse);
		this.addMouseWheelListener(this.mouse);




		this.addKeyListener(new KeyListener() {
    		public void keyPressed(KeyEvent e) {
    			//s and w unused but were needed in testing
    			//stores if they were pressed and later checks their state in different function
            	switch (e.getKeyCode()) {
            		case KeyEvent.VK_W :
            			w = true;
            			break;
            		case KeyEvent.VK_A :
            			a = true;
            			break;
            		case KeyEvent.VK_S :
            			s = true;
            			break;
            		case KeyEvent.VK_D :
            			d = true;
            			break;
            	}
        	}
        	//removes from "storage"
    		public void keyReleased(KeyEvent e) {

            	switch (e.getKeyCode()) {
            		case KeyEvent.VK_W :
            			w = false;
            			break;
            		case KeyEvent.VK_A :
            			a = false;
            			break;
            		case KeyEvent.VK_S :
            			s = false;
            			break;
            		case KeyEvent.VK_D :
            			d = false;
            			break;
            	}
            }
    		public void keyTyped(KeyEvent e) {}
		});
	}


}










