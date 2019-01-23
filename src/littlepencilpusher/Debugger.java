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

    public void stringToConsole(String plcOut)
    {
        int x0 = 0;
        int x1 = 0;
        int y0 = 0;
        int y1 = 0;
        int dx;
        int dy;
        String z0 = "-";
        String z1 = "-";

        int i;
        for (i = 0; i < plcOut.length() - 1; i += 14)
        {
            x1 = Integer.parseInt(plcOut.substring(i + 1, i + 6));
            dx = x1 - x0;
            y1 = Integer.parseInt(plcOut.substring(i + 7, i + 12));
            dy = y1 - y0;
            z1 = plcOut.substring(i + 13, i + 14);
            System.out.println("x0=" + x0 + " x1=" + x1 + " dx=" + dx + "    y0=" + y0 + " y1=" + y1 + " dy=" + dy + " z1=" + z1);
            x0 = x1;
            y0 = y1;
            z0 = z1;

        }

    }

}
