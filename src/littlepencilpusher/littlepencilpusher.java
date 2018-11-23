package littlepencilpusher;

import dk.sdu.mmmi.rd1.edgedetect.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import javax.imageio.ImageIO;

public class littlepencilpusher 
{ // start class

    public static void main(String[] args) throws IOException 
    { // start main
        
        /*
        Sending image to the Edgedetector, and getting a bufferet image back, meaning a representation
        og the edge we want to draw.
        The buffered image is saved as a new image file.
        */
        EdgeDetector edge = new EdgeDetector("images/Cirkel.jpg");
        BufferedImage image = edge.getBufferedImage();
        File outputfile = new File("images/Cirkelny.jpg");
        ImageIO.write(image, "jpg", outputfile);

        /*
        here we get an array of the edge of the image we can work with. It is a 2d int array of the 
        magnitude of the image edge. The magnitude array method has been changed to 
        return a binary truncate magnitude meaning it is eather "0" for black (draw) or "255" for white (don't draw)
        */  
        int mag[][] = edge.getMagnitudeArray();

        // for method description
        printMagnitudeArray(mag);
            
        coordinates(mag);
        
        /*
        Indsæt RobotComm kodelinier her!!!
        */
    
    } // *******************end Main*******************************

    
    
    
    
    
    /*
    This method prints the magnitude array values, for a grafical representation of the array.
    It was made during the testfase, and does not carry any other funtionality 
    than the test opotunity.
    The print lines have been commented out, as they are not needed for the actual program.
    The transpose method is also tested here, as an atempt to make an alternative to just
    change the index output
    */
    public static void printMagnitudeArray(int n[][]) 
    {// start method print
          
        int m[][] = new int[n[0].length][n.length];
        
        m = transpose(n);
        
        for (int columnB = 0 ; columnB < m.length ; columnB++)
        { // start column for loop
            for (int rowB = 0 ; rowB < m.length ; rowB++)
            { // start row for loop
                //System.out.print(m[columnB][rowB] + "\t");
                
            } // end row for loop
            //System.out.println(" end column");
        } // end column for loop
        
    } // ********************end print method************************
    
    
    

     
    /*
     
    */
    public static void coordinates(int n[][]) {
        
        
        StringBuilder result = new StringBuilder();

        // check rows;
        int m[][] = new int[n[0].length][n.length];
        
        m = transpose(n);
        
        int currentvalue;

        int startIndex;
        
        int counterLine = 0;

        for (int i = 0; i < n.length; i++) 
        {
            
            currentvalue = n[i][0];
            
            startIndex = 0;
            int index = i;
            int counter = 0;
            int track = 0;
            int endIndex = 0;

            for (int j = 0; j < n[0].length; j++) 
            {
                
               // System.out.print(n[i][j] + "\t");
                
                if (currentvalue != n[i][j]) 
                {

                    index = i;
                    if (counter >= 1) {
                        endIndex = j - 1;
                    }
                    
                /*    track = endIndex - startIndex;
                    if (track != 0 ) {
                        track += 1;
                    }
                    
                     if (startIndex != 0 && track == 0)
                    {
                        track += 1;
                    }
                */
                    String x = String.format("%5s", i).replace(" ", "0");
                    String z = null;
                    String y = null;
                    
                    if (n[i][j] == 255)
                    {
                        y = String.format("%5s", j - 1).replace(" ", "0");
                    }
                    else
                    {
                        y = String.format("%5s", j).replace(" ", "0");
                    }
                    
                    if (n[i][j] == 0)
                    {
                        z = "D";
                    }
                    else
                    {
                        z = "U";
                    }
                    
                    result.append("X");
                    result.append(x);
                    result.append("Y");
                    result.append(y);
                    result.append("Z");
                    result.append(z);
                    
                    
                    
                            startIndex = j;

                    currentvalue = n[i][j];
                    counter++;
                    
                }

            }

            
            counterLine ++;
            if (counterLine == 15)
            {
                result.append("\n");
                counterLine = 0;
            }
            
        }
        for (int i = 0; i < n.length; i++) 
        {
            
            currentvalue = n[0][i];
            startIndex = 0;
            int index = 0;
            int counter = 0;
            int track = 0;
            int endIndex = 0;

            for (int j = 0; j < n[0].length; j++) 
            {
                
                if (currentvalue != n[j][i]) 
                {

                    index = j;
                    if (counter >= 1) {
                        endIndex = i - 1;
                    }

                /*    track = endIndex - startIndex;
                    if (track != 0 ) {
                        track += 1;
                    }
                    
                     if (startIndex != 0 && track == 0)
                    {
                        track += 1;
                    }*/

                    
                    String y = String.format("%5s", i).replace(" ", "0");
                    String z = null;
                    String x = null;
                    
                    if (n[j][i] == 255)
                    {
                        x = String.format("%5s", j - 1).replace(" ", "0");
                    }
                    else
                    {
                        x = String.format("%5s", j).replace(" ", "0");
                    }
                    
                    if (n[j][i] == 0)
                    {
                        z = "D";
                    }
                    else
                    {
                        z = "U";
                    }
                    
                    result.append("X");
                    result.append(x);
                    result.append("Y");
                    result.append(y);
                    result.append("Z");
                    result.append(z);                    
                    
                            startIndex = i;

                    currentvalue = n[j][i];
                    counter++;
                    
                }

            }

            counterLine ++;
            if (counterLine == 15)
            {
                result.append("\n");
                counterLine = 0;
            }
            
        }
        
        result.append("Q");
        
        System.out.println(result);
            
    } // ***************** end method coordinates ********************

    
    
    public static int[][] transpose (int m[][])
    {
        if (m == null || m.length == 0)
        {
            return m;
        }
        
       int width = m.length;
       int height = m[0].length;
       
       int[][] l = new int[height][width];
       
       for (int i = 0 ; i < width ; i++)
       {
           for (int j = 0 ; j < height ; j++)
           {
               l[j][i] = m[i][j];
           }
       }
       return l;
    }
    
 
}
