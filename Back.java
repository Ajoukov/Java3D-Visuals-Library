import org.w3c.dom.css.Rect;
import java.awt.*;
import java.io.BufferedWriter;
import java.lang.Math;
import java.util.Arrays;
import java.awt.image.BufferStrategy;
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

import java.awt.*;

public class Back {


    private Game game;

    public Rectangle backButton = new Rectangle(Display.WIDTH / 2 - 390, 10, 250, 75);
    public Rectangle outline = new Rectangle(Display.WIDTH / 2 - 135, 372, 125 ,125);

    Rectangle[] buttons = new Rectangle[5];



    public Back() {
        for (int i = 0; i < 5; i++) {
            Rectangle button = new Rectangle(Display.WIDTH / 2 - 255 + i*175 + (i>2?-427:0), 130 + (i>2?230:0), 175, 150);
            buttons[i] = button;
        }
    }


    public void render (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        Font text1 = new Font("Consolas", Font.BOLD, 50);
        g.setFont(text1);
        g.setColor(Color.WHITE);

        Font fnt1 = new Font("Consolas", Font.BOLD, 65);
        g.setFont(fnt1);
        g.setColor(new Color(255,255,255));
        g.fillRect(Display.WIDTH / 2 - 390, 10, 250, 75);
        g.setColor(Color.BLACK);
        g.drawString("Back", backButton.x + 55, backButton.y + 60);
        g2d.draw(backButton);
        Font fnt2 = new Font("Consolas", Font.BOLD, 40);
        g.setFont(fnt2);
        g.setColor(Color.YELLOW);
        g.drawString("Balance:",  495,  50);
        Font fnt3 = new Font("Consolas", Font.BOLD, 20);
        g.setColor(Color.WHITE);
        g.setFont(fnt3);

        for (Rectangle b : buttons) {
            g2d.draw(b);
        }
        g2d.draw(outline);

        g.setColor(Color.yellow);
        g.fillRect(Display.WIDTH / 2 - 230, 142, 125, 125);
        g.setColor(Color.blue);
        g.fillRect(Display.WIDTH / 2 - 55, 142, 125, 125);

        String[] text = new String[5];
        for (int i = 0; i < 5; i++) {
            int price = -1;
            if (i == 1) price = 50;
            if (i == 2) price = 75;
            if (i == 4) price = 100;
            int b = Reader.get(i+2);
            switch (b) {
                case 0:  
                    text[i] = "  " + price;
                    break;
                case 1:  
                    text[i] = " Select";
                    break;
                case 2:  
                    text[i] = "Selected";
                    break; 
            }
        }

        for (int i = 0; i < 5; i++) {
            Font fnt4 = new Font("Consolas", Font.BOLD, 21);
            g.setColor(Color.WHITE);
            g.setFont(fnt4);
            g.drawString(text[i], buttons[i].x + 44, buttons[i].y + 144);
            Font fnt5 = new Font("Consolas", Font.BOLD, 20);
            g.setColor(Color.BLACK);
            g.setFont(fnt5);
            g.drawString(text[i], buttons[i].x + 45, buttons[i].y + 145);
        }
        // g.drawString(text[1], jerseyButton2.x + 62, jerseyButton2.y + 160);
        // g.drawString(text[2], jerseyButton3.x + 62, jerseyButton3.y + 160);
        // g.drawString(text[3], backgroundButton1.x + 62, backgroundButton1.y + 160);
        // g.drawString(text[4], backgroundButton2.x + 62, backgroundButton2.y + 160);
    }


    public void click(int mx, int my) {
        for (int i = 0; i < 5; i++) {
            if (mx >= buttons[i].x && mx < buttons[i].x + 175 && my >= buttons[i].y && my < buttons[i].y+150) {
                //if already purchased
                if (Reader.get(i+2) == 1) {
                    selectOwned(i);
                }
                //if not owned
                else if (Reader.get(i+2) == 0) {
                    int price = 10000000;
                    switch (i) {
                        case 1:
                            price = 50;
                            break;
                        case 2:
                            price = 75;
                            break;
                        case 4:
                            price = 100;
                            break; 
                    }
                    //sufficient funds
                    if (Reader.get(0) >= price) {
                        switch(i) {
                            case 1: 
                                Reader.save(-price, -1, -1, 2,-1,-1,-1);
                                break;
                            case 2: 
                                Reader.save(-price, -1, -1, -1,2,-1,-1);
                                break;
                            case 4: 
                                Reader.save(-price, -1, -1, -1,-1, -1, 2);
                                break;
                        }
                        selectOwned(i);
                    }
                }
            }
        }
    }

    public void selectOwned(int i) {
        if (i < 3) {
            int yellow = Reader.get(2)>0?1:0;
            int blue = Reader.get(3)>0?1:0;
            int psg = Reader.get(4)>0?1:0;
            if (i == 0) yellow = 2;
            if (i == 1) blue = 2;
            if (i == 2) psg = 2;
            Reader.save(0,-1,yellow, blue, psg, -1, -1);
            return;
        } else {
            int orig = Reader.get(5)>0?1:0;
            int wack = Reader.get(6)>0?1:0;
            if (i == 3) orig = 2;
            if (i == 4) wack = 2;
            Reader.save(0,-1,-1, -1, -1, orig, wack);
            return;
        }
    }
}
