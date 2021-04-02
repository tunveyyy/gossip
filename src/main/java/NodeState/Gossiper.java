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
    public static void examineGossiper(List<GossipDigest> gDigestList, List<GossipDigest> deltaGossipDigestList, Map<InetAddressAndPort, EndPointState> deltaEndPointStateMap){

        Map<InetAddressAndPort, EndPointState> localStateMap = EndPointStateMap.getEndPointStateMap();

        for(GossipDigest gossipDigest : gDigestList) {

            // TODO: Application version
            if(localStateMap.containsKey(gossipDigest.getEndpoint())) {
                int remoteGeneration = gossipDigest.getGeneration();
                int localGeneration = localStateMap.get(gossipDigest.getEndpoint()).getHeartbeatState().getGeneration();
                if(localGeneration < remoteGeneration)
                    deltaGossipDigestList.add(gossipDigest);
                else if(localGeneration == remoteGeneration){
                    int remoteHeartbeat = gossipDigest.getMaxVersion();
                    int localHeartbeat = localStateMap.get(gossipDigest.getEndpoint()).getHeartbeatState().getHeartbeatValue();
                    if(localHeartbeat < remoteHeartbeat) {
                        deltaGossipDigestList.add(gossipDigest);
                    }
                    else if(localHeartbeat > remoteHeartbeat){
                        deltaEndPointStateMap.put(gossipDigest.getEndpoint(), localStateMap.get(gossipDigest.getEndpoint()));
                    }
                }
            }
            else {
                deltaGossipDigestList.add(gossipDigest);
            }

        }

    }

    public static List<InetAddressAndPort> randomGossip(InetAddressAndPort selfAddress, int noOfNodesToGossipTo){
        System.out.println("in random gossip\n");
        Set<InetAddressAndPort> keys = EndPointStateMap.getEndPointStateMap().keySet();
        keys.remove(selfAddress);
        List<InetAddressAndPort> keyList = new ArrayList<>(keys);
        System.out.println("KeyList: "+keyList);
        if(keys.size() < noOfNodesToGossipTo)
            return null;
        System.out.println("start for random gsp, key size: \n"+keyList.size());
        List<InetAddressAndPort> nodeList = new ArrayList<>();
        for(int i = 0; i < noOfNodesToGossipTo; i++){
            int index = new Random().nextInt(keyList.size());
            nodeList.add(keyList.get(index));
            keyList.remove(index);
        }
        System.out.println("end of random gsp: "+nodeList.toString());
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
            thread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
