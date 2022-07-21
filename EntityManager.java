import java.util.List;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Objects;

public class EntityManager {
	private ArrayList<IEntity> grass;
	private ArrayList<IEntity> stadiumPs;
	private ArrayList<IEntity> gamePieces;
	private IEntity	player1;
	private IEntity	player2;
	private IEntity ball;
	private Game game;

	public EntityManager() {
		this.grass = new ArrayList<IEntity>();
		this.gamePieces = new ArrayList<IEntity>();
		this.stadiumPs = new ArrayList<IEntity>();
	}
	public void setGame(Game gm) {
		this.game = gm; 
	}


	public void move(boolean right) {
		int y = 10;
		if (!right) y = -10;
		if (game.getEnd()) return;
		int c_y = (int)player1.getCenter().y;
		if (c_y == 320 || c_y == -320) game.edge();
		if ((y == 10 && c_y < 320 ) || (y == -10 && c_y > -320 )) {
			player1.move(0,y,0);
			player2.move(0,y,0);
			ball.move(0,y/2.5,0);
		}
	}


	public void createObstacle(int loc) {
		IEntity e = BasicEntityBuilder.createObstacle(loc);
		this.gamePieces.add(0, e);
	}

	public void createCloud(int loc) {
		IEntity e = BasicEntityBuilder.createCloud(loc);
		this.stadiumPs.add(0, e);
	}

	public void createDiamond(int loc) {
		IEntity e = BasicEntityBuilder.createDiamond(loc);
		this.gamePieces.add(0, e);
	}
	public void reset() {
		this.grass = new ArrayList<IEntity>();
		this.gamePieces = new ArrayList<IEntity>();
		this.stadiumPs = new ArrayList<IEntity>();
		init();
	}
	public void init() {
//		this.grass.add(BasicEntityBuilder.createPlane(new Color(92,158,58), 20000, 20000, -900));
//		this.grass.get(0).move(-10000, 0, -400);
//		this.grass.get(0).rotate(true, 0, -5, 0);
//		this.grass.add(BasicEntityBuilder.createBarrel(Color.RED, 200, -10, 0, 0));
		this.grass.add(BasicEntityBuilder.createStadium());
		player1 = BasicEntityBuilder.createPlayer1();
		player2 = BasicEntityBuilder.createPlayer2();
		ball = BasicEntityBuilder.createBall();
//		int[][][] image = BasicEntityBuilder.imageToByteArray("example", "png");
//		BasicEntityBuilder.paintImage(image);
	}
	public void createGrass() {
		this.grass.add(1, BasicEntityBuilder.createGrass());
	}


	
	/*CType prevMouse = CType.UNKNOWN;
	int initialX, initialY;
	
	public void update(Input userInput) {
		int x = this.userInput.mouse.getX();
		int y = this.userInput.mouse.getY();
		if (this.userInput.mouse.getButton() == CType.LEFT) {
			int xDif = x - initialX;
			int yDif = y - initialY;
			
			this.entityManager.rotate(true, 0.0, -yDif/1.5, -xDif/1.5);
		}	
		initialX = x;
		initialY = y;
	}*/
	public void moveEnvironment(double x, double y, double z) {
		for (int i = 1; i < grass.size(); i++) {
			IEntity e = grass.get(i);
			if (e.getEnvironment()) {
				e.move(x,y,z);
				if (e.getX() > 2000) grass.remove(i);
			}
		}
		for (int i = 0; i < stadiumPs.size(); i++) {
			IEntity e = stadiumPs.get(i);
			if (e.getEnvironment()) {
				if (e.getType().equals("cloud")) {
					e.move(x*1.5, y*1.5, z*1.5);
				}
				e.move(x,y,z);
				if (e.getX() > 2000) stadiumPs.remove(i);
			}
		}	
		for (int i = 0; i < gamePieces.size(); i++) {
			IEntity e = gamePieces.get(i);
			e.move(x,y,z);
			if (e.getType().equals("coin"))
				e.rotate(true, 0,0,1);
			if (e.getType().equals("diamond"))
				e.rotate(true, 0,0,2);
			if (e.getX() > 2000) gamePieces.remove(i);
		}
		if (game.getEnd()) {
			ball.move(-x/12, 0, 0);
		}	
	}
	
	public void createCoin(int a) {
		IEntity e = BasicEntityBuilder.createCoin(a);
		this.gamePieces.add(0, e);
	}

	public void addStadiumPiece() {
		this.stadiumPs.add(0, BasicEntityBuilder.createStadiumTop());
	}
	public void rotate(boolean CW, double xDegrees, double yDegrees, double zDegrees) {
		//entities.get(1).rotate(CW, xDegrees, yDegrees, zDegrees);
	}
	public void calculateHit() {
		Point3D a = player1.getCenter();
		double x1 = a.x;
		double y1 = a.y;
		double z1 = a.z;
		for (IEntity e : gamePieces) {
			if (e.getType().equals("enemy")) {
				Point3D b = e.getCenter();

				double x2 = b.x;
				double y2 = b.y;
				double z2 = b.z;

				double x = x1-x2;
				double y = y1-y2;
				double z = z1-z2;
	
				int distance = (int)Math.sqrt(x*x+y*y+z*z);
				if (distance < 180 && !game.getEnd()) {
					game.hit();
				} 

			}
			

		}		
		for (int i = 0; i < gamePieces.size(); i++) {
			String type = gamePieces.get(i).getType();
			if (type.equals("coin") || type.equals("diamond")) {
				IEntity e = gamePieces.get(i);

				Point3D b = e.getCenter();
	
				double x2 = b.x;
				double y2 = b.y;
				double z2 = b.z;
				double x = x1-x2;
				double y = y1-y2;
				double z = z1-z2;

				int distance = (int)Math.sqrt(x*x+y*y+z*z);
				if (distance < 300) {
					if (type.equals("coin")) {
						this.game.collectCoin();
					} else {
						this.game.collectDiamond();
					}

					this.gamePieces.remove(i);
	
				}

			}
			 

		}

	}
	public void render(Graphics g) {

//		int[][][] image = BasicEntityBuilder.imageToByteArray("stadium2", "png");
//		if (g != null) {
//			BasicEntityBuilder.paintImage(g, image, 0, 0);
//		}
		for (IEntity e : grass) {
			e.render(g);
		}
		for (IEntity e : stadiumPs) {
			if (e.getType().equals("cloud")) {
				e.render(g);
			}
			else {
				e.renderS(g);
			}
		}
		for (IEntity e : gamePieces) {
			e.renderS(g);
		}


		if (!game.getEnd()) {
			/*if ((game.getTime()) % 119 < 59) {
				int delta = (game.getTime())%119 * ((game.getTime()) % 238 < 119 ? 1 : -1);
				ball.move(0,delta/10,0);
			}*/
			int delta = (game.getTime() % 119 - 59) / 5;
			ball.rotate(true, delta, ((game.getTime() + 60) % 119)/10, 0);
			ball.move(delta*3, 0, 0);
		}



		ball.renderS(g);
		if (game.getTime() % 60 < 29) {
			player1.renderS(g);
		}
		else {
			player2.renderS(g);
		}
		/*
		if (entities.size() > 9) {		
			IEntity[] b = new IEntity[10];
			for (int i = 0; i < 9; i++) {
				b[i] = entities.get(entities.size() -1 - i);
			}
			b[9] = player;
			BasicEntityBuilder.combine(b).render(g);
		}
		else player.render(g);
		*/
	}
	public void fall() {
		if (game.getEnd()) return;
		if (Math.random() < 0.5) {
			player1 = player2;
			player2.rotate(true,0,50,0);
			player1.move(-100,0,-50);
		}
		else {
			player2 = player1;
			player1.rotate(true,0,50,0);
			player1.move(-100,0,-50);
		}
	}
}