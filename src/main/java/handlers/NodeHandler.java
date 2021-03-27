package handlers;

import Utils.GenerateACK;
import message.ACK;
import message.ACK2;
import message.SYN;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class NodeHandler implements Runnable {
    Socket socket;
    ObjectInputStream objectInputStream;
    ObjectOutputStream objectOutputStream;

    public NodeHandler(Socket socket, ObjectInputStream objectInputStream, ObjectOutputStream objectOutputStream) {
        this.socket = socket;
        this.objectInputStream = objectInputStream;
        this.objectOutputStream = objectOutputStream;
    }

    /*
    Node is acting as a Server
    Receives SYN
    Sends ACK
    Receives ACK2
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
            Object receivedMessage = objectInputStream.readObject();
            if(receivedMessage.getClass()== SYN.class) {
                System.out.println("Received SYN Object");
                ACK ack = new GenerateACK().generateACKObject();
                objectOutputStream.writeObject(ack);
            }
            if(receivedMessage.getClass()== ACK2.class){
                System.out.println("Got ACK2. Now I am Updating my own node list");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
