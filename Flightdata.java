import java.io.*;

public class Flightdata extends Throw{
	
	double timeOfFlight;
	double maxHeight;
	double s;
	static int placeholder;
	static String[] forFileOutput = new String[24];
	
	
	public Flightdata(double _timeOfFlight,double _maxHeight,double _s){
		
		timeOfFlight = _timeOfFlight;
		maxHeight = _maxHeight;
		s = _s;
		
	}
	
	public Flightdata(String _forFileOutput[]){
		
		forFileOutput = _forFileOutput;
		
	}
	
	public Flightdata(int _placeholder){
		
		placeholder = _placeholder;
		
	}
	
	public static void resultsIntoFile(){
			
		try{
			File dir = new File("Results");
			
			dir.mkdir();
			String[] dateien = dir.list();		//list of files in the directory
			
			int k = 1;
			
			if(dateien.length > 0)
				k = (k*dateien.length)+1;				//number of Files in the directory +1
			
			File dateiOut = new File("Results" + "/" + "result" + k + "_v-" +  Point.getVelocity() + "_h-"+ Point.getHeight() + "_a-" + Point.getAngle() + ".csv");	//Benennung anhand der Anzahl der vorhandenen Dateien
		
			dateiOut.createNewFile();
			
			Writer out = new FileWriter(dateiOut);
			
			writeFile(out);
			
		} catch(IOException e){
			System.out.print(e);
		} catch(ArrayIndexOutOfBoundsException ae){
			System.out.println(ae);
		}
		
	}
	
	public static void writeFile(Writer w) throws IOException{
		
		int b = 0;
		
		w.write("Startparameters:\n");
		w.write("velocity: " + Point.getVelocity() + " m/s");
		w.write("\nDischarge height: " + Point.getHeight() + " m");
		w.write("\nDischarge angle: " + Point.getAngle() + " degree");
		w.write("\n\n");
		w.write("Time,");
		w.write("x-position,");
		w.write("z-position,");
		w.write("z-velocity,");
		w.write("x-velocity\n");
		w.write("\n");
		
			for(int v = 0; v < placeholder;v++){
				
				for(int j = 0; j < saves[v].length; j++){
				
						String p = Double.toString(saves[v][j]);
						w.write(p + ",");
					
					b++;
					
					if(b % 5 == 0){						//space - after five values
						w.write("\n");
					}
				}	
			}
			w.close();
	}
	
	
	public static void askiIntoFile() throws IOException{
		
		try{
			
			File dir = new File("Ascii-Pictures");
			
			dir.mkdir();
			String[] dateien = dir.list();
			
			int k = 1;
			
			if(dateien.length > 0)
				k = (k*dateien.length)+1;
			
			File dateiOut = new File("Ascii-Pictures" + "/" + "AsciiPic_" + k + "_" +  Point.getVelocity() + "_"+ Point.getHeight() + "_" + Point.getAngle() + ".txt");
			
			dateiOut.createNewFile();
			
			Writer out = new FileWriter(dateiOut);
			
			writeAskiFile(out);	
		} catch(IOException e){
			System.out.println(e);
		} catch(ArrayIndexOutOfBoundsException ae){
			System.out.println(ae);
		}
		
	}
	
	public static void writeAskiFile(Writer w) throws IOException{
		
		
		for(int i = 0;i < forFileOutput.length; i++){
			
			w.write(forFileOutput[i] + "\n");
			
		}
		
		w.close();
		
		
		
	}
	
		
	
} 
