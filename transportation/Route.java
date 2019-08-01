package transportation;

import java.awt.List;
import java.util.LinkedList;


public class Route {
	protected String name;
	LinkedList<Road> list=new LinkedList<>();
	public boolean containsf(City g) {
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getFinish()==g) {return true;}
		}
		return false;
	}
	public boolean containss(City g) {
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getStart()==g) {return true;}
		}
		return false;
	}
	public Route(String namee) {name=namee;}
	public int addRoad(Road p) {
		if(list.isEmpty() || list.getLast().getFinish()==p.getStart()) {
			list.add(p);return 0;}
		else return -1;
	}
	public double getFullDistance() {
		double sum=0;
		for (int i = 0; i < list.size()-1; i++) {
			sum+=list.get(i).getStart().getdistance(list.get(i+1).getFinish());
		}
		return sum;
	}
	public int numofcities() {return list.size();}
	public City getcitybyindex(int i) {try {
		return (i==list.size())?list.getLast().getFinish():list.get(i).getStart(); 
	}catch (IndexOutOfBoundsException e) {
		System.err.println("Index out of bounds");
	}
	return null;
	}
	public Road getroadbyindex(City g) {
		Road put=null;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getStart()==g) {
				return list.get(i);
			}
		}
		return put;
	}
	public double getPrice(City g1,City g2) {
		double sum=0;int i1=-1,i2=-1;
label1:	for (int j = 0; j < list.size() && i2==-1; j++) {
			if(i1==-1 && list.get(j).getStart()==g1) {i1=j;}
			if(i1!=-1){sum+=list.get(j).getTripprice();
			if (list.get(j).getFinish()==g2) {i2=j;}
			}
			}
		System.out.println(i1+" "+i2+" "+sum+" "+list.size());
		if(i1==-1 || i2==-1)return Double.MAX_VALUE;
		else return sum;
	}
	public String toString() {
		StringBuffer sb=new StringBuffer();
		sb.append("Route: "+name+"\nLength: "+getFullDistance()+"\nStations:\n");
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i).getStart()+"\n");
		}
		sb.append(list.get(list.size()-1).getFinish()+"\n");
		return sb.toString();
	}
}
	
