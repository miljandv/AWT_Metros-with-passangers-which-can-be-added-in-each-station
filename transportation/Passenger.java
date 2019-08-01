package transportation;

public class Passenger {
	protected City src,dst;
	public Passenger(City s,City d) {src=s;dst=d;}
	public City getSrc() {
		return src;
	}
	public City getDst() {
		return dst;
	}

}
