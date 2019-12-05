//Schiefer Wurf v.1.0
//Gruppe 3 Informatik 1
//Patrick Scheumer, Lennart Uphaus, Marius Benkert
//c 2019

import java.math.RoundingMode;
import java.math.BigDecimal;
import java.util.Scanner;

public class SchieferWurf{
	
	
	static double maxHeight;			//maximale Hoehe
	static double timeOfFlight;			//t des gesamten Fluges
	static double velocityZ;			//Geschwindigkeit in z-Richtung
	static double velocityX;			//Geschwindigkeit in x-Richtung
	static double s;					//Wurfweite
	
	public static void main(String[] args){
		
		
		Scanner velocityInput = new Scanner(System.in);
		Scanner startHeightInput = new Scanner(System.in);
		Scanner angleInput = new Scanner(System.in);
		
		
		System.out.println("Mit diesem Programm kann kannst du einen Schiefen Wurf berechnen und Dir die Flugbahn anzeigen lassen.");
		System.out.println("Bitte gib nun die Startparameter ein: ");
		System.out.print("Geschwindigkeit: ");				//Eingabe der Startparameter
		
		int velocityTest = velocityInput.nextInt();
		
		System.out.print("\nAbwurfhoehe: ");
		
		int heightTest = startHeightInput.nextInt();
		
		System.out.print("\nAbwurfwinkel: ");
		
		int angleTest = angleInput.nextInt();
		
		Point p1 = new Point(velocityTest,heightTest,angleTest,0); //Uebergabe der Paramter an die Punkt-Klasse, wo diese dann geprüft werden, die 0 ist die x-Pos
		
		Berechnung();
		
	}
	
	public static void Berechnung(){					//hier wird alles relevante berechnet
	
		
		int velocity = Point.getVelocity();
		int height = Point.getHeight();
		int angle = Point.getAngle();
		int x = Point.getX();
		double g = Point.getG();
		double a;
		
		
		velocityZ = velocity*Math.sin(angle*(Math.PI/180));
		velocityX = velocity*Math.cos(angle*(Math.PI/180));
		timeOfFlight = ((velocity*Math.sin(angle*(Math.PI/180)))/g) + Math.sqrt(((velocity*Math.sin(angle*(Math.PI/180)))/g)*((velocity*Math.sin(angle*(Math.PI/180)))/g)+((2*height)/g));
		maxHeight = height+0.5*(((velocity*Math.sin(angle*(Math.PI/180)))*(velocity*Math.sin(angle*(Math.PI/180))))/g);
		s = velocityX * timeOfFlight;
	
		System.out.println("Flugzeit: " + timeOfFlight);
		System.out.println("max. Hoehe: " + maxHeight);
		System.out.println("Wurfweite: " + s);
		
		Flightdata data = new Flightdata(velocityZ,velocityX,timeOfFlight,maxHeight,s);		//hier werden die berechneten Daten gespeichert
		
		int placeholder = (int)timeOfFlight*5;
		
		double[][] saves = new double[(placeholder+1)][5];		//Praemisse: alle 0.2 s wird was gespeichert
		
			for(int i = 1;i < saves.length;i++){
				
				if(i>0){
					saves[i][0] = saves[i-1][0] + 0.2;
					a = saves[i][0];
					a = (double)Math.round(a*100)/100;
					
				}
					else{
					saves[i][0] = saves[i][0] + 0.2;
					a = saves[i][0];
					a = (double)Math.round(a*100)/100;					
					}
					
				saves[i][1] = velocityX*a; 						//x-pos
				saves[i][2]	= height + (velocityZ*a)-(0.5*g*a*a);// z-pos
				saves[i][3]	= velocityZ-(g*a);					//Geschwindigkeit z-Richtung
				saves[i][4]	= velocityX;						//Geschwindigkeit in x-Richtung -> konstant

				double z = a;
				double b = saves[i][1];
				double c = saves[i][2];
				double d = saves[i][3];
				double e = saves[i][4];
				
				
				System.out.println( " " + z + " " + b + " " + c + " " + d + " " + e);
			}
	
	}
	
	
}