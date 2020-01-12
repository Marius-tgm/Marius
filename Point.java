public class Point{
	
	private static int velocity;
	private static int height;
	private static int angle;
	private static int x;
	private static double g = 9.81;
	
	public Point(int _velocity, int _height, int _angle, int _x){
		
		if(_velocity < 112 && _velocity > 0){				//Abfrage - max. Geschwindigkeit darf 400 km/h sein, Punkt muss schneller als 0 km/h sein
			velocity = _velocity;
		} else{
			System.out.println("The entered velocity is not permissible, the Program will shut down. Please enter a number between 0 and 111 m/s!");
			int n = -1;
			System.exit(n);
		  }
		
		if(_height < 10001 && _height > 0){					//Abfrage - max. Hoehe 10000m, muss aber mehr als 0 m sein
			height = _height;	
		} else{
			System.out.println("The entered height is not permissible, the Program will shut down. Please enter a number between 0 and 10000 m!");
			int z = -1;
			System.exit(z);
		  }
		
		if(_angle < 91 && _angle > -91){					//Abfrage - Winkel darf maximal 90 Grad sein (senkrechter Wurf) und mindestens größer als -90 Grad (Freier Fall mit Anfangsgeschwindigkeit)
			angle = _angle;
		} else{
			System.out.println("The entered angle is not permissible, the Program will shut down. Please enter a number between -90 and 90 degree!");
			int q = -1;
			System.exit(q);
		  }
		
		x = _x;
		
	}
	
	
	public static int getVelocity(){				//Getter für Geschwindigkeit
		return velocity;
	}
	
	public static int getHeight(){					//Getter für Hoehe(z)
		return height;
	}
	
	public static int getAngle(){					//Getter für Winkel
		return angle;
	}
	
	public static int getX(){						//Getter für x-Position
		return x;
	}
	
	public static double getG(){
		return g;
	}
	
	
} 
