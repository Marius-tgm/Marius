import java.io.*;

public class Flightdata extends SchieferWurf{
	
	double timeOfFlight;
	double maxHeight;
	double s;
	
	
	public Flightdata(double _timeOfFlight,double _maxHeight,double _s){
		
		timeOfFlight = _timeOfFlight;
		maxHeight = _maxHeight;
		s = _s;
		
	}
	
	
	public static void resultsIntoFile(){
		
		System.out.println("Das ist ein Test: " + saves[2][3]);
		
		
			
		try{
			File dir = new File("Ergebnisordner");
			
			dir.mkdir();
			String[] dateien = dir.list();		//Verzeichniseintraege aufzaehlen
			
			int k = 1;
			
			if(dateien.length > 0)
				k = (k*dateien.length)+1;				//Anzahl der Eintraege wird gezahelt und um 1 erhoeht
			
			File dateiOut = new File("Ergebnisordner" + "/" + "Ergebnisse_" + k + ".csv");	//Benennung anhand der Anzahl der vorhandenen Dateien
		
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
			
			
			for(int v = 0; v < saves.length;v++){
				
				for(int j = 0; j < saves[v].length; j++){
					
					String p = Double.toString(saves[v][j]);
					w.write(p + " ");
				}	
			}

			w.close();
	
	}
		
	
} 
