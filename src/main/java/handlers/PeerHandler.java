package handlers;

import NodeState.EndPointStateMap;
import message.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class PeerHandler implements Runnable{

    final Socket socket;
    final InputStream inputStream;
    final OutputStream outputStream;
    String clusterName;
    String partitionerId;

    public PeerHandler(Socket socket, InputStream objectInputStream, OutputStream objectOutputStream,String clusterName, String partitionerId) {
        this.socket = socket;
        this.inputStream = objectInputStream;
        this.outputStream = objectOutputStream;
        this.clusterName = clusterName;
        this.partitionerId = partitionerId;
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
        System.out.println(Thread.currentThread().getName());
        try {
            ObjectOutputStream out = new ObjectOutputStream(outputStream);
            System.out.println("Sending SYN");
            out.writeObject(new GossipDigestSyn(clusterName,partitionerId, EndPointStateMap.getGossipDigests()));
            ObjectInputStream in =  new ObjectInputStream(inputStream);

            Object receivedMessage = in.readObject();
            if(receivedMessage.getClass()== GossipDigestAck.class) {
                System.out.println("Received ACK");
                GossipDigestAck message = (GossipDigestAck) receivedMessage;
                GossipDigestAck2 ack2 = new AckVerbHandler().generateAck2(message.getGossipDigestList(),message.getEndpointStateMap());
                out.writeObject(ack2);
                System.out.println("Sending ACK2");
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
