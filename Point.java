public class Point{
	
	private static int velocity;
	private static int height;
	private static int angle;
	private static int x;
	private static double g = 9.81;
	
	public Point(int _velocity, int _height, int _angle, int _x){
		
		velocity = _velocity;
		height = _height;	
		angle = _angle;
		x = _x;
		
	}
	
	
	public static int getVelocity(){				//Getter for velocity
		return velocity;
	}
	
	public static int getHeight(){					//Getter for height
		return height;
	}
	
	public static int getAngle(){					//Getter for angle
		return angle;
	}
	
	public static int getX(){						//Getter for x-Position
		return x;
	}
	
	public static double getG(){					//Getter for g=9.81 m/s^2
		return g;
	}
	
	
} 
