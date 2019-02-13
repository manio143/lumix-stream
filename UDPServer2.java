import java.net.*;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.*;
import javax.swing.*;
import java.util.Arrays;

public class UDPServer2 {

    DatagramSocket theSocket = null;
    int serverPort = 49199;
    private InetAddress myip;

    BufferedImage bufferedImage = null;

    public UDPServer2() {
        try {
            myip = InetAddress.getLocalHost();
            theSocket = new DatagramSocket(serverPort);
            System.err.println(
                    "UDP Socket on IP address " + myip.getHostAddress() + " on port " + serverPort + " created");
        } catch (SocketException ExceSocket) {
            System.out.println("Socket creation error : " + ExceSocket.getMessage());
        } catch (UnknownHostException e) {
            System.out.println("Cannot find myip");
        }

    }

    public void clientRequest() {
        DatagramPacket theRecievedPacket;
        byte[] outBuffer;
        byte[] inBuffer;
        byte[] soi;
        int offset = 132;

        inBuffer = new byte[30000];
        while (true) {
            try {
                theRecievedPacket = new DatagramPacket(inBuffer, inBuffer.length, myip, serverPort);
                theSocket.receive(theRecievedPacket);
                outBuffer = theRecievedPacket.getData();

                for (int i = 130; i < 320; i += 1) {
                    if (outBuffer[i] == -1 && outBuffer[i + 1] == -40) {
                        offset = i;
                    }
                }

                byte[] newBuffer = Arrays.copyOfRange(outBuffer, offset, theRecievedPacket.getLength());

                System.out.write(newBuffer);

                // bufferedImage = ImageIO.read(new ByteArrayInputStream(newBuffer));

            } catch (IOException ExceIO) {
                System.err.println("Error with client request : " + ExceIO.getMessage());
            }
        }
        //theSocket.close();
    }

    public static void main(String[] args) {
        UDPServer2 theServer = new UDPServer2();
        theServer.clientRequest();
    }
}