import java.util.Arrays;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
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

public class Reader {
	

	private static int[] readFile() {
		int[] b = new int[20];
		try{
			BufferedReader reader = new BufferedReader(new FileReader("saveFile.txt"));
			b[0] = Integer.parseInt(reader.readLine());
			b[1] = Integer.parseInt(reader.readLine());
			b[2] = Integer.parseInt(reader.readLine());
			b[3] = Integer.parseInt(reader.readLine());
			b[4] = Integer.parseInt(reader.readLine());
			b[5] = Integer.parseInt(reader.readLine());
			b[6] = Integer.parseInt(reader.readLine());
			reader.close();
		}
		catch(Exception f){

		}
		return b;
	} 

	public static int get(int i) {
		int[] all = readFile();
		
		return all[i];
	}
	public static void save(int balance, int highscore, int yellow, int blue, int psg, int orig, int wack) {
		try{
			int newBal = get(0) + balance;
			int newHS = highscore > get(1) ? highscore : get(1);
			int[] old = readFile();
			BufferedWriter writer = new BufferedWriter(new FileWriter("saveFile.txt"));
			writer.write(""+newBal);
			writer.newLine();
			writer.write(""+newHS);
			writer.newLine();
			if (yellow != -1) writer.write(""+yellow);
			else {writer.write(""+old[2]);}
			writer.newLine();
			if (blue != -1) writer.write(""+blue);
			else {writer.write(""+old[3]);}
			writer.newLine();
			if (psg != -1) writer.write(""+psg);
			else {writer.write(""+old[4]);}
			writer.newLine();
			if (orig != -1) writer.write(""+orig);
			else {writer.write(""+old[5]);}
			writer.newLine();
			if (wack != -1) writer.write(""+wack);
			else {writer.write(""+old[6]);}
			writer.close();
		}
		catch(Exception f){

		}
	}
}