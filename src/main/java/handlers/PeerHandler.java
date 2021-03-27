package handlers;

import message.ACK;
import message.ACK2;
import message.SYN;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class PeerHandler implements Runnable{

    final Socket socket;
    final InputStream inputStream;
    final OutputStream outputStream;
    public PeerHandler(Socket socket, InputStream objectInputStream, OutputStream objectOutputStream) {
        this.socket = socket;
        this.inputStream = objectInputStream;
        this.outputStream = objectOutputStream;
    }

    /*
    Node is acting as a Client for peers
    Sends SYN
    Receives ACK
    Sends ACK2
    */
    /**
     * When an object implementing interface {@code Runnable} is used
     * to create a thread, starting the thread causes the object's
     * {@code run} method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method {@code run} is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(outputStream);
            out.writeObject(new SYN());
            ObjectInputStream in =  new ObjectInputStream(inputStream);
            if(in.readObject().getClass()== ACK.class) {
                System.out.println("Received ACK");
                ACK2 ack2 = new ACK2().generateACK2Object();
                out.writeObject(ack2);
                System.out.println("Sending ACK2");
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
