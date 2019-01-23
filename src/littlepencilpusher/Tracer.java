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
public class Tracer
{

    public Tracer()
    {

    }
    
    // Det er meningen, at Peters nye metoder skal ind her.
    // Indtil videre er det den gamle coordinates().

    public String coordinates(int n[][])
    {

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
                    } else
                    {
                        if ((m[i][c] == 0 && m[i][j] == 0 && m[i][b] == 255 && m[i][d] == 255))
                        {

                            continue;
                        } else
                        {

                            y = String.format("%5s", i).replace(" ", "0"); // this string sets the Y value as the i index.

                            if (m[i][j] == 255) // if a change is found and the value is 255, the last value must have been 0, and therefore 
                            {

                                x = String.format("%5s", j - 1).replace(" ", "0");

                            } else
                            {

                                x = String.format("%5s", j).replace(" ", "0");

                            }

                            if (m[i][j] == 255)
                            {
                                z = "D";
                            } else
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
                    } else
                    {
                        if ((m[c][i] == 0 && m[j][i] == 0 && m[b][i] == 255 && m[d][i] == 255))
                        {
                            continue;
                        } else
                        {
                            x = String.format("%5s", i).replace(" ", "0"); // this string sets the Y value as the i index.

                            if (m[j][i] == 255) // if a change is found and the value is 255, the last value must have been 0, and therefore 
                            {
                                y = String.format("%5s", j - 1).replace(" ", "0");
                            } else
                            {
                                y = String.format("%5s", j).replace(" ", "0");
                            }

                            if (m[j][i] == 255)
                            {
                                z = "D";
                            } else
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

        }
        System.out.println(result);

        return result.toString();

    } // ***************** end method coordinates ********************

    public int[][] transpose(int m[][])
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
