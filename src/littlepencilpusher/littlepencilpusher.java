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
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import javax.imageio.ImageIO;

/**
 *
 * @author lars
 */
public class littlepencilpusher { // start class

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException { // start main
        EdgeDetector edge = new EdgeDetector("images/figur.jpg");
        BufferedImage image = edge.getBufferedImage();
        File outputfile = new File("images/figurny.jpg");
        ImageIO.write(image, "jpg", outputfile);

        //Picture picture0 = new Picture("images/androidny.jpg"); 
        int mag[][] = edge.getMagnitudeArray();

        printMagnitudeArray(mag);
        // magnitude.append(noget);

        //stringBuilder(mag);
        //System.out.println();
        //printCoordinatesByRow(mag);    
        coordinates(mag);
        
        //int testCoordinates = testCoordinates(mag);
    } // end Main

    public static void printMagnitudeArray(int m[][]) {// start class print
        for (int row = 0; row < m.length; row++) {//start row for loop
            for (int column = 0; column < m[row].length; column++) {// start column for loop
                //System.out.print(m[row][column] + "\t ");

            }// end colum for loop
            //System.out.println(" end row ");
        }// end row for loop
        //System.out.println(m[1][10]);
    } // end print class      

    
    
    
    
    
    
    
    public static void coordinates(int n[][]) {

        StringBuilder resultRow = new StringBuilder();

        // check rows;
        int m[][] = new int[n[0].length][n.length];
        
        m = transpose(n);
        
        int currentvalueR;

        int startIndexR;

        for (int i = 0; i < n.length; i++) {
            currentvalueR = n[i][0];
            startIndexR = 0;
            int indexR = i;
            int counterR = 0;
            int trackR = 0;
            int endIndexR = 0;

            for (int j = 0; j < n[0].length; j++) {
                
               // System.out.print(n[i][j] + "\t");
                
                if (currentvalueR != n[i][j]) {

                    indexR = i;
                    if (counterR >= 1) {
                        endIndexR = j - 1;

                    }
                    trackR = endIndexR - startIndexR;
                    if (trackR != 0 ) {
                        trackR += 1;
                    }
                    
                     if (startIndexR != 0 && trackR == 0)
                    {
                        trackR += 1;
                    }

                     System.out.print(indexR + "; " + startIndexR + ":track " + trackR + "\t");
                    startIndexR = j;

                    currentvalueR = n[i][j];
                    counterR++;
                    //System.out.print(startIndex + "; " + endIndex + "\t" );

                }

            }

            System.out.print("\n");  
            resultRow.append("\n");
        }

        //System.out.println(resultRow);
        //System.out.println(count);
        
        
        
        
         StringBuilder resultColumn = new StringBuilder();
        // check colums;
        int currentvalueC;

        int startIndexC;

        for (int i = 0; i < n.length; i++) {
            currentvalueC = n[i][0];
            startIndexC = 0;
            int indexC = i;
            int counterC = 0;
            int trackC = 0;
            int endIndexC = 0;

            for (int j = 0; j < n[0].length; j++) {
                
                //System.out.print(n[i][j] + "\t");
                
                if (currentvalueC != n[i][j]) {

                    indexC = i;
                    if (counterC >= 1) {
                        endIndexC = j - 1;

                    }
                    trackC = endIndexC - startIndexC;
                    if (trackC != 0 ) {
                        trackC += 1;
                    }
                    
                    if (startIndexC != 0 && trackC == 0)
                    {
                        trackC += 1;
                    }

                     System.out.print(indexC + "; " + startIndexC + ":track " + trackC + "\t");
                    startIndexC = j;

                    currentvalueC = n[i][j];
                    counterC++;
                    //System.out.print(startIndex + "; " + endIndex + "\t" );

                }

            }

            System.out.print("\n");  
            resultRow.append("\n");
        }

        //System.out.println(resultRow);
        //System.out.println(count);
    }

   
    
    
    
    
    
    
    
    
    
    

    public static void coordinatesDiagonalyUp(int n[][]) {

    }

    public static void coordinatesDiagonalyDown(int n[][]) {
        // check left-rising diagonals
        for (int i = 0; i < n.length; i++) {
            for (int j = i + 1, k = 1; j < n.length && k < n[j].length;
                    j++, k++) {
                if (n[j][k] == 0) {
                    int d = j + 1;
                    int e = k + 1;
                    int f = j - 1;
                    int g = k - 1;

                    if ((n[j][k] == 0 && n[d][e] == 0) || (n[j][k] == 0 && n[f][g] == 0)) {
                        //System.out.print(j + ", " + k + "\t");  
                    }

                }

            }
        }
        System.out.println("\n");
        for (int i = 0; i < n.length; i++) {
            for (int j = i + 1, k = n[i].length - 2; j < n.length && k >= 0; j++, k--) {
                if (n[j][k] == 0) {
                    int d = j + 1;
                    int e = k - 1;
                    int f = j - 1;
                    int g = k + 1;

                    if ((n[j][k] == 0 && n[d][e] == 0) || (n[j][k] == 0 && n[f][g] == 0)) {
                        //System.out.print(j + ", " + k + "\t");  
                    }
                }

            }
        }

    }

    public static int track(int endIndex, int startIndex) {
        int track = endIndex - startIndex;

        return track;
    }

    public static int testCoordinates(int n[][]) {
        int useless = 0;
        StringBuilder sb_0 = new StringBuilder();

        StringBuilder sb_255 = new StringBuilder();

        for (int i = 0; i < n.length; i++) {
            for (int j = 0; j < n[i].length; j++) {
                int d = j - 1;
                int e = j + 1;

                if ((n[i][j] == 0 && n[i][d] == 0) || (n[i][j] == 0 && n[i][e] == 0)) {

                }

            }
        }

        System.out.print(sb_0);

        System.out.println(sb_255);

        return useless;
    }

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
