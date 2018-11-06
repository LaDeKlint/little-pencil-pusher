/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package littlepencilpusher;
import dk.sdu.mmmi.rd1.edgedetect.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import javax.imageio.ImageIO;

/**
 *
 * @author lars
 */
public class LittlePencilPusher
{ // start class

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException
    { // start main
        EdgeDetector edge = new EdgeDetector("images/kryds.jpg");
        BufferedImage image = edge.getBufferedImage();
        File outputfile = new File("images/krydsny.jpg");
        ImageIO.write(image, "jpg", outputfile);
       
        
        //Picture picture0 = new Picture("images/androidny.jpg"); 
        
         int mag[][] = edge.getMagnitudeArray();
         
      
      
        // printMagnitudeArray(mag);
                // magnitude.append(noget);
            
       stringBuilder(mag);
        
        //System.out.println();
     
        
        
 
    } // end Main
     
    public static void printMagnitudeArray(int m[][] )
    {// start class print
        for( int row = 0 ; row < m.length ; row++)
        {//start row for loop
            for (int column = 0 ; column < m[row].length ; column++)
            {// start column for loop
               System.out.print(m[row][column] + "\t ");
               
                
                
            }// end colum for loop
            System.out.println(" end row ");
        }// end row for loop
        System.out.println(m[1][10]);
    } // end print class      
        
    // metode til stringbuilder
       public static void stringBuilder(int m[][])
       { // start method stringbuilder
           StringBuilder sb = new StringBuilder();
           
           for ( int row = 0 ; row < m.length ; row++)
           { // start row for loop
               for (int column = 0 ; column < m[row].length ; column++)
               { //start column for loop
                   
                   
               } // end column for loop
           } // end row for loop
           
           
           for (int i = 0 ; i < m.length ; i++)
           { // start i for loop
               for ( int j = 0 ; j < m[i].length ; j++)
               { // start j for loop
                   
                   sb.append("(");
                   sb.append(i);
                   sb.append(",");
                   sb.append(j);
                   sb.append(")");
                   sb.append(" : ");
                   sb.append(m[i][j]);
                   sb.append("\t");
                   
                   
               } // end j for loop
                    sb.append("\n");
               
           } // end for loop
       
           System.out.println(sb);
       } // end method  stringbuilder
        
        
        //System.out.println(magnitude + "\n");
        
    }
    
   
    
    
    
   

