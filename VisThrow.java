//Schiefer Wurf v.3.0
//Gruppe 3 Informatik 1
//Patrick Scheumer, Lennart Uphaus, Marius Benkert
//c 2019,2020

import java.awt.*;
import javax.swing.*;
import java.util.concurrent.TimeUnit;
import java.lang.Math.*;

public class VisThrow{

    private double[][] saves = new double[300][5];
    private double maxHeight;
    private int placeholder;
    private double s;
    private int y_Max;
    private int x_Max;
    String visual = "";
    String[][] Aski = new String[24][105];

    public VisThrow(double _saves[][], double _maxHeight, int _placeholder, double _s){

        saves = _saves;
        maxHeight = _maxHeight;
        placeholder = _placeholder;
        s = _s;

        //jframe
        //jpanel
        //jlabel

        y_Max = define_yscale(maxHeight);
        

        x_Max = define_xscale(s);
        

            for(int i=0; i<24; i++){              //filling the array with space
                
                for(int u=0; u<105; u++){   
        
                    Aski[i][u] = " ";
                }     
        
            }
            for(int i=2; i<22; i++){
    
                Aski[i][2] = "|";
                
            }
            Aski[1][3] = "^";
            Aski[0][1] = "Height in m";
            Aski[22][103] = ">";
            Aski[22][104] = " thrown distance in m";
            for(int i=2; i<103; i++){
            
                Aski[22][i] = "-";

            }
            for(int i=2; i<103; i=i+5){      
            
                Aski[22][i] = "+";
            }
            for(int i=2; i<22; i=i+2){   							
        
                
                Aski[i][1] = "-";
            }
            Aski[1][1]="";      //emptying unnecessary spaces
            for(int i=3; i<104; i++){   							
        
                
                Aski[23][i] = "";
            }

        draw_y(y_Max);
        draw_x(x_Max);
        
        for(int i=1; i<placeholder; i++){					

                spot(saves[i][1],saves[i][2]);
              
        }
		
		String[] forFileOutput = new String[24];
        
        for(int i=0; i<24; i++){                    //fill the Aski-Data into a txt.file
            
            visual = ArraysRead(Aski[i], 105);
            System.out.println(visual);
			forFileOutput[i] = visual;

        }
		
		Flightdata data2 = new Flightdata(forFileOutput);
		
    }

    //define_yscale defines, which scale fits best to the maximum of Height, it uses a double and returns an int
    int yMax;
    double digit;
    private int define_yscale(double maxH){
        digit = digitread(maxH);
        yMax = (int)Math.pow(10.0, digit);
        while(yMax/maxH>4){
            yMax = yMax/2;
        }
        if(yMax==0)
        return 1;
        return yMax;
    }

    //define_xscale defines, which scale fits best to the thrown distance, it uses a double and returns an int
    int xMax;
    private int define_xscale(double s){
        digit = digitread(s);
        xMax = (int)Math.pow(10.0, digit);
        while(xMax/s>4){
            xMax = xMax/2;
        }
        if(xMax==0)
        return 1;
        return xMax;
    }
    //method digitread takes a given double and counts the amount of digits before the comma, which will be returned as a double
    //works up to 999.999,0 with 6 digits
    String countedNumber;        
    int count;
    int comparevalue;
    private double digitread(double number){
        countedNumber = number + "-1";
        count = -1;
        comparevalue = 0;
        do{
            comparevalue = Character.getNumericValue(countedNumber.charAt(count+1));
            count++;
        }while(comparevalue != -1);
        return (double)count;
    }
    
    //draw_y is drawing the y-scale and makes sure, that the axis stays straight
	
    double calcnumber;
    private void draw_y(int y_Maximum){
        calcnumber = y_Maximum;
        int track = 1;
        int helpoe;
        for(int i=1; i<24; i++){
            
            visual = "";
            comparevalue = (int)digitread(calcnumber);
            if(i % 2 != 0){
                
                for(int u=0; u<(int)digitread(y_Maximum); u++)
                visual = visual + " ";
    
            }
            else{
                helpoe = comparevalue;
                track = (int)digitread(y_Maximum) - helpoe;
                while(track>0){

                    visual = visual + " ";
                    track--; 

                }
                visual = visual + (int)calcnumber;
                calcnumber = calcnumber - y_Maximum / 10;
            }

            Aski[i][0] = visual;
               
               
        }
    }

    //draw_x is building a string, which includes the x-scale. It uses the int x_Max
    int u = 0;
    int a = 9;
    int e = 0;
    private void draw_x(int x_Maximum){
        visual = "";
        for(int i=1; i<11; i++){

            calcnumber = i*(x_Maximum/10);
            a = (int)(0.5*digitread(calcnumber));
           
            u = 0;
            while(u<9-(a+e)){
 
                visual = visual + " ";
                u++;
            }
            e = a;
            if(digitread(calcnumber) % 2 == 0)
                e--;
            visual = visual +  (x_Maximum/10*i);
        }
    if(s<1)
        Aski[23][3] = "        0,1       0,2       0,3       0,4       0,5       0,6       0,7       0,8       0,9        1";
    else
        Aski[23][3] = visual;
    }

 
    //this method finds the best fitting array spot 
    double comp1;
    double comp2;
    double diff1;
    double diff2; 
    double saving1;
    double saving2;
    int savingspotx = 0;
    int savingspoty = 0;
    private void spot(double x_P, double y_P){
      
        saving1 = 10000000;
        for(int i=0; i<100; i++){
  
            comp1 = i*(x_Max/100);
            diff1 = Math.abs(comp1 - x_P); 
            if(diff1 < saving1){

                saving1 = diff1;
                savingspotx = i;

            }
        }
      
        saving2 = 10000000;
        for(int i=0; i<21; i++){
       
            comp2 = i*y_Max/20;
            diff2 = Math.abs(comp2 - y_P);
           
            if(saving2>diff2){
          
                saving2 = diff2;
                savingspoty = i;
            
            }

        }
      
        Aski[22-savingspoty][2+savingspotx] = "o";
    }


    private String ArraysRead(String[] Aski, int ilength){      //turning each line of the array into a string
		String stringbuild;
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<ilength; i++){
			sb.append(Aski[i]);
		}
		
		stringbuild = sb.toString();
        return stringbuild;
    }	


}

