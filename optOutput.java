import java.awt.*;
import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class optOutput
{
    String[][] Askii = new String[28][108];  /*initiating the Array for Askii- visualization*/
    int Laenge = 10000;
    int Hoehe = 10000;
    double maxHeight;
    double timeOfFlight;     
	
    public optOutput(double _saves[][], double _maxHeight, double _timeOfFlight ) throws Exception
    {
        String Ausgabe;
        double yposition;
        JLabel[] iAusgabe = new JLabel[28];     
        int count = 0;        
        timeOfFlight = _timeOfFlight;
        maxHeight = _maxHeight;
        
        if(timeOfFlight<=1000)      //categorizing the magnitude of the throw
        Laenge = 1000;
        if(timeOfFlight<=100)
        Laenge = 100;
        if(timeOfFlight<=10)
        Laenge = 10;
        
        if(maxHeight<=1000)
        Hoehe = 1000;
        if(maxHeight<=100)
        Hoehe = 100;
        if(maxHeight<=10)
        Hoehe = 10;

        JFrame window = new JFrame(); 
        Font mono = new Font("Monospaced",Font.PLAIN,14);
      
           
        
  
        
                                            //general build up of the graph: the string [0][0] is in the upper left corner

        for(int i=0; i<28; i++)              //filling the array with space
        {
            for(int u=0; u<108; u++)   
            {
                Askii[i][u] = " ";
            }      
        }
        Askii[0][0] = "Flughoehe in m";
        Askii[1][2] = "     ";
        Askii[1][3] = "^";
        for(int i=2; i<22; i++)
        {
            Askii[i][3] = "|";
            Askii[i][0] = "     "; /*five times space, making room for height with max. five digits*/
        }
        Askii[22][0] = "     ";
        Askii[2][1] = "";
        Askii[22][105] = ">";
        Askii[22][107] = " Zeit in s";
        for(int i=3; i<105; i++)
        {
            Askii[22][i] = "-";
        }
        for(int i=3; i<103; i=i+5)      
        {
            Askii[22][i] = "+";
        }
        int u = 0;
        for(int i=2; i<22; i=i+2)   /*scale of y-axis*/
        {
            u = i/2-1;
            Askii[i][2] = "-";
            switch(Hoehe)
            {
                case 10000:
                Askii[i][0] = String.valueOf(Hoehe-u*Hoehe/10);
                break;
                case 1000:
                Askii[i][0] ="  " + String.valueOf(Hoehe-u*Hoehe/10);
                break;
                case 100:
                Askii[i][0] ="   " + String.valueOf(Hoehe-u*Hoehe/10);
                break;
                case 10:
                Askii[i][0] ="    " + String.valueOf(Hoehe-u*Hoehe/10);
                break;
                default:
                break;
            }
            
        }
    
        for(int i=5; i<105; i++)
        {
            Askii[23][i] = "";
        }  
        switch(Laenge)
        {
            case 10000:
            Askii[23][5] = "  0        1000      2000      3000      4000      5000      6000      7000      8000      9000      10000";
            break;
            case 1000:
            Askii[23][5] = "  0        100       200       300       400       500       600       700       800       900       1000";
            break;
            case 100:
            Askii[23][5] = "  0         10        20        30        40        50        60        70        80        90        100";
            break;
            case 10:
            Askii[23][5] = "  0         1         2         3         4         5         6         7         8         9         10";
            break;
            default:
            break;
        }
        
        boolean fill = true;
        for(int i=0; i<_saves.length; i++)
        {
            fill = timeselect(_saves[i][0], Laenge);    //evaluates timebased, wether a data will be drawn
            if (fill=true)
            {
                yposition = round(_saves[i][2], Hoehe);
                
                if(yposition==0)
                {    
                    paint(xpos(_saves[i][0],Laenge), 0);
                }
                else if(yposition==Hoehe)
                {    
                    paint(xpos(_saves[i][0],Laenge), 21);
                }
                else
                {     
                    paint(xpos(_saves[i][0],Laenge), (int)(yposition*2));
                    
                }
            
                for(int o=0; o<28; o++)                  
                {
                    Ausgabe = Arraysauslesen(Askii[o],108);     //creating a string for each row of the graph
                    iAusgabe[o] = new JLabel("Label" + o);      //filling up the array with labels
                    iAusgabe[o].setText(Ausgabe);       //converting Strings into labels
                    iAusgabe[o].setFont(mono);        //changing font
                }
                TimeUnit.MILLISECONDS.sleep(100);
                jgraph(iAusgabe, count, window);
                count++;


            }

        }

        //
        

    }

    public static String Arraysauslesen(String[] Askii, int iLaenge)      //turning each line of the array into a string
	{
		String Ausgabe;
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<iLaenge; i++)
		{
			sb.append(Askii[i]);
			
		}
		Ausgabe = sb.toString();
		return Ausgabe;
    }
    
    public static void jgraph(JLabel[] i, int _count, JFrame window)       //opens a window to use JLabels, which can be changed visually. 
    {
       
        
        window.setSize(1100,650);
        window.setTitle("optische Ausgabe ");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        JPanel Pan = new JPanel();
        Pan.setSize(1000,500);
        Pan.setLocation(10, 10);
        Pan.setLayout(new GridLayout(i.length,1));
        
        window.add(Pan);

        Pan.setVisible(true);
        window.setVisible(true);
        
        
        for(int o=0; o<i.length; o++)
        {
            Pan.add(i[o]);
        }
    Pan.revalidate();
    Pan.repaint();
        
    }

    public static boolean timeselect(double a, int Laenge)  //figures out, wether the time of a data fits the scale for it to be drawn
    {
        int i = 0;
        boolean b = false;

        while(i!=100)
        {
            if(a+0.01*Laenge*i==Laenge)
            {
                b = true;
                break;
            }
            else
            {
                i++;
            }
        }
        return b;
    }
    
    public static double round(double number, int _Hoehe)          // rounds the number to fit it into the graph
    {
        if(number==0||number==10||number==100||number==1000||number==10000)    //they have to be treated differently, as they have one decimal place more
        return (int)number;
        
        switch(_Hoehe)
        {
            case 100: number = number/10;
            case 1000: number = number/100;
            case 10000: number = number/1000;
            default: 
        }
        

        int[] rnumber = new int[4];     //rnumber[1] is always -1, since its describing the comma
        double hnumber;
        
        rnumber = distribute(number);
        
        if(rnumber[3]>=5)
            rnumber[2]++;
        if(rnumber[2]>=3&&rnumber[1]<7)
            rnumber[2] = 5;
            
        else
        {
            if(rnumber[2]>=7)
                rnumber[0]++;
        }
       
        hnumber = rnumber[0]*10 + rnumber[2];
        
        return (hnumber/10);    //put the number in the right size for the graph
    }

    public static int[] distribute(double number)     //reads the first three characters of a number and put them into an array
    {    
                   
        String tmpString = number + "0001";     //the number has to have at least three digits
        int[] numberArray = new int[4];
        for (int i=0; i<4; i++) 
        {
            
                numberArray[i] = Character.getNumericValue(tmpString.charAt(i));  
               
        }   
        return numberArray;
    }

    public static int xpos(double time, int _Laenge)
    {
        
        switch(_Laenge)
        {
            case 100: return (int)(time);
            case 10: return (int)(time*10);
            case 1000: return (int)((time/10));
            case 10000: return (int)((time/100));
            default: return 1;
        }
    }

    public void paint(int _X, int _Y)
    {
        this.Askii[22-_Y][3+_X] = "o";
    }
        
    
}