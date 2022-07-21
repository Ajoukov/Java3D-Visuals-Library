import java.awt.Point;

public class PointConverter {

	private static double scale = 1;

	public static Point convertPoint(Point3D point3D) {
		double x3d = point3D.y * scale;
		double y3d = point3D.z * scale;
		double depth = point3D.x * scale;
		double[] newVal = scale(x3d, y3d, depth);
		int x2d = (int) (Display.WIDTH / 2 + newVal[0]);
		int y2d = (int) (Display.HEIGHT / 2 - newVal[1]);
		Point point2D = new Point(x2d, y2d);
		return point2D;
	}


	private static double[] scale(double x3d, double y3d, double depth) {
		double distance = Math.sqrt(x3d*x3d + y3d*y3d);
		double theta = Math.atan2(y3d, x3d);
		double depth2 = 15-depth;
		double localScale = Math.abs(1400/(depth2+1400));
		distance *= localScale;
		double[] newVal = new double[2];
		newVal[0] = distance * Math.cos(theta);
		newVal[1] = distance * Math.sin(theta);
		return newVal;
	}

	public static void move(Point3D p, double x, double y, double z) {
		p.x += x;
		p.y += y;
		p.z += z;
	}

	public static void rotateAxisX(Point3D p, Point3D axis, boolean CW, double degrees) {
		double y = (p.y-axis.y);
		double z = (p.z-axis.z);
		double radius = Math.sqrt(y*y + z*z);
		double theta = Math.atan2(z, y);
		theta += 2*Math.PI/360*degrees*(CW?-1:1);
		p.y = radius * Math.cos(theta) + axis.y;
		p.z = radius * Math.sin(theta) + axis.z;
	}

	public static void rotateAxisY(Point3D p, Point3D axis, boolean CW, double degrees) {
		double z = (p.z-axis.z);
		double x = (p.x-axis.x);
		double radius = Math.sqrt(x*x + z*z);
		double theta = Math.atan2(x, z);
		theta += 2*Math.PI/360*degrees*(CW?-1:1);
		p.x = radius * Math.sin(theta) + axis.x;
		p.z = radius * Math.cos(theta) + axis.z;
	}
	public static void rotateAxisZ(Point3D p, Point3D axis, boolean CW, double degrees) {
		double y = (p.y-axis.y);
		double x = (p.x-axis.x);
		double radius = Math.sqrt(y*y + x*x);
		double theta = Math.atan2(y, x);
		theta += 2*Math.PI/360*degrees*(CW?-1:1);
		p.y = radius * Math.sin(theta) + axis.y;
		p.x = radius * Math.cos(theta) + axis.x;
	}
}