import java.awt.event.*;



public class Keyboard implements KeyListener {
	private boolean w = false;
	private boolean a = false;
	private boolean s = false;
	private boolean d = false;
	public Keyboard() {}

    public void keyPressed(KeyEvent e) {
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

	public boolean getW() {
		return w;
	}
	public boolean getA() {
		return a;
	}
	public boolean getS() {
		return s;
	}
	public boolean getD() {
		return d;
	}

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
}