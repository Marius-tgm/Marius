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
	static double[][] saves = new double[300][5];	//Array fuer die Ergebnisse
	static int placeholder;
	static double maxVelocityZ = 0.0;
	static double maxVelocity = 0.0;
	
	public static void main(String[] args) throws Exception {
		
		
		Scanner velocityInput = new Scanner(System.in);
		Scanner startHeightInput = new Scanner(System.in);
		Scanner angleInput = new Scanner(System.in);
		
		
		System.out.println("Mit diesem Programm kann kannst du einen Schiefen Wurf berechnen und Dir die Flugbahn anzeigen lassen.");
		System.out.println("Bitte gib nun die Startparameter ein: ");
		System.out.print("Geschwindigkeit (m/s): ");				//Eingabe der Startparameter
		
		int velocityTest = velocityInput.nextInt();
		
		System.out.print("\nAbwurfhoehe (m): ");
		
		int heightTest = startHeightInput.nextInt();
		
		System.out.print("\nAbwurfwinkel (Grad): ");
		
		int angleTest = angleInput.nextInt();
		
		Point p1 = new Point(velocityTest,heightTest,angleTest,0); //Uebergabe der Paramter an die Punkt-Klasse, wo diese dann geprüft werden, die 0 ist die x-Pos
		
		berechnung();
		Flightdata.resultsIntoFile();
		vergleich();
		optOutput Graphic = new optOutput(saves, maxHeight, timeOfFlight, placeholder); 
	}
	
	public static void berechnung(){					//hier wird alles relevante berechnet
	
		
		int velocity = Point.getVelocity();
		int height = Point.getHeight();
		int angle = Point.getAngle();
		int x = Point.getX();
		double g = Point.getG();
		double a;
		
		velocityZ = velocity*Math.sin(angle*(Math.PI/180));				//vz bei Start
		velocityX = velocity*Math.cos(angle*(Math.PI/180));				//vx ist ja gleich über den ganzen Zeitraum
		timeOfFlight = ((velocity*Math.sin(angle*(Math.PI/180)))/g) + Math.sqrt(((velocity*Math.sin(angle*(Math.PI/180)))/g)*((velocity*Math.sin(angle*(Math.PI/180)))/g)+((2*height)/g));
		maxHeight = height+0.5*(((velocity*Math.sin(angle*(Math.PI/180)))*(velocity*Math.sin(angle*(Math.PI/180))))/g);
		s = velocityX * timeOfFlight;
		maxVelocityZ = (velocityZ-(g*timeOfFlight));
		maxVelocity = Math.sqrt((maxVelocityZ*maxVelocityZ)+(velocityX*velocityX));
		
		
		System.out.println("Flugzeit: " + timeOfFlight);
		System.out.println("max. Hoehe: " + maxHeight);
		System.out.println("Wurfweite: " + s);
		
		
		placeholder = (int)(timeOfFlight*5);
		
		Flightdata data1 = new Flightdata(placeholder);		//es wird die placeholder var an flightdata uebergeben
		
			for(int i = 1;i < placeholder+1;i++){					//alle 0.2 s Berechnung der Position
				
				if(i>0){
					saves[i][0] = saves[i-1][0] + 0.2;
					a = saves[i][0];
					a = (double)Math.round(a*100)/100;
					saves[i][0] = a;
				}
					else{
					saves[i][0] = saves[i][0] + 0.2;
					a = saves[i][0];
					a = (double)Math.round(a*100)/100;	
					saves[i][0] = a;					
					}
					
				saves[i][1] = velocityX*a; 						//x-pos
				saves[i][2]	= height + (velocityZ*a)-(0.5*g*a*a); //z-pos
				saves[i][3]	= velocityZ-(g*a);					//Geschwindigkeit z-Richtung
				saves[i][4]	= velocityX;						//Geschwindigkeit in x-Richtung -> konstant

				double z = a;
				double b = saves[i][1];
				double c = saves[i][2];
				double d = saves[i][3];
				double e = saves[i][4];
				
				
				System.out.println( " " + z + " " + b + " " + c + " " + d + " " + e);
				
				Flightdata data2 = new Flightdata(timeOfFlight,maxHeight,s);		//das hier ist gerade noch irgendwie unnütz, aber es wird sinnvoll, wenn es darum geht die verglichenen Daten rüber in FlightData zu schieben
			
			}	
		
	}
	
		public static void vergleich(){		//hier wird das Array ausge_savest und mit der max. Hoehe und Wurfweite verglichen
		
		double arrayMaxHeight = 0.0;
		double arrayMaxHeightXpos = 0.0;
		double arrayMaxVelocity = 0.0;
		double arrayMaxVelocityXpos = 0.0;
		double arrayMaxVelocityZpos = 0.0;
		double arrayMaxVelocityTest = 0.0;
		
		for(int d = 0; d < placeholder; d++){
			
			if(d == 0){
				arrayMaxHeight = saves[d][2];	//hoehe (z-pos)
				arrayMaxHeightXpos = saves[d][1];	//x-pos (die dazu gehört)
				arrayMaxVelocity = saves[d][3];		//geschw. in z-Richtung
				arrayMaxVelocityZpos = saves[d][1];	//z-pos
			}
				else{
					if(saves[d][2] > saves[d-1][2]){
						arrayMaxHeight = saves[d][2];
						arrayMaxHeightXpos = saves[d][1];
					}
					if((saves[d][3]*saves[d][3]) > (saves[d-1][3]*saves[d-1][3])){
						arrayMaxVelocity = saves[d][3];			//geschw. z-Richtung
						arrayMaxVelocityXpos = saves[d][1];		//dazugeh. x-pos
						arrayMaxVelocityZpos = saves[d][2];		//dazugeh. z-pos
					}
				}
		}
		
		arrayMaxVelocityTest = Math.sqrt((arrayMaxVelocity*arrayMaxVelocity)+(velocityX*velocityX));	//Betrag der maximalen Gesamtgeschwindigkeit 		
		
		System.out.println("\nBerechnete maximale Hoehe: " + maxHeight);			
		System.out.println("In der Grafik dargestellte maximale Hoehe und x-pos: " + arrayMaxHeight + " " + arrayMaxHeightXpos);
		System.out.println("Differenz: " + (maxHeight-arrayMaxHeight));
		System.out.println("Maximale Geschwindigkeit (berechnet): " + maxVelocity);
		System.out.println("Bei den Koordinaten (x,z): (" + s + "," + 0 + ")");
		System.out.println("Maximale Geschwindigkeit im Array: " + arrayMaxVelocityTest);
		System.out.println("Bei den Koordinaten (x,z): (" + arrayMaxVelocityXpos + "," + arrayMaxVelocityZpos + ")");	
		System.out.println("Differenz der maximalen Geschwindigkeiten: " + (maxVelocity-arrayMaxVelocityTest));
	
	}
	
	
}