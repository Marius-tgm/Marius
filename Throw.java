//Schiefer Wurf v.1.0
//Gruppe 3 Informatik 1
//Patrick Scheumer, Lennart Uphaus, Marius Benkert
//c 2019-2020

import java.math.RoundingMode;
import java.math.BigDecimal;
import java.util.Scanner;

public class Throw{
	
	
	static double maxHeight;			//max height
	static double timeOfFlight;			//timespan of the whole flight
	static double velocityZ;			//velocity in z-direction
	static double velocityX;			//velocity in x-direction
	static double s;					//length of the throw
	static double[][] saves = new double[300][5];	//Array for the results
	static int placeholder;
	static double maxVelocityZ = 0.0;
	static double maxVelocity = 0.0;
	
	public static void main(String[] args) throws Exception {
		
		boolean checkVelocity = false;
		boolean checkHeight = false;
		boolean checkAngle = false;
		int velocityTest = 0;
		int heightTest = 0;
		int angleTest = 0;
		
		
		Scanner velocityInput = new Scanner(System.in);
		Scanner startHeightInput = new Scanner(System.in);
		Scanner angleInput = new Scanner(System.in);
		
		System.out.println("This program simulates a throw. It will be shown as an askii image after you entered the Parameters. \nThe results and the askii image will be saved in seperate files.");
		System.out.println("Please enter a velocity between 0 an 111 m/s, a discharge height between 0 and 10000 m and a discharge angle between 90 and -90 degree:");
		
		while(checkVelocity == false){
			System.out.print("Velocity (m/s): ");				//User enters the paramters of the throw	
			velocityTest = velocityInput.nextInt();	
		
			if(velocityTest < 112 && velocityTest > 0)
				checkVelocity = true;
				else
					System.out.println("\nThe entered velocity is greater than 111 m/s or lower than 1 m/s - please enter a viable velocity for the throw.");
		}
		
		while(checkHeight == false){
			System.out.print("\nDischarge height (m): ");			
			heightTest = startHeightInput.nextInt();
		
			if(heightTest < 10001 && heightTest > 0)
				checkHeight = true;
				else
					System.out.println("\nThe entered height is greater than 10000 m or lower than 1 m - please enter a viable height for the throw.");
		}
		
		while(checkAngle == false){
			System.out.print("\nDischarge angle (degree): ");			
			angleTest = angleInput.nextInt();
		
			if(angleTest < 91 && angleTest > -90)
				checkAngle = true;
				else
					System.out.println("\nThe entered angle is greater than 90 degree or lower than -90 degree - please enter a viable angle for the throw.");
		}
		
		Point p1 = new Point(velocityTest,heightTest,angleTest,0); //passing entered paramters to the point class
		
		calculation();
		Flightdata.resultsIntoFile();
		comparison();
		VisThrow Graphic = new VisThrow(saves, maxHeight, placeholder, s);
		Flightdata.askiIntoFile();
	}
	
	public static void calculation(){					//calculates the maxHeight, velocity (x-v,z-v and general velocity), the time of flight, the length of the throw
	
		
		int velocity = Point.getVelocity();
		int height = Point.getHeight();
		int angle = Point.getAngle();
		int x = Point.getX();
		double g = Point.getG();
		double a;
		
		velocityZ = velocity*Math.sin(angle*(Math.PI/180));				//vz at start
		velocityX = velocity*Math.cos(angle*(Math.PI/180));				//vx
		timeOfFlight = ((velocity*Math.sin(angle*(Math.PI/180)))/g) + Math.sqrt(((velocity*Math.sin(angle*(Math.PI/180)))/g)*((velocity*Math.sin(angle*(Math.PI/180)))/g)+((2*height)/g));
		maxHeight = height+0.5*(((velocity*Math.sin(angle*(Math.PI/180)))*(velocity*Math.sin(angle*(Math.PI/180))))/g);
		s = velocityX * timeOfFlight;
		maxVelocityZ = (velocityZ-(g*timeOfFlight));
		maxVelocity = Math.sqrt((maxVelocityZ*maxVelocityZ)+(velocityX*velocityX));

		placeholder = (int)(timeOfFlight*5);
		
		Flightdata data1 = new Flightdata(placeholder);		//passing var placeholdeer to the class Flightdata
		
			for(int i = 1;i < placeholder+1;i++){					//calculates every 0.2 sec. parameters of the flight
				
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
				saves[i][3]	= velocityZ-(g*a);					//velocity z-direction
				saves[i][4]	= velocityX;						//velocity x-direction -> const.


				Flightdata data2 = new Flightdata(timeOfFlight,maxHeight,s);
			
			}	
		
	}
	
		public static void comparison(){		//compares the exact calculated paramters with paramters in the saves array (array contains the paramters of the flight every 0.2 seconds)
		
		double arrayMaxHeight = 0.0;
		double arrayMaxHeightXpos = 0.0;
		double arrayMaxVelocity = 0.0;
		double arrayMaxVelocityXpos = 0.0;
		double arrayMaxVelocityZpos = 0.0;
		double arrayMaxVelocityTest = 0.0;
		double arrayMinVelocity = 0.0;
		double arrayMinVelocityTest = 0.0;
		double arrayMinVelocityXpos = 0.0;
		double arrayMinVelocityZpos = 0.0;
		
		for(int d = 0; d < placeholder; d++){
			
			if(d == 0){
				arrayMaxHeight = saves[d][2];	//height (z-position)
				arrayMaxHeightXpos = saves[d][1];	//x-pos (that belongs to that z-pos )
				arrayMaxVelocity = saves[d][3];		//velocity z-direction
				arrayMaxVelocityZpos = saves[d][2];	//z-position
				arrayMinVelocity = saves[d][3];		//velocity z-dir
				arrayMinVelocityXpos = saves[d][1];
				arrayMinVelocityZpos = saves[d][2];
			}
				else{
					if(saves[d][3]*saves[d][3] < saves[d-1][3]*saves[d-1][3]){			//min velocity in the array
						arrayMinVelocity = saves[d][3];
						arrayMinVelocityXpos = saves[d][1];
						arrayMinVelocityZpos = saves[d][2];
					}
					if(saves[d][2] > saves[d-1][2]){			//max height in the array
						arrayMaxHeight = saves[d][2];
					}
					if((saves[d][3]*saves[d][3]) > (saves[d-1][3]*saves[d-1][3])){		//max velocity in the array
						arrayMaxVelocity = saves[d][3];			//velocity. z-direction
						arrayMaxVelocityXpos = saves[d][1];		//x-pos that belongs to the z-velocity
						arrayMaxVelocityZpos = saves[d][2];		//z-pos that belongs to the z-velocity
					}
				}
		}
		
		arrayMaxVelocityTest = Math.sqrt((arrayMaxVelocity*arrayMaxVelocity)+(velocityX*velocityX));	//absolute value of max velocity		
		arrayMinVelocityTest = Math.sqrt((arrayMinVelocity*arrayMinVelocity)+(velocityX*velocityX));	//absolute value of min velocity
		
		System.out.format("\nExact maximal height: %.2f m\n",maxHeight);			
		System.out.format("Maximal height in the saved file: %.2f m\n",arrayMaxHeight);
		System.out.format("Difference: %.2f m\n\n",(maxHeight-arrayMaxHeight));
		System.out.format("Exact maximal velocity: %.2f m/s\n",maxVelocity);
		System.out.format("Coordinates (x;z): (%.2f m;0 m)\n",s);
		System.out.format("Maximal velocity in the saved file: %.2f m/s \n",arrayMaxVelocityTest);
		System.out.format("Coordinates (x;z): (%.2f m;%.2f m)\n",arrayMaxVelocityXpos,arrayMaxVelocityZpos);	
		System.out.format("Difference: %.2f m/s\n\n",(maxVelocity-arrayMaxVelocityTest));
		System.out.format("Exact minimal velocity: %.2f m/s \n",velocityX);
		System.out.format("Minimal velocity in the saved file: %.2f m/s\n",arrayMinVelocityTest);
		System.out.format("Difference: %.2f m/s\n\n",(arrayMinVelocityTest-velocityX));
	
	}
	
	
}