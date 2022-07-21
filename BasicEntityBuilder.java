import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.IOException;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.image.BufferedImage;


public class BasicEntityBuilder {

/*

################################################################
Creates IEntity objects (sometimes from other basic ones like rectangles, etc.) for EntityManager to use
################################################################


*/



	//Tried to print image but too laggy
/*	public static void paintImage(Graphics g, int[][][] image, int x1, int y1) {
    	int numPixelsInRow = image[0].length;
    	int numPixelsInCol = image.length;
    	for(int x = 0; x < numPixelsInCol; x+=2) {
      		for(int y = 0; y < numPixelsInRow; y+=2) {
        		int r2 = image[x][y][0];
	        	int g2 = image[x][y][1];
        		int b2 = image[x][y][2];
        		if (!(r2 == 255 && g2 == 0)) {
        			g.setColor(new Color(r2,g2,b2));
        			g.fillRect(y1 + y-2, x1 + x, 2, 2);
        		}
      		}
    	}
  	}

	//converts png to 3D array (stole from the modified smoother project)


	public static int[][][] imageToByteArray(String a, String b){
    File file = new File(a + "." + b);
    BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
    try {
        image = ImageIO.read(file);
    } catch (IOException e) {
        e.printStackTrace();
    }
    int p = image.getWidth();     
    int q = image.getHeight();      
    int r = 3;
    int[][][] data = new int[q][p][r];
    for(int y = 0; y < image.getHeight(); y++){
      for(int x = 0; x < image.getWidth(); x++){
        int px = image.getRGB(x,y);
        int red = (px >> 16) & 0xFF;
        int green = (px >> 8) & 0xFF;
        int blue = px & 0xFF;
        data[y][x][0] = red;
        data[y][x][1] = green;
        data[y][x][2] = blue;
      }
    }
    return data;
  }


*/


	//Create objects

	public static IEntity createGrass() {
		int seed = 40;
		while (3100 < seed*100 && seed*100 < 4900) {
			seed = (int)(Math.random() * 80);
		}
		int seed2 = (int)(Math.random() * 30)+10;
		IEntity grass1 = createLongRect(new Color(72, 128, 38), 10, 0, 0, 0, 6);
		IEntity grass2 = createLongRect(new Color(72, 128, 38), 10, 0, 0, 0, 6);
		IEntity grass3 = createLongRect(new Color(72, 128, 38), 10, 0, 0, 0, 6);
		IEntity grass4 = createLongRect(new Color(72, 128, 38), 10, 0, 0, 0, 6);
		IEntity grass5 = createLongRect(new Color(72, 128, 38), 10, 0, 0, 0, 6);
		IEntity shadow = createPlane(new Color(88,152,54), 200, 200, 0);
		shadow.rotate(true, 0, -10, 0);
		grass1.rotate(true, 0, 70, seed+72-seed2);
		grass2.rotate(true, 0, 70, seed+144);
		grass3.rotate(true, 0, 70, seed+216+seed/12);
		grass4.rotate(true, 0, 70, seed+288+seed2);
		grass5.rotate(true, 0, 70, seed);
		IEntity allGrass = combine(grass1, grass2, grass3, grass4, grass5);
		allGrass.setShadow(shadow);
		allGrass.move(-20000, -4000 + seed*100, -399);
		allGrass.makeEnvironment();
		allGrass.setType("grass");
		return allGrass;
	}


	public static IEntity createPlayer(boolean enemy) {
		IEntity shadow = createPlane(new Color(78,140,54), 100, 100, -100);
		shadow.move(100,0,0);
		shadow.rotate(true, 0, 20, 0);
		Color shade = new Color(199,132,90);
		IEntity head = createBarrel(shade, 100, 0,0,0);
		Color jersey = enemy ? Color.BLUE : Color.YELLOW;
		IEntity body = createBarrel(jersey, 45,0,0,-50);
		body.move(0,0,-50);
		IEntity rArm = createBarrel(shade, 10, 0, 50, 10);
		rArm.rotate(false, 15, -20, 0);
		rArm.move(0,-23,-70);
		IEntity lArm = createBarrel(shade, 10, 0, -50, 10);
		lArm.rotate(true, 15, -20, 0);
		lArm.move(0,23,-70);
		IEntity rLeg = createBarrel(shade, 10, 0, 50, 10);
		rLeg.rotate(false, 15, -20, 0);
		rLeg.move(0,-35,-93);
		IEntity lLeg = createBarrel(shade, 10, 0, -50, 10);
		lLeg.rotate(true, 15, -20, 0);
		lLeg.move(0,35,-93);


		IEntity player = combine(head, body, lArm, rArm, lLeg, rLeg);
		player.setShadow(shadow);
		player.setType("enemy");
		player.move(-100, 0, -200);

		return player;
	}
	public static IEntity createPlayer1() {
		Color shade = new Color(199,132,90);
		IEntity head = createBarrel(shade, 100, 0,0,0);
		IEntity hair = createLongRect(new Color(251,231,161), 35, 50, 0, -50, 2.5);
		hair.rotate(true, 0, 90, 0);
		hair.move(10,0,85);
		IEntity body = createBarrel(Color.YELLOW, 45,0,0,-30);
		body.move(0,0,-65);
		IEntity rArm = createBarrel(shade, 10, 0, 50, 10);
		rArm.rotate(false, 15, 70, 0);
		rArm.move(0,-23,-70);
		IEntity lArm = createBarrel(shade, 10, 0, -50, 10);
		lArm.rotate(true, 15, 40, 0);
		lArm.move(0,23,-70);
		IEntity rLeg = createBarrel(shade, 10, 0, 50, 10);
		rLeg.rotate(false, 15, -60, 0);
		rLeg.move(0,-35,-93);
		IEntity lLeg = createBarrel(shade, 10, 0, -50, 10);
		lLeg.rotate(true, 15, -60, 0);
		lLeg.move(0,35,-93);


		IEntity player = combine(head, body, lArm, rArm, lLeg, rLeg, hair);
		player.move(-100, 0, -200);
		player.setType("player");
		IEntity shadow = createPlane(new Color(78,140,54), 100, 200, -250);
		shadow.move(50,0,0);
		shadow.rotate(true, 0, 0, -90);
		player.setShadow(shadow);

		return player;
	}
	public static IEntity createPlayer2() {
//		return createCube(200, -1000, 200, -200);

		Color shade = new Color(199,132,90);
		IEntity head = createBarrel(shade, 100, 0,0,0);
		//IEntity hair1 = createLongRect(new Color(251,231,161), 25, 50, 0, -40, 2.5);
		IEntity hair = createLongRect(new Color(251,231,161), 35, 50, 0, -50, 2.5);
		//IEntity hair3 = createLongRect(new Color(251,231,161), 25, 47, 0, -70, 1.75);
		//hair3.rotate(true, 0, 47, 0);
		//hair3.move(10,0,85);
		hair.rotate(true, 0, 90, 0);
		hair.move(10,0,85);
		IEntity body = createBarrel(Color.YELLOW, 45,0,0,-30);
		body.move(0,0,-65);
		IEntity rArm = createBarrel(shade, 10, 0, 50, 10);
		rArm.rotate(false, 15, -70, 0);
		rArm.move(0,-23,-70);
		IEntity lArm = createBarrel(shade, 10, 0, -50, 10);
		lArm.rotate(true, 15, -40, 0);
		lArm.move(0,23,-70);
		IEntity rLeg = createBarrel(shade, 10, 0, 50, 10);
		rLeg.rotate(false, 15, 60, 0);
		rLeg.move(0,-35,-93);
		IEntity lLeg = createBarrel(shade, 10, 0, -50, 10);
		lLeg.rotate(true, 15, 60, 0);
		lLeg.move(0,35,-93);


		IEntity player = combine(head, body, lArm, rArm, lLeg, rLeg, hair);
		player.move(-100, 0, -200);
		player.setType("player");
		IEntity shadow = createPlane(new Color(78,140,54), 100, 200, -250);
		shadow.move(50,0,0);
		shadow.rotate(true, 0, 0, -90);
		player.setShadow(shadow);

		return player;
	}

	public static IEntity combine(IEntity... a) {
		ArrayList<MyPolygon> tempList = new ArrayList<MyPolygon>();
		for (IEntity e : a) {
			tempList.addAll(Arrays.asList(e.getPolygons()));
		}
		MyPolygon[] polygons = new MyPolygon[tempList.size()];
		tempList.toArray(polygons);
		MyPolygon.sortPolygons(polygons);
		Tetrahedron tetra = new Tetrahedron(polygons);
		ArrayList<Tetrahedron> d = new ArrayList<Tetrahedron>();
		d.add(tetra);

		Point3D zero = new Point3D(0,0,0);
		
		Entity entity = new Entity(d, zero);
		return entity;
	}

	public static IEntity createStadium() {
		IEntity rWall = createPlane(new Color(96, 98, 107), 2000000, 10000, -400);
		rWall.rotate(false, 20, 0, 0);
		rWall.move(-1050000, 20000 , 1000 );
		IEntity lWall = createPlane(new Color(96, 98, 107), 2000000, 10000, -400);
		lWall.rotate(false, -20, 0, 0);
		lWall.move(-1050000, -20000 , 1000 );
		IEntity lWallV = createPlane(new Color(62, 64, 71), 2000000, 10000, -400);
		lWallV.rotate(false, 90, 0, 0);
		lWallV.move(-1090000, -30000 , 3000 );
		IEntity rWallV = createPlane(new Color(62, 64, 71), 2000000, 10000, -400);
		rWallV.rotate(false, -90, 0, 0);
		rWallV.move(-1090000, 30000 , 3000 );


		IEntity total = combine(rWall, lWall, lWallV, rWallV);
		total.setType("stadium");
		return total;
	}

	public static IEntity createCoin(int a) {
		ArrayList<Tetrahedron> tetras = new ArrayList<Tetrahedron>();
		int s = 200;
		int e = 16;
		double inFactor = 0.6;
		Point3D[] outerPoints1 = new Point3D[e];
		Point3D[] innerPoints1 = new Point3D[e];
		Point3D[] outerPoints2 = new Point3D[e];
		Point3D[] innerPoints2 = new Point3D[e];
		for (int i = 0; i < e; i++) {
			double theta = 2*Math.PI / e * i;
			double xPos = -Math.sin(theta)*s/2;
			double yPos = Math.cos(theta)*s/2;
			double zPos = s/2;
			outerPoints1[i] = new Point3D(xPos,yPos,zPos*inFactor/8);
			innerPoints1[i] = new Point3D(xPos*inFactor,yPos*inFactor,zPos/8);
			outerPoints2[i] = new Point3D(xPos,yPos,-zPos*inFactor/8);
			innerPoints2[i] = new Point3D(xPos*inFactor,yPos*inFactor,-zPos/8);
		}


		MyPolygon[] polygons = new MyPolygon[3*e+2];

		for (int i = 0; i < e; i++) {
			polygons[i] = new MyPolygon(outerPoints1[i], outerPoints2[i], outerPoints2[(i+1)%e], outerPoints1[(i+1)%e]);
		}
		for (int i = 0; i < e; i++) {
			polygons[i + e] = new MyPolygon(outerPoints1[i], outerPoints1[(i+1)%e], innerPoints1[(i+1)%e], innerPoints1[i]);
		}
		for (int i = 0; i < e; i++) {
			polygons[i + 2*e] = new MyPolygon(outerPoints2[i], outerPoints2[(i+1)%e], innerPoints2[(i+1)%e], innerPoints2[i]);
		}
		polygons[e*3] = new MyPolygon(innerPoints1);
		polygons[e*3+1] = new MyPolygon(innerPoints2);
		Tetrahedron tetra = new Tetrahedron(new Color(212, 175, 55), polygons);
		tetra.sortPolygons();
		tetras.add(tetra);
		Point3D b = new Point3D(0,0,0);
		IEntity ret = new Entity(tetras, b);
		ret.rotate(true, 0,90,0);
		IEntity shadow = createPlane(new Color(78,140,54), 250, 150, -250);
		shadow.rotate(true, 0, 0, -90);
		ret.setShadow(shadow);
		ret.setType("coin");
		ret.move(-51250,a*320,-150);
		return ret;
	}

	public static IEntity createStadiumTop() {
		IEntity lWallT = createLongRect(Color.GRAY, 2000, 0, 0, 0, 14);
		if (Math.random() < 0.4) {
			IEntity flag = createPlane(Math.random() < 0.7?new Color(255,223,0):new Color(0,156,59), 3000, 5000, 5000);
			flag.rotate(true, 90, 0, 0);
			flag.move(-3300,-2000, 0);
			lWallT = combine(lWallT, flag);
		}
		lWallT.move(0, -20000 , 0 );
		IEntity rWallT = createLongRect(Color.GRAY, 2000, 0, 0, 0, 14);
		if (Math.random() < 0.4) {
			IEntity flag = createPlane(Math.random() < 0.7?new Color(255,223,0):new Color(0,156,59), 3000, 5000, 5000);
			flag.rotate(true, -90, 0, 0);
			flag.move(-3300,2000, 0);
			rWallT = combine(rWallT, flag);
		}
		rWallT.move(0, 20000 , 0 );
		IEntity total = combine(lWallT, rWallT);
		
		total.rotate(false, 0, -90, 0);
		total.move(-700000, 0, 6000);
		total.makeEnvironment();

		total.setType("stadiumTop");
		return total;
	}

	public static IEntity createLongRect(Color c, double s, double x, double y, double z, double m) {
		Point3D p1 = new Point3D(x + s/2, y - s/2, z);
		Point3D p2 = new Point3D(x + s/2, y + s/2, z);
		Point3D p3 = new Point3D(x + s/2, y + s/2, z + s*m);
		Point3D p4 = new Point3D(x + s/2, y - s/2, z + s*m);
		Point3D p5 = new Point3D(x - s/2, y - s/2, z);
		Point3D p6 = new Point3D(x - s/2, y + s/2, z);
		Point3D p7 = new Point3D(x - s/2, y + s/2, z + s*m);
		Point3D p8 = new Point3D(x - s/2, y - s/2, z + s*m);
		Tetrahedron tetra = new Tetrahedron(
			new MyPolygon(c, p1, p2, p3, p4),
			new MyPolygon(c, p5, p6, p7, p8),
			new MyPolygon(c, p1, p2, p6, p5),
			new MyPolygon(c, p1, p5, p8, p4),
			new MyPolygon(c, p2, p6, p7, p3),
			new MyPolygon(c, p4, p3, p7, p8));
		ArrayList<Tetrahedron> a = new ArrayList<Tetrahedron>(1);
		a.add(tetra);
		Point3D b = new Point3D(x,y,z);
		return new Entity(a, b);
	}	

	public static IEntity createCube(Color c, double s, double x, double y, double z) {
		Point3D p1 = new Point3D(x + s/2, y - s/2, z - s/2);
		Point3D p2 = new Point3D(x + s/2, y + s/2, z - s/2);
		Point3D p3 = new Point3D(x + s/2, y + s/2, z + s/2);
		Point3D p4 = new Point3D(x + s/2, y - s/2, z + s/2);
		Point3D p5 = new Point3D(x - s/2, y - s/2, z - s/2);
		Point3D p6 = new Point3D(x - s/2, y + s/2, z - s/2);
		Point3D p7 = new Point3D(x - s/2, y + s/2, z + s/2);
		Point3D p8 = new Point3D(x - s/2, y - s/2, z + s/2);
		Tetrahedron tetra = new Tetrahedron(
			new MyPolygon(c, p1, p2, p3, p4),
			new MyPolygon(c, p5, p6, p7, p8),
			new MyPolygon(c, p1, p2, p6, p5),
			new MyPolygon(c, p1, p5, p8, p4),
			new MyPolygon(c, p2, p6, p7, p3),
			new MyPolygon(c, p4, p3, p7, p8));
		ArrayList<Tetrahedron> a = new ArrayList<Tetrahedron>(1);
		a.add(tetra);
		Point3D b = new Point3D(x,y,z);
		IEntity ret = new Entity(a,b);
		return ret;
	}
	public static IEntity createCube(double s, double x, double y, double z) {
		return createCube(Color.MAGENTA, s, x, y , z);
	}	

	public static IEntity createBall() {
		IEntity ball = createCube(Color.WHITE, 20, 0, 0, 0);
		IEntity shadow = createPlane(new Color(78,140,54), 100, 30, -5);
		shadow.move(100, 0, 0);
		ball.setShadow(shadow);
		ball.move(420, 0, -120);
		ball.setType("ball");
		return ball;
	}
	public static IEntity createObstacle(int loc) {

		IEntity ret = createPlayer(true);
		ret.move(-50000,loc*320,50);
		ret.rotate(true, 0,0,180);
		ret.makeEnvironment();
		ret.setType("enemy");
		/*

		int x = 0, y = 0, z = 0, s = 200;
		Point3D p1 = new Point3D(x + s/2, y - s/2, z - s/2);
		Point3D p2 = new Point3D(x + s/2, y + s/2, z - s/2);
		Point3D p3 = new Point3D(x + s/2, y + s/2, z + s/2);
		Point3D p4 = new Point3D(x + s/2, y - s/2, z + s/2);
		Point3D p5 = new Point3D(x - s/2, y - s/2, z - s/2);
		Point3D p6 = new Point3D(x - s/2, y + s/2, z - s/2);
		Point3D p7 = new Point3D(x - s/2, y + s/2, z + s/2);
		Point3D p8 = new Point3D(x - s/2, y - s/2, z + s/2);
		Tetrahedron tetra = new Tetrahedron(
			new MyPolygon(Color.MAGENTA, p1, p2, p3, p4),
			new MyPolygon(Color.RED, p5, p6, p7, p8),
			new MyPolygon(Color.BLUE, p1, p2, p6, p5),
			new MyPolygon(Color.WHITE, p1, p5, p8, p4),
			new MyPolygon(Color.ORANGE, p2, p6, p7, p3),
			new MyPolygon(Color.YELLOW, p4, p3, p7, p8));
		ArrayList<Tetrahedron> a = new ArrayList<Tetrahedron>(1);
		a.add(tetra);
		Point3D b = new Point3D(x,y,z);
		IEntity ret = new Entity(a,b);
		ret.makeEnvironment();
		ret.move(-20000,loc*500,-300);*/
		return ret;
	}	
	public static IEntity createPlane(Color c, double x, double y, double z) {
		Point3D p1 = new Point3D(x/2, -y/2, z);
		Point3D p2 = new Point3D(x/2, y/2, z);
		Point3D p3 = new Point3D(-x/2, y/2, z);
		Point3D p4 = new Point3D(-x/2, -y/2, z);
		Tetrahedron tetra = new Tetrahedron(new MyPolygon(c, p1, p2, p3, p4));
		ArrayList<Tetrahedron> a = new ArrayList<Tetrahedron>(1);
		a.add(tetra);
		Point3D b = new Point3D(0,0,0);
		return new Entity(a, b);
	}

	public static IEntity createAxis() {

		Point3D x1 = new Point3D(-1000, 2, 0);
		Point3D x2 = new Point3D(1000, -2, 0);
		Point3D x3 = new Point3D(-1000, -2, 0);
		Point3D x4 = new Point3D(1000, 2, 0);


		Point3D y1 = new Point3D(2, -1000, 0);
		Point3D y2 = new Point3D(-2, 1000, 0);
		Point3D y3 = new Point3D(-2, -1000, 0);
		Point3D y4 = new Point3D(2, 1000, 0);


		Point3D z1 = new Point3D(0, 2, 1000);
		Point3D z2 = new Point3D(0, -2, -1000);
		Point3D z3 = new Point3D(0, -2, 1000);
		Point3D z4 = new Point3D(0, 2, -1000);

		Tetrahedron tetra = new Tetrahedron(
			new MyPolygon(Color.RED, x1, x2, x3, x4),
			new MyPolygon(Color.GREEN, y1, y2, y3, y4),
			new MyPolygon(Color.BLUE, z1, z2, z3, z4));
		ArrayList<Tetrahedron> a = new ArrayList<Tetrahedron>(1);
		tetra.sortPolygons();
		a.add(tetra);
		Point3D b = new Point3D(0,0,0);
		return new Entity(a, b);
	}

	public static IEntity createDiamond(int loc) {
		double s = 175;
		double x = -50400;
		double y = loc*320;
		double z = -200;

		IEntity shadow = createPlane(new Color(78,140,54), 150, 250, -250);
		shadow.rotate(true, 0, 0, -90);
		shadow.move(-50400, loc*320, -80);

		Color c = new Color(0,246,255);
		ArrayList<Tetrahedron> tetras = new ArrayList<Tetrahedron>();
		int e = 7;
		double inFactor = 0.7;
		Point3D bottom = new Point3D(x,y,z-s/2);
		Point3D[] outerPoints = new Point3D[e];
		Point3D[] innerPoints = new Point3D[e];
		for (int i = 0; i < e; i++) {
			double theta = 2*Math.PI / e * i;
			double xPos = -Math.sin(theta)*s/2;
			double yPos = Math.cos(theta)*s/2;
			double zPos = s/2;
			outerPoints[i] = new Point3D(x+xPos,y+ yPos, z+zPos*inFactor);
			innerPoints[i] = new Point3D(x+xPos*inFactor,y+ yPos*inFactor, z+zPos);
		}

		MyPolygon[] polygons = new MyPolygon[2*e+1];

		for (int i = 0; i < e; i++) {
			polygons[i] = new MyPolygon(outerPoints[i], bottom, outerPoints[(i+1)%e]);
		}
		for (int i = 0; i < e; i++) {
			polygons[i + e] = new MyPolygon(outerPoints[i], outerPoints[(i+1)%e], innerPoints[(i+1)%e], innerPoints[i]);
		}
		polygons[e*2] = new MyPolygon(innerPoints);
		Tetrahedron tetra = new Tetrahedron(c, polygons);
		tetra.sortPolygons();
		tetras.add(tetra);
		Point3D b = new Point3D(x,y,z);
		IEntity ret = new Entity(tetras, b);
		ret.makeEnvironment();
		ret.setType("diamond");
		ret.rotate(true, 10,0,0);
		ret.setShadow(shadow);

		return ret;

	}

	public static IEntity createCloud(int loc) {
		IEntity cloud = createLongRect(new Color(83, 136, 211), 10000, -2000000, loc*12000, 20000, 5);
		cloud.makeEnvironment();
		cloud.setType("cloud");
		cloud.rotate(true,0,90,0);
		return cloud;
	}

	public static IEntity createBarrel(Color c, double s, double x, double y, double z) {
		ArrayList<Tetrahedron> tetras = new ArrayList<Tetrahedron>();
		int e = 16;
		double inFactor = 0.7;
		Point3D[] outerPoints1 = new Point3D[e];
		Point3D[] innerPoints1 = new Point3D[e];
		Point3D[] outerPoints2 = new Point3D[e];
		Point3D[] innerPoints2 = new Point3D[e];
		for (int i = 0; i < e; i++) {
			double theta = 2*Math.PI / e * i;
			double xPos = -Math.sin(theta)*s/2;
			double yPos = Math.cos(theta)*s/2;
			double zPos = s/2;
			outerPoints1[i] = new Point3D(x+ xPos,y+ yPos, z+zPos*inFactor);
			innerPoints1[i] = new Point3D(x+ xPos*inFactor,y+ yPos*inFactor, z+zPos);
			outerPoints2[i] = new Point3D(x+ xPos,y+ yPos, -z-zPos*inFactor);
			innerPoints2[i] = new Point3D(x+ xPos*inFactor,y+ yPos*inFactor, -z-zPos);
		}

		MyPolygon[] polygons = new MyPolygon[3*e+2];

		for (int i = 0; i < e; i++) {
			polygons[i] = new MyPolygon(outerPoints1[i], outerPoints2[i], outerPoints2[(i+1)%e], outerPoints1[(i+1)%e]);
		}
		for (int i = 0; i < e; i++) {
			polygons[i + e] = new MyPolygon(outerPoints1[i], outerPoints1[(i+1)%e], innerPoints1[(i+1)%e], innerPoints1[i]);
		}
		for (int i = 0; i < e; i++) {
			polygons[i + 2*e] = new MyPolygon(outerPoints2[i], outerPoints2[(i+1)%e], innerPoints2[(i+1)%e], innerPoints2[i]);
		}
		polygons[e*3] = new MyPolygon(innerPoints1);
		polygons[e*3+1] = new MyPolygon(innerPoints2);
		Tetrahedron tetra = new Tetrahedron(c, polygons);
		tetra.sortPolygons();
		tetras.add(tetra);
		Point3D b = new Point3D(x,y,z);


		IEntity eleven1 = createPlane(new Color(0,156,59), 20,5,0);
		IEntity eleven2 = createPlane(new Color(0,156,59), 20,5,0);
		eleven1.rotate(true, 0,90,0);
		eleven2.rotate(true, 0,90,0);
		eleven1.move(23,5,0);
		eleven2.move(23,-5,0);

		IEntity bod = new Entity(tetras, b);
		if (c.equals(Color.YELLOW)) {
			bod = combine(bod, eleven1, eleven2);
		}

		return bod;

	}


}