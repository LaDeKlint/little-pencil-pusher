/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package littlepencilpusher;

import dk.sdu.mmmi.rd1.edgedetect.*;
import dk.sdu.mmmi.rd1.robotcomm.RobotClient;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author lars
 */
public class LittlePencilPusher
{

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException
    {
        EdgeDetector edge = new EdgeDetector("images/3583.jpg");
        BufferedImage image = edge.getBufferedImage();
        File outputfile = new File("images/androidny.jpg");
        ImageIO.write(image, "jpg", outputfile);

//        RobotClient client = new RobotClient("123.123.123.123", 12345);
//        
//        if(!client.isConnected())
//        {
//            client.connect();            
//        }
//        client.write("Hej");
//        client.disconnect(); 
    }

}
