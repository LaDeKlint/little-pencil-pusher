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
        EdgeDetector edge = new EdgeDetector("images/android500.jpg");
        BufferedImage image = edge.getBufferedImage();
        File outputfile = new File("images/output.jpg");
        ImageIO.write(image, "jpg", outputfile);

        String plcIP = "localhost";
        //String plcIP = "192.168.0.3";
        int plcPort = 12345;
        String plcOut = "X00000Y00000ZU";

        Tracer tracer = new Tracer();


        /*
        here we get an array of the edge of the image we can work with. It is a 2d int array of the 
        magnitude of the image edge. The magnitude array method has been changed to 
        return a binary truncate magnitude meaning it is eather "0" for black (draw) or "255" for white (don't draw)
         */
        int[][] mag = edge.getMagnitudeArray();
        
        

        plcOut = tracer.coordinates(mag);
        System.out.println(plcOut.length());

        sendToPlc(plcOut, plcIP, plcPort);

        Debugger debug = new Debugger();
        
        debug.printMagnitudeArray(mag);
        debug.stringToConsole(plcOut,false);

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
//                        TimeUnit.SECONDS.sleep(3);
//                        client.disconnect();
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

}