import java.awt.Point;
import java.awt.Color;
import java.awt.Graphics;

public class Tetrahedron {
	private Color color;
	private MyPolygon[] polygons;
	
	public Tetrahedron(Color color, MyPolygon... polygons) {
		this.color = color;
		this.polygons = polygons;
		this.setPolygonColor();
		this.sortPolygons();
	}
	public Tetrahedron(MyPolygon... polygons) {
		this.polygons = polygons;
		this.sortPolygons();
	}

	public void render(Graphics g, Point3D center) {
		for (MyPolygon a : this.polygons) {
			a.render(g, center);
		}
	}
	public void render(Graphics g) {
		for (MyPolygon a : this.polygons) {
			a.render(g);
		}
	}
	public void sortPolygons() {
		MyPolygon.sortPolygons(this.polygons);
	}


	public MyPolygon[] getPolygons() {
		return this.polygons;
	}


	public void rotate(boolean CW, Point3D a, double xDegrees, double yDegrees, double zDegrees) {
		for (MyPolygon p : polygons) {
			p.rotate(CW, a, xDegrees, yDegrees, zDegrees);
		}
		sortPolygons();
	}

	public void move(double x, double y, double z) {
		for (MyPolygon a : this.polygons) {
			a.move(x,y,z);
		}
		sortPolygons();
	}

	public void setPolygonColor() {
		for(MyPolygon a : this.polygons)
			a.setColor(this.color);
	}
}