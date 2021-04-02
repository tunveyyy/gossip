package Utils;

import NodeState.EndPointStateMap;
import NodeState.Gossiper;
import data_structures.InetAddressAndPort;
import handlers.PeerHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import data_structures.*;
public class StartGossip extends TimerTask {
    String ip;
   int port;
   int noOfNeighbours;
   String clusterName;
   String partitionerId;

    public StartGossip(String ip, int port, int noOfNeighbours, String clusterName, String partitionerId) {
        this.ip = ip;
        this.port = port;
        this.noOfNeighbours = noOfNeighbours;
        this.clusterName = clusterName;
        this.partitionerId = partitionerId;
    }

    /**
     * The action to be performed by this timer task.
     */

    @Override
    public void run() {

        List<InetAddressAndPort> neighbors = null;

        System.out.println("----------------gossip triggered : " + new Date());
//        Gossiper gossiper = new Gossiper();
//        gossiper.startGossip(ip, port, "1", "1");
        InetAddressAndPort selfAddress = null;
        try {
            selfAddress = new InetAddressAndPort(InetAddress.getByName(ip), port);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        neighbors = Gossiper.randomGossip(selfAddress, noOfNeighbours);
        System.out.println("Total Neighbors: "+neighbors.size());
        EndPointState value = EndPointStateMap.getEndPointStateMap().get(selfAddress);
            if(value.getApplicationState(ApplicationState.STATUS) == MetaAppState.STATUS_NORMAL) {
                assert neighbors != null;

                for (InetAddressAndPort node : neighbors) {

                    Gossiper gossiper = new Gossiper();
                    gossiper.startGossip(node.getHostAddress(false), node.port, clusterName, partitionerId);
                }
            }
    }


}
