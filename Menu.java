import org.w3c.dom.css.Rect;

import java.awt.*;

public class Menu {

    public Rectangle playButton = new Rectangle(Display.WIDTH / 2 - 125, 150, 250 ,75);
    public Rectangle saveButton = new Rectangle(Display.WIDTH / 2 - 375, 10, 200 ,75);
    public Rectangle loadButton = new Rectangle(Display.WIDTH / 2 + 185, 10, 200 ,75);
    public Rectangle shopButton = new Rectangle(Display.WIDTH / 2 - 125, 300, 250 ,75);
    public Rectangle quitButton = new Rectangle(Display.WIDTH / 2 - 125, 450, 250 ,75);
    public void render (Graphics g){

        Graphics2D g2d = (Graphics2D) g;

        Font text1 = new Font("Consolas", Font.BOLD, 70);
        g.setFont(text1);
        g.setColor(Color.BLACK);
        g.drawString("NEYMAR RUN", 210, 100);
        Font text2 = new Font("Consolas", Font.BOLD, 69);
        g.setFont(text2);
        g.setColor(Color.WHITE);
        g.drawString("NEYMAR RUN", 205, 100);

        Font fnt1 = new Font("Consolas", Font.BOLD, 65);
        g.setColor(new Color(255,255,255));
        g.fillRect(Display.WIDTH / 2 - 125, 150, 250, 75);
        g.setColor(Color.BLACK);
        g.setFont(fnt1);
        g.drawString("PLAY", playButton.x + 55, playButton.y + 60);
        g2d.draw(playButton);
        g.setColor(new Color(255,255,255));
        g.fillRect(Display.WIDTH / 2 - 125, 300, 250, 75);
        g.setColor(Color.BLACK);
        g.drawString("SHOP", shopButton.x + 55, shopButton.y + 60);
        g2d.draw(shopButton);
        g.setColor(new Color(255,255,255));
        g.fillRect(Display.WIDTH / 2 - 125, 450, 250, 75);
        g.setColor(Color.BLACK);
        g.drawString("QUIT", quitButton.x + 55, quitButton.y + 60);
        g2d.draw(quitButton);
    }
}
