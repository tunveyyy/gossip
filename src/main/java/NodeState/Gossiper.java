package NodeState;

import data_structures.EndPointState;
import data_structures.InetAddressAndPort;
import message.GossipDigest;
import message.GossipDigestSyn;

import java.util.List;
import java.util.Map;
import java.util.Set;

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
}
