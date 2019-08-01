package transportation;

public class Road {
	protected City start,finish;
	double speed,tripprice; 
	public Road(City startt,City finishh,double speedd, double trippricee) {start=startt;finish=finishh;
	speed=speedd;tripprice=trippricee;}
	public City getStart() {
		return start;
	}
	public City getFinish() {
		return finish;
	}
	public double getSpeed() {
		return speed;
	}
	public double getTripprice() {
		return tripprice;
	}
	public double getrastojanje() {return start.getdistance(finish);}
}
