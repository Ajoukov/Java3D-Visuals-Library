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


/*
###################################################################
Contains all game information, such as the time the game has been running, how to move the character, if it is a test game, how fast the game is going, if the game is over,
score, number of collected coins, and calculates where to place obstacles so they are not repetitive but nonetheless random. 
###################################################################
*/
public class Game {
	EntityManager entityManager;
	boolean[][] field;
	int time;
	int moveReserve;
	boolean test;
	Display display;
	int speed;
	boolean end;
	int score;
	int coins;
	int balance;
	int highscore;
	int[] obstacleLoc = {-2,-2,-2,-2,-2,-2,-2,-2,-2,-2};
	int prevCloud;
	int timeAfterHit;


	//creates game and connects to entitymanager and display
	public Game(EntityManager e, Display b) {
		this.entityManager = e;
		display = b;
		reset();
	}


	//stops player from gglitching off the side of the map
	public void edge() {
		moveReserve = 0;
	}


	//
	public void reset() {
		prevCloud = 0;
		timeAfterHit = 0;
		end = false;
		coins = 0;
		speed = 80;
		score = 0;
		field = new boolean[250][3];
		time = 0;
		moveReserve = 0;
		test = false;
		entityManager.setGame(this);
		entityManager.reset();
		while (time < 10300) {
			update();
		}
	}

	public void update() {
		time++;
		if(end){
			timeAfterHit++;
		}
		if(timeAfterHit > 120){
			display.state = STATE.Menu;
			reset();
			return;
		}
		//if (time < 10400 && time%500 == 0) {
		//	speed += 1;
		//}
		moveArena();
		moveField();
		if (moveReserve > 0) {
			entityManager.move(true);
			moveReserve--;
		}
		else if (moveReserve < 0) {
			entityManager.move(false);
			moveReserve++;
		}
		if (end && speed > 0) {
			speed--;
		}

	}
	private void moveField() {
		if (time < 10000 || end) {
			return;
		}


		if (test) {
			if (time%60 == 0) {
				field[10][1] = true;
				this.entityManager.createObstacle(0);
				this.entityManager.createCoin(-1); 
				this.entityManager.createDiamond(1); 
			}
		}
		else if (time%(60*80/speed) == 0) {
			double chanceL = 1.0;
			double chanceM = 1.0;
			double chanceR = 1.0;
			for (int i : obstacleLoc) {
				if (i == -1) chanceL -=0.50;
				if (i == 0) chanceM -=0.50;
			}
			if (Math.random() < chanceL) {
				field[0][0] = true;
				this.entityManager.createObstacle(-1);
				obstacleLoc[4] = -1;
			}
			else if (Math.random() < chanceM) {
				field[0][1] = true;
				this.entityManager.createObstacle(0);
				obstacleLoc[4] = 0;
			}
			else {
				field[0][2] = true;
				this.entityManager.createObstacle(1);
				obstacleLoc[4] = 1;
			}
			if (field[0][0] == false && Math.random() < 0.6) {
				if (Math.random() < 0.1) {					
					this.entityManager.createDiamond(-1);
				}
				else {
					this.entityManager.createCoin(-1);
				}
			}
			if (field[0][1] == false && Math.random() < 0.6) {
				if (Math.random() < 0.1) {					
					this.entityManager.createDiamond(0);
				}
				else {
					this.entityManager.createCoin(0);
				}
			}
			if (field[0][2] == false && Math.random() < 0.6) {
				if (Math.random() < 0.1) {					
					this.entityManager.createDiamond(1);
				}
				else {
					this.entityManager.createCoin(1);
				}
			}
			field[0][0] = false;
			field[0][1] = false;
			field[0][2] = false;
			for (int i = 0; i < obstacleLoc.length - 1; i++) {
				obstacleLoc[i] = obstacleLoc[i+1];
			}
			obstacleLoc[4] = -2;
		}
	}
	public void init() {
		this.entityManager.init();
	}

	public void move(boolean right) {
		if (right && moveReserve <= 0) {
			moveReserve += 32;
		}
		else if (!right && moveReserve >= 0 ) {
			moveReserve -= 32;
		}
	}
	public int getTime() {
		return time;
	}
	public int getScore() {
		return (int)Math.pow((score/80 - 10200), 1.5);
	}

	private void moveArena() {
		if (time%960 == 0) {
			if (Math.random() < 0.33 && prevCloud != -2) {
				entityManager.createCloud(-2);
				prevCloud = -2;
			}
			else if ((Math.random() < 0.33 && prevCloud != -1)) {
				entityManager.createCloud(-1);
				prevCloud = -1;
			}
			else if ((Math.random() < 0.33 && prevCloud != 1) || prevCloud == 2) {
				entityManager.createCloud(1);
				prevCloud = 1;
			}
			else {
				entityManager.createCloud(2);
				prevCloud = 2;
			}
		}
		if (Math.random() < 0.5 && !end) this.entityManager.createGrass();
		if (!end && time % (32000 / speed) == 0) this.entityManager.addStadiumPiece();
		score+=speed;
		this.entityManager.moveEnvironment(speed,0,0);
	}
	public void hit() {
		Reader.save(coins, score, -1, -1, -1, -1, -1);
		entityManager.fall();
		end = true;
	}
	public boolean getEnd() {
		return end;
	}
	public void collectCoin() {
		coins++;
	}
	public void collectDiamond() {
		coins+=5;
	}
	public int getCoins() {
		return coins;
	}
}