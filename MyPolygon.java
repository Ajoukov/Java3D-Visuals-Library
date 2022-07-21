import java.awt.Point;
import java.awt.Color;
import java.awt.Polygon;
import java.awt.Graphics;
import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;

public class MyPolygon {
	private Color color;
	private Point3D[] points;
	
	public MyPolygon(Point3D... points) {
		this.points = new Point3D[points.length];
		for (int i = 0; i < points.length; i++) {
			Point3D a  = points[i];
			this.points[i] = new Point3D(a.x, a.y, a.z);
		}
	}
	public MyPolygon(Color c, Point3D... points) {
		this.color = c;
		this.points = new Point3D[points.length];
		for (int i = 0; i < points.length; i++) {
			Point3D a  = points[i];
			this.points[i] = new Point3D(a.x, a.y, a.z);
		}
	}


	public void rotate(boolean CW, Point3D axis, double xDegrees, double yDegrees, double zDegrees) {
		for (Point3D p : this.points) {
			PointConverter.rotateAxisX(p, axis, CW, xDegrees);
			PointConverter.rotateAxisY(p, axis, CW, yDegrees);
			PointConverter.rotateAxisZ(p, axis, CW, zDegrees);
		}
	}

	public void move(double x, double y, double z) {
		for (Point3D p : this.points) {
			PointConverter.move(p, x, y, z);
		}
	}

	public void render(Graphics g, Point3D center) {
		Polygon polygon = new Polygon();
		for (int i = 0; i < points.length; i++) {
			Point p1 = PointConverter.convertPoint(points[i]);
			polygon.addPoint(p1.x, p1.y);
		}
		double x = 0;
		double y = 0;
		double z = 0;
		for (Point3D p : points) {
			x += p.x;
			y += p.y;
			z += p.z;
		}
		x/=points.length;
		y/=points.length;
		z/=points.length;
		x -= center.x;
		y -= center.y;
		z -= center.z;
		//System.out.println("\n" + x + ", " + y + ", " + z );

		double dotProd = x/2 + z;
		double length = Math.sqrt(x * x + y * y + z * z) * 1.12;
		double theta = Math.acos(dotProd / length);
		
		//System.out.println(dotProd / length);
		//double f = theta/3.14 * (theta>0?1:-1);
		double f = Math.cos(theta);
		f = f*0.5 + 0.7;
		f *= (f>0?1:-1);
		
		//System.out.println(f);
		int r = (int)(color.getRed() * f);
		if (r > 255 || r < 0) r = color.getRed();
		int g1 = (int)( color.getGreen() * f);
		if (g1 > 255 || g1 < 0) g1 = color.getGreen();
		int b = (int)(color.getBlue() * f);
		if (b > 255 || b < 0) b = color.getBlue();

		Color c = new Color(r,g1,b);

		g.setColor(c);
		g.fillPolygon(polygon);
	}
	public void render(Graphics g) {
		Polygon polygon = new Polygon();
		for (int i = 0; i < points.length; i++) {
			Point p1 = PointConverter.convertPoint(points[i]);
			polygon.addPoint(p1.x, p1.y);
		}
		g.setColor(color);
		g.fillPolygon(polygon);
	}

	public void setColor(Color c) {
		this.color = c;
	}


	public double getAverageX() {
		double sum = 0;
		for (Point3D p : this.points) {
			sum += p.x;
		}
		return sum/this.points.length;
	}

	public static MyPolygon[] sortPolygons(MyPolygon[] polygons) {
		ArrayList<MyPolygon> polygonsList = new ArrayList<MyPolygon>();
		
		for (MyPolygon p : polygons) {
			polygonsList.add(p);
		}

		Collections.sort(polygonsList, new Comparator<MyPolygon>() {
			public int compare(MyPolygon p1, MyPolygon p2) {
				double p1AverageX = p1.getAverageX();
				double p2AverageX = p2.getAverageX();
				double diff = p2AverageX - p1AverageX;
				if (diff == 0) return 0;
				return diff < 0 ? 1 : -1;
			}
		});

		for (int i = 0; i < polygons.length; i++) {
			polygons[i] = polygonsList.get(i);
		}
		return polygons;
	}
}