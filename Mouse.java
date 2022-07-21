import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.io.*;


public class Mouse implements MouseListener, MouseMotionListener, MouseWheelListener {

	private int mouseX = -1;
	private int mouseY = -1;
	private int mouseB = -1;
	private int scroll = 0;
	private Display display;
	private Menu menu;
	private Game game;
	private Back shop;



	public Mouse(Display d) {
		display = d;
	}

	public int getX() {
		return this.mouseX;
	}
	public int getY() {
		return this.mouseY;
	}
	public CType getButton() {
		switch(this.mouseB) {
		case 1: 
			return CType.LEFT;
		case 2: 
			return CType.SCROLL;
		case 3: 
			return CType.RIGHT;
		default:
			return CType.UNKNOWN;
		}
	}


	public void resetButton() {
		this.mouseB = -1;
	}

	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseWheelMoved(MouseWheelEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {
		//public Rectangle playButton = new Rectangle(Display.WIDTH / 2 - 125, 150, 250 ,75);
		//public Rectangle saveButton = new Rectangle(Display.WIDTH / 2 - 375, 10, 200 ,75);
		//public Rectangle loadButton = new Rectangle(Display.WIDTH / 2 + 185, 10, 200 ,75);
		//public Rectangle shopButton = new Rectangle(Display.WIDTH / 2 - 125, 300, 250 ,75);
		//public Rectangle quitButton = new Rectangle(Display.WIDTH / 2 - 125, 450, 250 ,75);
		int mx = e.getX();
		int my = e.getY();
		if (display.state == STATE.Menu && mx >= Display.WIDTH / 2 - 125 && mx <= Display.WIDTH / 2 + 125) {
			if(my >= 150 && my <= 225) {
				display.state = STATE.Game;
			}
		}
		if (display.state == STATE.Menu && mx >= Display.WIDTH / 2 - 125 && mx <= Display.WIDTH / 2 + 125) {
			if(my >= 300 && my <= 375){
				display.state = STATE.Shop;
			}
		}
		if (display.state == STATE.Menu && mx >= Display.WIDTH / 2 - 125 && mx <= Display.WIDTH / 2 + 125) {
			if(my >= 450 && my <= 525){
				System.exit(1);
			}
		}
		if (display.state == STATE.Menu && mx >= Display.WIDTH / 2 - 375 && mx <= Display.WIDTH / 2 + 375) {
			if(my >= 10 && my <= 85){
			}
		}
		if (display.state == STATE.Shop && mx >= Display.WIDTH / 2 - 390 && mx <= Display.WIDTH / 2 + 390) {
			if(my >= 10 && my <= 85){
				display.state = STATE.Menu;
			}
		}
		if (display.state == STATE.Shop) {
			display.clickShop(mx, my);
		}
		//public Rectangle buyButton1 = new Rectangle(Display.WIDTH / 2 - 255, 280, 175 ,30);
		//public Rectangle buyButton2 = new Rectangle(Display.WIDTH / 2 - 80, 280, 175 ,30);
		//public Rectangle buyButton3 = new Rectangle(Display.WIDTH / 2 + 95, 280, 175 ,30);
		//public Rectangle buyButton4 = new Rectangle(Display.WIDTH / 2 -255, 510, 175 ,30);
		//public Rectangle buyButton5 = new Rectangle(Display.WIDTH / 2 -80, 510, 175 ,30);
		//public Rectangle buyButton6 = new Rectangle(Display.WIDTH / 2 +95, 510, 175 ,30);
	}
	public void mouseReleased(MouseEvent e) {
		this.mouseB = -1;
	}
	public void mouseDragged(MouseEvent e) {
		this.mouseX = e.getX();
		this.mouseY = e.getY();
	}
	public void mouseMoved(MouseEvent e) {
		this.mouseX = e.getX();
		this.mouseY = e.getY();
	}
}