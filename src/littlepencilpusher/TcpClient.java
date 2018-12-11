/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package littlepencilpusher;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lars
 */
public class TcpClient
{

    private String hostname;
    private int port;
    private Socket connection;
    private PrintWriter out;
    // tilføjet
    private BufferedReader in;

    /**
     *
     * @param hostname Hostname of the robot
     * @param port Port of the robot
     */
    public TcpClient(String hostname, int port)
    {
        this.hostname = hostname;
        this.port = port;
    }

    /**
     * Method which connects to the robot, using the parameters provided to the
     * constructor.
     */
    public void connect()
    {
        try
        {
            connection = new Socket(hostname, port);
            out = new PrintWriter(connection.getOutputStream(), true);
            //tilføjet in-variabel der lytter på svar fra server-streamen
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        } catch (IOException ex)
        {
            Logger.getLogger(TcpClient.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error connecting to robot: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     * This method is used to determine if a connection has been established to
     * the robot.
     *
     * @return COnnection state to see if connection is established (true) or
     * not (false).
     */
    public boolean isConnected()
    {
        return connection.isConnected();
    }

    /**
     * This method writes a message to the robot iff a connection to the robot
     * is established.
     *
     * @param message The message to write to the robot.
     * @return
     * @throws java.io.IOException
     */
    // tilføjet: returnerer svar fra server
    public String write(String message) throws IOException
    {
        if (isConnected())
        {
            out.print(message);
            out.flush();
            String resp = in.readLine();
            return resp;
        } else
        {
            return "no response!";
        }
    }
    
    // følgende metode er tilføjtet. Den lytter på inputstreamen og returnerer TRUE, hvis vi modtager et 'OK'
    public boolean listenForOK() throws IOException
    {
        boolean ok = false;
        String resp;
        do
        {
            resp = in.readLine();
            System.out.println("PLC: "+ resp);
           
            if ("OK".equals(resp))
            {
                ok = true;                
                break;
            }

        } while (resp != null);
        return ok;

    }

    /**
     * Method to close connection to the robot.
     */
    public void disconnect()
    {
        if (isConnected())
        {
            try
            {
                connection.close();
            } catch (IOException ex)
            {
                Logger.getLogger(TcpClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
