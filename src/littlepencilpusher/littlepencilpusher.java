package littlepencilpusher;

import dk.sdu.mmmi.rd1.edgedetect.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;

public class littlepencilpusher
{ // start class

    public static void main(String[] args) throws IOException, InterruptedException
    { // start main

        /*
        Sending image to the Edgedetector, and getting a bufferet image back, meaning a representation
        og the edge we want to draw.
        The buffered image is saved as a new image file.
         */
        EdgeDetector edge = new EdgeDetector("images/rektangel.jpg");
        BufferedImage image = edge.getBufferedImage();
        File outputfile = new File("images/rektangelNy.jpg");
        ImageIO.write(image, "jpg", outputfile);

        //String plcIP = "localhost";
        String plcIP = "192.168.0.3";
        int plcPort = 12345;
        String plcOut = "X00000Y00000ZU";


        /*
        here we get an array of the edge of the image we can work with. It is a 2d int array of the 
        magnitude of the image edge. The magnitude array method has been changed to 
        return a binary truncate magnitude meaning it is eather "0" for black (draw) or "255" for white (don't draw)
         */
        int mag[][] = edge.getMagnitudeArray();

        // for method description
        printMagnitudeArray(mag);

        plcOut = coordinates(mag);
        System.out.println(plcOut.length());

        sendToPlc(plcOut, plcIP, plcPort);


    } // *******************end Main*******************************

    // Her skrives data til plc.
    public static void sendToPlc(String plcOut, String plcIP, int plcPort) throws IOException, InterruptedException
    {

        TcpClient client = new TcpClient(plcIP, plcPort);

        // Hvor mange strenge skal vi sende?
        int strLen = 4900;                                                              //længden af de strenge der skal sende
        int strDiv = plcOut.length() / strLen;                                          //antallet af hele strenge
 
        String plcOutSubstr = "";
        String resp;
        String endXY = "";
        int divCount = 0;
        
        System.out.println("Trying to send " + strDiv + " strings (plus remainder)\n");
                
        while (divCount <= strDiv)
        {
            client.connect();
            if (divCount < strDiv)
            {
                plcOutSubstr = plcOut.substring(divCount * strLen, divCount * strLen + strLen) + "Q";
            } else if (divCount == strDiv)
            {
                plcOutSubstr = plcOut.substring(divCount * strLen) + "X00000Y00000ZUQ";  //her får vi den sidste rest med og sikrer, at vi kommer hjem.
            }
            if (divCount > 0)
            {
                resp = client.write(endXY + "ZU" + plcOutSubstr);
            } else
            {
                resp = client.write(plcOutSubstr);

            }
            endXY = plcOutSubstr.substring(plcOutSubstr.length() - 15, plcOutSubstr.length() - 3); // gemmer de sidste xy-koordinater i hver streng.

            System.out.println("Sending string #" + divCount);
            System.out.println("PLC:" + resp);

            switch (resp)
            {
                case "WAIT":
                    System.out.println("Waiting for PLC ...");
                    if (client.listenForOK())
                    {
                        divCount++;
                        client.disconnect();
                        break;
                    }

                case "OK":
                    System.out.println(divCount + ": " + plcOutSubstr);
                    divCount++;
                    client.disconnect();
                    break;
                default:
                    System.out.println("PLC connection error!");
                    client.disconnect();
                    break;
            }

        }
        client.disconnect();

    } //*********************end sendToPlc**********************

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

        for (int columnB = 0; columnB < m.length; columnB++)
        { // start column for loop
            for (int rowB = 0; rowB < m.length; rowB++)
            { // start row for loop
                //System.out.print(m[columnB][rowB] + "\t");

            } // end row for loop
            //System.out.println(" end column");
        } // end column for loop

    } // ********************end print method************************

    /*
     
     */
   private static String coordinates(int n[][]) {
        
        String useless = null;
        StringBuilder result = new StringBuilder(); //this stringbuilder, builds the string that is send to the PLC
        
      

        // check rows;
        int m[][] = new int[n[0].length][n.length]; // this is a new 2D array which is transposed, to get the right position.
        
        m = transpose(n); // this method turnes the array 90 deg. 
        
        int currentvalue; // testvalue where a shift in value is detected.

        // ****************** by row ****************************************

        for (int i = 0; i < m.length; i++) 
        {
            
            currentvalue = m[i][0]; // initial value is set to j=0 for each row
           

            for (int j = 0; j < m[0].length; j++) 
            {
                
                if (currentvalue != m[i][j]) // As currentvalue is updated later ind the for-loop this statement compares the pressent value from 
                {                            // the for-loop with the last value - currentvalue. Hence this statement detects a change in value. 
                                             // the currentvalue variable is updated at line 174.
                
                    
                   
                    // the string needed to sent to the PLC has a very specific format. These following 3 string variables 
                    // is used return this exact format wich is X00000Y00000Z(U/D). the last char behind Z is a key char that 
                    // tells the PLC to raise or lover the Z axis.
                    
                    String y = null; // 
                    String z = null; // As Z is determined by an if statement, it is initiated as null.
                    String x = null; // X is likewise determined from an if statement and therefore initiated as null 
                   
                    
                    int a = j + 2;
                    int b = j + 1;
                    int c = j - 1;
                    int d = j - 2;
                    
                    if ((m[i][c] == 255 && m[i][j] == 0 && m[i][b] == 0 && m[i][a] == 255))
                        {
                           
                            continue;
                        }
                        else
                        {
                            if ((m[i][c] == 0 && m[i][j] == 0 && m[i][b] == 255 && m[i][d] == 255))
                            {
                             
                                continue;
                            }
                            else
                            {
                                
                                y = String.format("%5s", i).replace(" ", "0"); // this string sets the Y value as the i index.
                                

                                if (m[i][j] == 255) // if a change is found and the value is 255, the last value must have been 0, and therefore 
                                {
                                    
                                    x = String.format("%5s", j - 1).replace(" ", "0");
                                     
                                }
                                else
                                {
                                   
                                    x = String.format("%5s", j).replace(" ", "0");
                                    
                                }

                                if (m[i][j] == 255)
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
                                //result.append(" ");

                                
                                

                            }
                        }
                    currentvalue = m[i][j];
                }
                

            }
            
        }
       //result.append("\n");  
 //     ************************** by column **********************************
            
            for (int i = 0; i < m[0].length; i++) 
            {
            
                currentvalue = n[i][0];
            

                for (int j = 0; j < m.length; j++) 
                {
                    if (currentvalue != m[j][i]) // As currentvalue is updated later ind the for-loop this statement compares the pressent value from 
                    {                            // the for-loop with the last value - currentvalue. Hence this statement detects a change in value. 
                                                 // the currentvalue variable is updated at line 174.
                
                    
                   
                        // the string needed to sent to the PLC has a very specific format. These following 3 string variables 
                        // is used return this exact format wich is X00000Y00000Z(U/D). the last char behind Z is a key char that 
                        // tells the PLC to raise or lover the Z axis.
                    
                        String y = null;
                        String z = null; // As Z is determined by an if statement, it is initiated as null.
                        String x = null; // X is likewise determined from an if statement and therefore initiated as null 
                    
                    
                        int a = j + 2;
                        int b = j + 1;
                        int c = j - 1;
                        int d = j - 2;
                    
                        if ((m[c][i] == 255 && m[j][i] == 0 && m[b][i] == 0 && m[a][i] == 255))
                        {
                            continue;
                        }
                        else
                        {
                            if ((m[c][i] == 0 && m[j][i] == 0 && m[b][i] == 255 && m[d][i] == 255))
                            {
                                continue;
                            }
                            else
                            {
                                x = String.format("%5s", i).replace(" ", "0"); // this string sets the Y value as the i index.

                                if (m[j][i] == 255) // if a change is found and the value is 255, the last value must have been 0, and therefore 
                                {
                                    y = String.format("%5s", j - 1).replace(" ", "0");
                                }
                                else
                                {
                                    y = String.format("%5s", j).replace(" ", "0");
                                }

                                if (m[j][i] == 255)
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
                                //result.append(" ");

                            }
                        }
                    currentvalue = m[j][i];
                    }

                }
             
            }        System.out.println(result); 

        

                return result.toString();
                
        
            
    } // ***************** end method coordinates ********************
   
    public static int[][] transpose(int m[][])
    {
        if (m == null || m.length == 0)
        {
            return m;
        }

        int width = m.length;
        int height = m[0].length;

        int[][] l = new int[height][width];

        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < height; j++)
            {
                l[j][i] = m[i][j];
            }
        }
        return l;
    }

}
