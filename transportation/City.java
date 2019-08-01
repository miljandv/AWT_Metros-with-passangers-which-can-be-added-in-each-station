package transportation;

public class City {
	protected String name;
	protected double x,y;
	public String getname() {return name;}
	public double getdistance(City g) {return Math.sqrt(Math.pow(x-g.x, 2)+Math.pow(y-g.y, 2));}
	public City(String namee,double xx,double yy) {x=xx;y=yy;name=namee;}
	public String toString() {return name;}
}
