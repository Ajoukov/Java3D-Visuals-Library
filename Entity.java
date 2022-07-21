import java.util.ArrayList;
import java.awt.Graphics;
import java.util.Arrays;

public class Entity implements IEntity {
	private ArrayList<Tetrahedron> tetras;
	private MyPolygon[] polygons;
	Point3D centerOfRotation;
	boolean environment;
	private IEntity shadow;
	private String type;
	
	public Entity(ArrayList<Tetrahedron> tetras, Point3D center) {
		environment = false;
		this.centerOfRotation = center;
		this.tetras = tetras;
		ArrayList<MyPolygon> tempList = new ArrayList<MyPolygon>();
		for (Tetrahedron t : this.tetras) {
			tempList.addAll(Arrays.asList(t.getPolygons()));
		}
		this.polygons = new MyPolygon[tempList.size()];
		tempList.toArray(this.polygons);
		this.sortPolygons();
	}

	public void renderS(Graphics g) {
		if (shadow != null) shadow.render(g);
		for (Tetrahedron t : this.tetras) {
			t.render(g, centerOfRotation);
		}
		///System.out.println("\n\n\n\n");
	}

	public Point3D getCenter() {
		return centerOfRotation;
	}
	public void render(Graphics g) {
		if (shadow != null) shadow.render(g);
		for (Tetrahedron t : this.tetras) {
			t.render(g);
		}
	}
	public void setType(String s) {
		type = s;
	}
	public String getType() {
		return type;
	}
	public void makeEnvironment() {
		environment = true;
	}
	public boolean getEnvironment() {
		return environment;
	}
	public void setShadow(IEntity e) {
		shadow = e;
	}

	public double getX() {
		return centerOfRotation.x;
	}
	public MyPolygon[] getPolygons() {
		return polygons;
	}
	public void rotate(boolean CW, double xDegrees, double yDegrees, double zDegrees) {
		if (shadow != null && (!   (getType().equals("ball") || getType().equals("diamond")))) shadow.rotate(CW, 0, 0, zDegrees);
		for (Tetrahedron t : this.tetras) {
			t.rotate(CW, centerOfRotation, xDegrees, yDegrees, zDegrees);
		}
	}
	public void move(double x, double y, double z) {
		if (shadow != null) {
			shadow.move(x,y,z);
			if (type!=null && type.equals("player")) {
				shadow.move(0,-y*0.25,0);
			}
			if (type!=null && type.equals("ball")) {
				shadow.move(0,-y*0.1,0);
			}
		}
		PointConverter.move(centerOfRotation, x, y, z);
		for (Tetrahedron t : tetras) {
			t.move(x,y,z);
		}
	}
	public void sortPolygons() {
		MyPolygon.sortPolygons(this.polygons);
	}
}