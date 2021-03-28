import handlers.NodeHandler;
import handlers.PeerHandler;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Client {

//    public Client(String ip, int port, String clusterId, int partitionerId) {
//        this.ip = ip;
//        this.port = port;
//        this.clusterId = clusterId;
//        this.partitionerId = partitionerId;
//    }

    public static void main(String[] args)throws IOException {
        String ip = "localhost";
        int port = 5000;
        final String clusterName = "Cluster";
        final String partitionerId = "1";
        Socket socket = new Socket(ip, port);
        System.out.println("Connected to " + ip + ":" + port);

        OutputStream out = socket.getOutputStream();
        InputStream in = socket.getInputStream();

       // ObjectInputStream objectInputStream = new ObjectInputStream(in);
       // ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);

        Runnable runnable = new PeerHandler(socket,in,out,clusterName, partitionerId);
        Thread thread = new Thread(runnable);
        thread.start();

    }
}