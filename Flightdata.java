import java.io.*;

public class Flightdata extends SchieferWurf{
	
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
		
		int b = 0;
		int t1 = 0;
		int t2 = 0;
		int t3 = 0;
		int t4 = 0;
		int t5 = 0;
		
		w.write("First line: Time \n");
		w.write("Second line: x-position \n");
		w.write("Third line: z-position \n");
		w.write("fourth line: z-velocity \n");
		w.write("fifth line: x-velocity \n");
		w.write("\n");
		
			for(int v = 0; v < placeholder;v++){
				
				for(int j = 0; j < saves[v].length; j++){
				
						String p = Double.toString(saves[v][j]);
						w.write(p + "\n");
					
					b++;
					
					if(b % 5 == 0){						//alle 5 Zeilen gibt es eine Leerzeile - zur Uebersichtlichkeit
						w.write("\n");
					}
				}	
			}
			w.close();
	}
	
	
	public static void askiIntoFile() throws IOException{
		
		try{
			
			File dir = new File("Askii-Pictures");
			
			dir.mkdir();
			String[] dateien = dir.list();
			
			int k = 1;
			
			if(dateien.length > 0)
				k = (k*dateien.length)+1;
			
			File dateiOut = new File("Askii-Pictures" + "/" + "AskiiPic_" + k + ".txt");
			
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
