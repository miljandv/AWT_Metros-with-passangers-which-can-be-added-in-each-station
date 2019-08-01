package main;
import transportation.Bus;
import transportation.City;
import transportation.Operator;
import transportation.Road;
import transportation.Route;
import transportation.Passenger;

public class Main {
public static void main(String[] args) {
 Operator p = new Operator();
 Route route = new Route("Vn Crtlndt Pk 242 St-96 St");
 City city1 = new City("Vn Crtlndt Pk 242 St", 0, 0);
 City cities[] = new City[] {
 city1,
 new City("238 St", 50., 100.),
 new City("168 St", 150., 200.),
 new City("137 St City College", 150., 300.),
 new City("103 St", 200., 400.),
 new City("96 St", 250., 500.)
 };
 double speeds[] = {50., 60., 60., 50., 70.},
 prices[] = {100., 150., 150., 100., 200.};

 for(int i=0; i<cities.length-1; i++) {
 Road road1 = new Road(cities[i], cities[i+1], speeds[i], prices[i]);
 route.addRoad(new Road(road1.getStart(), road1.getFinish(), road1.getSpeed(), road1.getTripprice()));
 }


 Bus a = new Bus(route, 4, 1000);
 p.addBus(a);

 route = new Route("Vn Crtlndt Pk 242 St-96 St");
 cities = new City[] {
 city1,
 new City("66 St Lincoln Ctr", -50., 0.),
 new City("Times Sq 42 St", -100., -50.),
 new City("Chambers St", -200., -100.),
 new City("South Ferry", -300., -250.)
 };
 speeds = new double[] {40., 70., 80., 70.};
 prices = new double[] {100., 200., 200., 150.};

 for(int i=0; i<cities.length-1; i++) {
 Road road2 = new Road(cities[i], cities[i+1], speeds[i], prices[i]);
 route.addRoad(road2);
 }

 a = new Bus(route, 5, 1500);
 p.addBus(a);

 Passenger passenger = new Passenger(cities[0], cities[2]);
 City fromCity = passenger.getSrc(), toCity = passenger.getDst();
 p.assignpassanger(passenger);
 }
} 