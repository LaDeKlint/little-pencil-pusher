/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package littlepencilpusher;

/**
 *
 * @author lars
 */
public class Debugger
{

    public Debugger()
    {

    }

    // Denne metode læser en streng i vores format og skriver punkterne ud så man kan se, hvad der sker.
    // Hvis den kaldes med parametren pencilDownOnly til true, får man kun de punkter, hvor Z=D.
    public void stringToConsole(String plcOut, boolean pencilDownOnly)
    {
        int x0 = 0;
        int x1 = 0;
        int y0 = 0;
        int y1 = 0;
        int dx;
        int dy;
        String z0 = "-";
        String z1 = "-";

        System.out.printf("%5s -> %5s : %5s   %5s ->%5s : %5s  %s\n", "x0", "x1", "dx", "y0", "y1", "dy", "z1");

        int i;
        for (i = 0; i < plcOut.length() - 1; i += 14)
        {
            x1 = Integer.parseInt(plcOut.substring(i + 1, i + 6));
            dx = x1 - x0;
            y1 = Integer.parseInt(plcOut.substring(i + 7, i + 12));
            dy = y1 - y0;
            z1 = plcOut.substring(i + 13, i + 14);

            if ("D".equals(z1) || !pencilDownOnly)
            {
                System.out.printf("%5d -> %5d : %5d   %5d ->%5d : %5d  %s\n", x0, x1, dx, y0, y1, dy, z1);
            }

            x0 = x1;
            y0 = y1;
            z0 = z1;

        }

    }
    
        /*
    This method prints the magnitude array values, for a grafical representation of the array.
    It was made during the testfase, and does not carry any other funtionality 
    than the test opotunity.
    The print lines have been commented out, as they are not needed for the actual program.
    The transpose method is also tested here, as an atempt to make an alternative to just
    change the index output
    */
    public void printMagnitudeArray(int n[][]) 
    {// start method print
        
        Tracer trace = new Tracer();
          
        int m[][] = new int[n[0].length][n.length];
        
        m = trace.transpose(n);
        
        for (int columnB = 0 ; columnB < m.length ; columnB++)
        { // start column for loop
            for (int rowB = 0 ; rowB < m.length ; rowB++)
            { // start row for loop
                System.out.print(m[columnB][rowB] + "\t");
                
            } // end row for loop
            System.out.println(" end column");
        } // end column for loop
        
    } // ********************end print method************************

}
