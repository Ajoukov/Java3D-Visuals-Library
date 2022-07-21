import java.awt.Graphics;


//self explanatory
public interface IEntity {
	void render(Graphics g);
	void renderS(Graphics g);
	void rotate(boolean CW, double xDegrees, double yDegrees, double zDegrees);
	void move(double x, double y, double z);
	MyPolygon[] getPolygons();
	double getX();
	void makeEnvironment();
	boolean getEnvironment();
	Point3D getCenter();
	void setShadow(IEntity e);
	void setType(String s);
	String getType();
}