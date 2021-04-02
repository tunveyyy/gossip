package NodeState;

import data_structures.EndPointState;
import data_structures.InetAddressAndPort;
import handlers.PeerHandler;
import message.GossipDigest;
import message.GossipDigestSyn;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.*;

public class Gossiper {
    public static void examineGossiper(List<GossipDigest> gDigestList, List<InetAddressAndPort>deltaGossipDigestList, Map<InetAddressAndPort, EndPointState> deltaEndPointStateMap){

        Map<InetAddressAndPort, EndPointState> localStateMap = EndPointStateMap.getEndPointStateMap();

        for(GossipDigest gossipDigest : gDigestList) {

            // TODO: Application version
            if(localStateMap.containsKey(gossipDigest.getEndpoint())) {
                int remoteGeneration = gossipDigest.getGeneration();
                int localGeneration = localStateMap.get(gossipDigest.getEndpoint()).getHeartbeatState().getGeneration();
                if(localGeneration < remoteGeneration)
                    deltaGossipDigestList.add(gossipDigest.getEndpoint());
                else if(localGeneration == remoteGeneration){
                    int remoteHeartbeat = gossipDigest.getMaxVersion();
                    int localHeartbeat = localStateMap.get(gossipDigest.getEndpoint()).getHeartbeatState().getHeartbeatValue();
                    if(localHeartbeat < remoteHeartbeat) {
                        deltaGossipDigestList.add(gossipDigest.getEndpoint());
                    }
                    else if(localHeartbeat > remoteHeartbeat){
                        deltaEndPointStateMap.put(gossipDigest.getEndpoint(), localStateMap.get(gossipDigest.getEndpoint()));
                    }
                }
            }
            else {
                deltaGossipDigestList.add(gossipDigest.getEndpoint());
            }

        }

    }

    public static List<InetAddressAndPort> randomGossip(InetAddressAndPort selfAddress, int noOfNodesToGossipTo){
        Set<InetAddressAndPort> keys = EndPointStateMap.getEndPointStateMap().keySet();
        keys.remove(selfAddress);
        List<InetAddressAndPort> keyList = new ArrayList<>(keys);

        if(keys.size() < noOfNodesToGossipTo)
            return null;

        List<InetAddressAndPort> nodeList = new ArrayList<>();
        for(int i = 0; i < noOfNodesToGossipTo; i++){
            int index = new Random().nextInt(keyList.size());
            nodeList.add(keyList.get(index));
            keyList.remove(index);
        }
        return nodeList;
    }
    public void startGossip(String ip,int port,String clusterName, String partitionerId) {
        try {
            Socket socket = new Socket(ip, port);
            System.out.println("Connected to " + ip + ":" + port);

            OutputStream out = socket.getOutputStream();
            InputStream in = socket.getInputStream();
            Runnable runnable = new PeerHandler(socket, in, out, clusterName, partitionerId);
            Thread thread = new Thread(runnable);
//            Thread thread1 = new Thread(runnable);
//            Thread thread2 = new Thread(runnable);
//            Thread thread3 = new Thread(runnable);
//            Thread thread4 = new Thread(runnable);

            thread.start();
//            thread1.start();
//            thread2.start();
//            thread3.start();
//            thread4.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
