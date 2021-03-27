package handlers;

import message.SYN;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class PeerHandler implements Runnable{

    final Socket socket;
    final ObjectInputStream inputStream;
    final ObjectOutputStream outputStream;
    public PeerHandler(Socket socket, ObjectInputStream objectInputStream, ObjectOutputStream objectOutputStream) {
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
        System.out.println("Inside run");
        try {
            outputStream.writeObject(new SYN());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
