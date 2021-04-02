import NodeState.EndPointStateMap;
import NodeState.Gossiper;
import Utils.HeartBeatState;
import Utils.StartGossip;
import data_structures.*;

import java.io.*;
import java.net.InetAddress;
import java.util.Map;
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
        int port = Integer.parseInt(args[0]);
        HeartbeatState hb1 = new HeartbeatState((int) System.nanoTime(), 0);
        EndPointState ep1 = new EndPointState(hb1);
        ep1.addApplicationState(ApplicationState.STATUS, MetaAppState.STATUS_NORMAL);
        EndPointStateMap.getEndPointStateMap().put(new InetAddressAndPort(InetAddress.getByName("localhost"), port), ep1);

        System.out.println(EndPointStateMap.getEndPointStateMap());
        final String clusterName = "Cluster";
        final String partitionerId = "1";
        int noOfNeighbours = 1;
        int tGossip = 3;
        int heartBeat = 1;
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(10);

        executor.scheduleAtFixedRate(new HeartBeatState(new InetAddressAndPort(InetAddress.getByName("localhost"), port)),0,heartBeat,TimeUnit.SECONDS);
        executor.scheduleAtFixedRate(new StartGossip(ip,port,noOfNeighbours,clusterName,partitionerId), 0, tGossip, TimeUnit.SECONDS);

//        Gossiper g = new Gossiper();
//        g.startGossip(ip,port,clusterName,partitionerId);
        System.out.println("Timer Task started");
    }



}