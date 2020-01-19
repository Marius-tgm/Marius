This program simulates a throw. To start the programm you need to compile the file Throw.java. If the compiling is finished, you will be able to insert the parameters into the program. Open the project with the command "java Throw".
The program requires some parameters you need to enter. You may do this as you start the program like this: 
java Throw [velocity] [discharge height] [discharge angle] 
Please note that you can't enter floating point numbers as paramters.
If you have not done this, you must enter a velocity (and the other paramters) while runtime. This is the initial speed at the start of the throw.
Then you must enter the starting height. You can choose an angle between -90° and 90°.
When the paramters keep the set borders, the program calculates the throw, creates a .csv-file containing the calculated values and draws the throw. The .csv-file and the drawing (.txt-file) will be saved into two folders ("results" for .csv and "ascii-Pictures" for the ascii drawing). Besides the calculated values the program can show you the maximum height, maximum velocity and minimun velocity.
