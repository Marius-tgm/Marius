public class Point{
	
	private static int velocity;
	private static int height;
	private static int angle;
	private static int x;
	private static double g = 9.81;
	
	public Point(int _velocity, int _height, int _angle, int _x){
		
		if(_velocity < 401 && _velocity > 0){				//Abfrage - max. Geschwindigkeit darf 400 km/h sein, Punkt muss schneller als 0 km/h sein
			velocity = _velocity;
		}	
		
		if(_height < 10001 && _height > 0){					//Abfrage - max. Hoehe 10000m, muss aber mehr als 0 m sein
			height = _height;	
		}
		
		if(_angle < 91 && _angle > -91){					//Abfrage - Winkel darf maximal 90 Grad sein (senkrechter Wurf) und mindestens größer als -90 Grad (Freier Fall mit Anfangsgeschwindigkeit)
			angle = _angle;
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
