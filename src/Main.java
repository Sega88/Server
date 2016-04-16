/**
 * Created by Sergei on 10.04.2016.
 */

import org.omg.CORBA.portable.UnknownException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Main implements Runnable{
    static private ServerSocket server;
    static private Socket connection;
    static private ObjectOutputStream output;
    static private ObjectInputStream input;
    public static void main(String[] arg)
    {
        new Thread(new Main()).start();
    }


    @Override
    public void run(){
        try {
            server = new ServerSocket(5678, 10);
            while(true){
                connection = server.accept();
                output = new ObjectOutputStream(connection.getOutputStream());
                input = new ObjectInputStream(connection.getInputStream());
                output.writeObject("Prislali: "+(String)input.readObject());
            }//while
        } catch (UnknownException e) {
        } catch (IOException e){
        } catch (HeadlessException e){
        } catch (ClassNotFoundException e){}
    }
}
