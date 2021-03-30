import Utils.HeartBeatState;
import Utils.StartGossip;
import data_structures.*;

import java.io.*;
import java.util.concurrent.*;

public class Client {

    String ip;
    int port;
    String clusterName;
    String partitionerId;
//    public Client(String ip, int port, String clusterName, String partitionerId) throws IOException {
//        this.ip = ip;
//        this.port = port;
//        this.clusterName = clusterName;
//        this.partitionerId = partitionerId;
//        //initiateGossip(Gossiper.randomGossip());
//
//    }

    public static void main(String[] args) throws IOException {
        String ip = "localhost";
        int port = 5001;
        HeartbeatState hb1 = new HeartbeatState((int) System.nanoTime(), 0);
        EndPointState ep1 = new EndPointState(hb1);
        final String clusterName = "Cluster";
        final String partitionerId = "1";
        int noOfNeighbours = 3;
        int tGossip = 3;
        int heartBeat = 1;
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(10);

        executor.scheduleAtFixedRate(new StartGossip(ip,port,noOfNeighbours,clusterName,partitionerId), 0, tGossip, TimeUnit.SECONDS);
        executor.scheduleAtFixedRate(new HeartBeatState(ep1),0,heartBeat,TimeUnit.SECONDS);

        System.out.println("Timer Task started");
    }



}