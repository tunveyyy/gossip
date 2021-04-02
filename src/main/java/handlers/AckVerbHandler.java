package handlers;

import NodeState.EndPointStateMap;
import data_structures.EndPointState;
import data_structures.InetAddressAndPort;
import message.GossipDigest;
import message.GossipDigestAck2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AckVerbHandler {

    public Map<InetAddressAndPort, EndPointState> doVerb(List<GossipDigest> gDigestList, Map<InetAddressAndPort, EndPointState> receivedStateMap) {

        Map<InetAddressAndPort, EndPointState> localEpStateMap = EndPointStateMap.getEndPointStateMap();

        Map<InetAddressAndPort, EndPointState> deltaEpStateMap = new HashMap();
        System.out.println("Creating delta list and map");
        for (GossipDigest gDigest : gDigestList)
        {
           deltaEpStateMap.put(gDigest.getEndpoint(), localEpStateMap.get(gDigest));
        }

       for(Map.Entry<InetAddressAndPort, EndPointState> e: receivedStateMap.entrySet()){

           if(e.getValue().getHeartbeatState().getGeneration() > localEpStateMap.get(e.getKey()).getHeartbeatState().getGeneration())
               localEpStateMap.put(e.getKey(),e.getValue());
       }

       return localEpStateMap;
        // TODO: record last processed message here
    }

    public GossipDigestAck2 generateAck2(List<GossipDigest> gDigestList, Map<InetAddressAndPort, EndPointState> epStateMap) {

        return  new GossipDigestAck2(doVerb(gDigestList, epStateMap));

    }
}
