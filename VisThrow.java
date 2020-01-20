//Schiefer Wurf v.3.0
//Gruppe 3 Informatik 1
//Patrick Scheumer, Lennart Uphaus, Marius Benkert
//c 2019,2020

import java.awt.*;
import javax.swing.*;
import java.util.concurrent.TimeUnit;
import java.lang.Math.*;

public class VisThrow{

    private double[][] saves = new double[300][5]; //array with the same size, that the results array of the main method has
    private double maxHeight;
    private int placeholder; //number of results
    private double s;  //thrown distance
    private int y_Max;  // x_Max and y_Max each are the biggest number of the scalings
    private int x_Max;
    String visual = "";
    String[][] ASCII = new String[24][105];    //the array in which the parts of the graph are put together

    public VisThrow(double _saves[][], double _maxHeight, int _placeholder, double _s){

        saves = _saves;
        maxHeight = _maxHeight;
        placeholder = _placeholder;
        s = _s;


        y_Max = define_yscale(maxHeight);   //the given maximum reached height and length are used to find a well fitting scaling
        

        x_Max = define_xscale(s);
        
        //build up of the ASCII visualization, starting with the basics
            for(int i=0; i<24; i++){              //filling the array with space
                
                for(int u=0; u<105; u++){   
        
                    ASCII[i][u] = " ";
                }     
        
            }
            for(int i=2; i<22; i++){
    
                ASCII[i][2] = "|";
                
            }
            ASCII[1][3] = "^";
            ASCII[0][1] = "Height in m";
            ASCII[22][103] = ">";
            ASCII[22][104] = " thrown distance in m";
            for(int i=2; i<103; i++){
            
                ASCII[22][i] = "-";

            }
            for(int i=2; i<103; i=i+5){      
            
                ASCII[22][i] = "+";
            }
            for(int i=2; i<22; i=i+2){   							
        
                
                ASCII[i][1] = "-";
            }
            ASCII[1][1]="";      //emptying unnecessary spaces
            for(int i=3; i<104; i++){   							
        
                
                ASCII[23][i] = "";
            }

        draw_y(y_Max);      //adding the throw specific numbers to the ASCII array
        draw_x(x_Max);
        
        for(int i=1; i<placeholder; i++){		// assign each result to a spot of the ASCII array

                spot(saves[i][1],saves[i][2]);
              
        }
		
		String[] forFileOutput = new String[24];
        
        for(int i=0; i<24; i++){                    
            
            visual = ArraysRead(ASCII[i], 105); //the content of ASCII is put together in a few strings
            System.out.println(visual); //the strings are drawn
			forFileOutput[i] = visual;  

        }
		//for the possibility of using the visual output later, it is transferred to Fligthdata
		Flightdata data2 = new Flightdata(forFileOutput);   
		
    }

    //define_yscale defines, which scale fits best to the maximum of Height, it uses a double and returns an int
    int yMax;
    double digit;
    private int define_yscale(double maxH){
        digit = digitread(maxH);    //checking the number of digits before the comma
        yMax = (int)Math.pow(10.0, digit);  //calculating a value, which is big enough for the throw
        while(yMax/maxH>2){     //but small enough for the graph to take a meaningful amount of space
            yMax = yMax/2;  //of the screen
        }
        if(yMax==0)
        return 1;
        return yMax;
    }

    //define_xscale defines, which scale fits best to the thrown distance, it uses a double and returns an int
    int xMax;
    private int define_xscale(double s){       //works the same way as define_yscale does
        digit = digitread(s);
        xMax = (int)Math.pow(10.0, digit);
        while(xMax/s>2){
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
        countedNumber = number + "-1"; //changing the double into a string to be safe, in case of no comma existing
        count = -1;
        comparevalue = 0;
        do{    //each char of the string gets investigated. Loop counts chars until the comma arrives
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
            if(i % 2 != 0){  //just every second row will localize a number
                            //everything else gets the right amount of space
                for(int u=0; u<(int)digitread(y_Maximum); u++)
                visual = visual + " ";
    
            }
            else{
                helpoe = comparevalue;
                track = (int)digitread(y_Maximum) - helpoe; //if the number has less digits than y_Maximum,
                while(track>0){     //extra space is added

                    visual = visual + " ";
                    track--; 

                }
                visual = visual + (int)calcnumber;
                calcnumber = calcnumber - y_Maximum / 10;
            }

            ASCII[i][0] = visual;
               
               
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
            while(u<9-(a+e)){ //method tries to place the numbers right in the middle
 
                visual = visual + " ";
                u++;
            }
            e = a;
            if(digitread(calcnumber) % 2 == 0)  //even and odd amount of digits have to be treated slightly different
                e--;
            visual = visual +  (x_Maximum/10*i);
        }
    if(s<1)
        ASCII[23][3] = "        0,1       0,2       0,3       0,4       0,5       0,6       0,7       0,8       0,9        1";
    else
        ASCII[23][3] = visual;
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
      
        saving1 = 10000000;   //this value is selected to make sure, the loop is starting on the first run
        for(int i=0; i<100; i++){
            //the best spot is, where the difference between the actual value and calculated value of the 
            //ASCII spot is the smallest
            comp1 = i*(x_Max/100);
            diff1 = Math.abs(comp1 - x_P); 
            if(diff1 < saving1){

                saving1 = diff1;
                savingspotx = i;

            }
        }
        //works exactly the same as above
        saving2 = 10000000;
        for(int i=0; i<21; i++){
       
            comp2 = i*y_Max/20;
            diff2 = Math.abs(comp2 - y_P);
           
            if(saving2>diff2){
          
                saving2 = diff2;
                savingspoty = i;
            
            }

        }
        //drawing of ASCII sybmol
        ASCII[22-savingspoty][2+savingspotx] = "o";
    }

    //turning each line of the array into a string
    private String ArraysRead(String[] ASCII, int ilength){      
		String stringbuild;
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<ilength; i++){
			sb.append(ASCII[i]);
		}
		
		stringbuild = sb.toString();
        return stringbuild;
    }	


}

