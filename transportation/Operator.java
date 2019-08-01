package transportation;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;

public class Operator extends Frame implements ActionListener {
	private double earnings=0;
	private Choice src=new Choice();
	private Choice dst=new Choice();
	Panel cnt=new Panel();
	private LinkedList<City> srcCities=new LinkedList<>();
	private LinkedList<City> dstCities=new LinkedList<>();
	private Button b1=new Button("Add a passenger");
	private Label l1=new Label("Operator:         ");
	LinkedList<Bus> list=new LinkedList<>();
	class we extends WindowAdapter{
		public void windowClosing(WindowEvent e) {dispose();}
	}
	public Operator() {
		super("Coach USA");setBounds(500,100,560,400);
		addWindowListener(new we());
		l1.setAlignment(Label.CENTER);
		Panel hd=new Panel();hd.setBackground(Color.gray);
		hd.add(l1);
		add(hd,BorderLayout.NORTH);
		Panel ft=new Panel();ft.add(src);ft.add(dst);ft.add(b1);
		b1.addActionListener(this);
		//src.addItemListener(this);
		//dst.addItemListener(this);
		add(ft,BorderLayout.SOUTH);
		add(cnt,BorderLayout.CENTER);
		setVisible(true);
	}
	public void addBus(Bus a) {
		list.add(a);
		for (int i = 0; i < a.getRoute().numofcities()+1; i++) {
				if(!srcCities.contains(a.getRoute().getcitybyindex(i))) {srcCities.add(a.getRoute().getcitybyindex(i));src.add(a.getRoute().getcitybyindex(i).getname());}
				if(i!=0 && !dstCities.contains(a.getRoute().getcitybyindex(i))) {dstCities.add(a.getRoute().getcitybyindex(i));dst.add(a.getRoute().getcitybyindex(i).getname());}
			}
		cnt.add(a);
		for (int i = 0; i < list.size(); i++) {list.get(i).refresh();}
		setVisible(true);
		}
	public int assignpassanger(Passenger p) {
		System.out.println("=========================================");
		double sum=Double.MAX_VALUE;int j=-1;
		for (int i = 0; i < list.size(); i++) {
			if((list.get(i).getRoute().containsf(p.getDst()) && list.get(i).getRoute().containss(p.getSrc())) && list.get(i).getRoute().getPrice(p.getSrc(), p.getDst())<sum && !list.get(i).isfull()) {sum=list.get(i).getRoute().getPrice(p.getSrc(), p.getDst());System.out.println("sum="+sum);j=i;}
		}
		for (int i = 0; i < list.size(); i++) {list.get(i).refresh();}
		setVisible(true);
		if(j!=-1) {list.get(j).addPassenger(p);earnings+=sum+sum*110/100;l1.setText("Operator: "+earnings);return 0;}
		else return -1;
	}
	public double getEarnings() {return earnings;}
	public void actionPerformed(ActionEvent arg0) {

		String string=arg0.getActionCommand();
		if(string.equals("Add a passenger")) {
			assignpassanger(new Passenger(srcCities.get(src.getSelectedIndex()),dstCities.get(dst.getSelectedIndex())));for (int i = 0; i < list.size(); i++) {list.get(i).refresh();}
		}
	}

}
