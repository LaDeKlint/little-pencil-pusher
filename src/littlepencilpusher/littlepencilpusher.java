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
public class littlepencilpusher
{ // start class

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException
    { // start main
        EdgeDetector edge = new EdgeDetector("images/figur.jpg");
        BufferedImage image = edge.getBufferedImage();
        File outputfile = new File("images/figurny.jpg");
        ImageIO.write(image, "jpg", outputfile);
       
        
        //Picture picture0 = new Picture("images/androidny.jpg"); 
        
         int mag[][] = edge.getMagnitudeArray();
         
      
      
        // printMagnitudeArray(mag);
                // magnitude.append(noget);
            
       stringBuilder(mag);
        
        //System.out.println();
        
        int a[][] = new int[7][7];
            a[0][0] = 255;
            a[0][1] = 255;
            a[0][2] = 255;
            a[0][3] = 255;
            a[0][4] = 255;
            a[0][5] = 255;
            a[0][6] = 255;
            
            a[1][0] = 255;
            a[1][1] = 0;
            a[1][2] = 255;
            a[1][3] = 255;
            a[1][4] = 255;
            a[1][5] = 0;
            a[1][6] = 255;
            
            a[2][0] = 255;
            a[2][1] = 255;
            a[2][2] = 0;
            a[2][3] = 255;
            a[2][4] = 0;
            a[2][5] = 255;
            a[2][6] = 255;
            
            a[3][0] = 255;
            a[3][1] = 255;
            a[3][2] = 255;
            a[3][3] = 0;
            a[3][4] = 255;
            a[3][5] = 255;
            a[3][6] = 255;
            
            
            a[4][0] = 255;
            a[4][1] = 255;
            a[4][2] = 0;
            a[4][3] = 255;
            a[4][4] = 0;
            a[4][5] = 255;
            a[4][6] = 255;
            
            a[5][0] = 255;
            a[5][1] = 0;
            a[5][2] = 255;
            a[5][3] = 255;
            a[5][4] = 255;
            a[5][5] = 0;
            a[5][6] = 255;
            
            a[6][0] = 255;
            a[6][1] = 255;
            a[6][2] = 255;
            a[6][3] = 255;
            a[6][4] = 255;
            a[6][5] = 255;
            a[6][6] = 255;
            
            
        int coordinates = coordinates(mag);
    
        int testCoordinates = testCoordinates(mag);
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
                    
           for (int i = 0 ; i < m.length ; i++)
           { // start i for loop
               for ( int j = 0 ; j < m[i].length ; j++)
               { // start j for loop
                   int magnitude = m[j][i];
                  
                   /*
                   sb.append("(");
                   sb.append(i);
                   sb.append(",");
                   sb.append(j);
                   sb.append(")");
                   sb.append(" : ");
                   */
                   
                   if (magnitude == 0)
                   {
                       sb.append(magnitude);
                       sb.append("   ");
                   }
                   else
                   {
                     sb.append(magnitude); 
                     sb.append(" ");  
                   }
                    sb.append(" ");
                   
                   
                   
               } // end j for loop
                    sb.append("\n");
               
           } // end i for loop
       
           System.out.println(sb);
       } // end method  stringbuilder
        
        
        //System.out.println(magnitude + "\n");
        
       
    public static int coordinates(int n[][]) 
    {
       
        StringBuilder coordinates = new StringBuilder();
        
       int useless = 0; 
       int count = 0;
        // check rows
        
    for (int i = 0; i < n.length ; i++) 
    {
      for (int j = 0; j < n[0].length ; j++) 
      {
        int d = j + 1;
        int e = j - 1;
          
        if (d < n[0].length)  
        {
            if ((n[i][j] == 0 && n[i][d] == 0) || (n[i][j] == 0 && n[i][e] == 0))
            { 
                
                coordinates.append("(" + i + "," + j + ")\t");
                //count ++;
            }
        }
        
      }
        //System.out.println("\n");  
         
       coordinates.append("\n");
    }
    
    //System.out.println(coordinates);
        //System.out.println(count);
        
        // check columns
    for (int i = 0; i < n.length; i++) 
    {
      for (int j = 0; j < n[i].length; j++) 
      {
        int d = i + 1;
        int e = i - 1;
       
        
        if (n[i][j] == 0)  
        {
            if ((0 == n[j][i] && 0 == n[j][d]) || (0 == n[j][i] && 0 == n[j][e]))
            { 
               
              
                     //System.out.print(i + ", " + j + "\t");
              
                
               
                
               
             
            }
        }
      }
      //System.out.println("\n");
    } 
    
    
      // check left-rising diagonals
    for (int i = 0; i < n.length; i++) 
    {
      for (int j = i + 1, k = 1; j < n.length && k < n[j].length;
        j++, k++) {
          if (n[j][k] == 0) 
          {
            int d = j + 1;
            int e = k + 1;
            int f = j - 1;
            int g = k - 1;
            
            if ((n[j][k] == 0 && n[d][e] == 0) || (n[j][k] == 0 && n[f][g] == 0))
            {
              //System.out.print(j + ", " + k + "\t");  
            }
                        
          }
          
      }
    }
        System.out.println("\n");
    for (int i = 0; i < n.length; i++) 
    {
      for (int j = i + 1, k = n[i].length - 2; j < n.length && k >= 0; j++, k--) 
      {
          if (n[j][k] == 0) 
          {
              int d = j + 1;
            int e = k - 1;
            int f = j - 1;
            int g = k + 1;
            
            if ((n[j][k] == 0 && n[d][e] == 0) || (n[j][k] == 0 && n[f][g] == 0))
            {
              //System.out.print(j + ", " + k + "\t");  
            }
          }
          
      }
    }
       
    
     return useless;
    }
    
    
    public static int testCoordinates (int n[][])   
    {
        int useless = 0;
        StringBuilder sb_0 = new StringBuilder();
        
        StringBuilder sb_255 = new StringBuilder();
        
        
        for (int i = 0 ; i < n.length ; i++ )
                {
                 for (int j = 0 ; j < n[i].length ; j++)
                 {
                   int d = j - 1;
                   int e = j + 1;
                  
                   
                   if ((n[i][j] == 0 && n[i][d] == 0) || (n[i][j] == 0 && n[i][e] == 0))
                   {
                       
                   }
                    
                  
                 }
                }
       
       
       System.out.print(sb_0);
        
        System.out.println(sb_255);
        
        
        
        return useless;
    }
    
}
