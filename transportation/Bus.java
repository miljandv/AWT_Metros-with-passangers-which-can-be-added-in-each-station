package transportation;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.TextArea;
import java.awt.TextField;
import java.util.ArrayList;
import java.util.Iterator;

public class Bus extends TextArea implements Runnable {
	private Thread myThread=new Thread(this);protected Route route;
	private int num,time;
	private int working;int ii;
	private City curCity;
	private ArrayList<Passenger> passArrayList=new ArrayList<>();
	private state curstate;
	public enum state{STATION,DRIVING,FINISHED};
	public Bus(Route pp,int n,int t) {super(18,35);curCity=pp.getcitybyindex(0);curstate=state.STATION;setBackground(Color.yellow);time=t;route=pp;num=n;myThread.start();}
	public void run() {try { ii=0;curCity=route.getcitybyindex(0);
		synchronized (myThread) {while(passArrayList.size()<num)myThread.wait(1);}
		while(!Thread.interrupted() && ii<route.numofcities()) {
		curstate=state.DRIVING;setBackground(Color.green);
		setText("");
		append(toString());
		myThread.sleep((long) (route.getroadbyindex(route.getcitybyindex(ii)).getSpeed()*route.getroadbyindex(route.getcitybyindex(ii)).getStart().getdistance(route.getroadbyindex(route.getcitybyindex(ii)).getFinish())));
		setBackground(Color.orange);curstate=state.STATION;ii++;curCity=route.getcitybyindex(ii);
		Iterator<Passenger> i1=passArrayList.iterator();
		while(i1.hasNext()) {
			Passenger p=i1.next();
			if(p.getDst()==curCity) {
				i1.remove();
				refresh();
			}
		}
		setText("");
		append(toString());
		myThread.sleep(time);
		}
		curstate=state.FINISHED;setBackground(Color.red);
		setText("");
		append(toString());
	}catch (InterruptedException e) {}
	}
	public int addPassenger(Passenger p) {
		if (p.getSrc()==curCity && curstate==state.STATION && passArrayList.size()<num) {
			passArrayList.add(p);
			refresh();
			return 0;
		}
		else return -1;
	}
	public void stoppermanently() {myThread.interrupt();}
	public boolean isfull() {return passArrayList.size()==num;}
	public Route getRoute() {return route;}
	public String toString() {
		StringBuffer sb=new StringBuffer();
		sb.append(route.toString()+"\n");
		sb.append("Number of passengers: "+passArrayList.size()+"/"+num+"\n");
		switch (curstate) {
		case DRIVING:
			sb.append("Driving: "+route.getcitybyindex(ii)+"-"+route.getcitybyindex(ii+1));
			break;
		case STATION:
			sb.append("In station: "+curCity);
			break;
		case FINISHED:
			sb.append("Arrived: "+curCity);
			break;
		}
		return sb.toString();
	}
	public void refresh() {
		setText("");
		append(toString());
	}
}
