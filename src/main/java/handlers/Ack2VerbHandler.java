package handlers;

import NodeState.EndPointStateMap;
import data_structures.EndPointState;
import data_structures.InetAddressAndPort;
import message.GossipDigestAck2;

import java.util.Map;

public class Ack2VerbHandler {

    public void doVerb(Map<InetAddressAndPort,EndPointState> receivedStateMap)
    {
        Map<InetAddressAndPort, EndPointState> localEpStateMap = EndPointStateMap.getEndPointStateMap();

        System.out.println("Updating my endPointState");
        for(Map.Entry<InetAddressAndPort, EndPointState> e: receivedStateMap.entrySet()){
            if(e.getValue().getHeartbeatState().getGeneration() > localEpStateMap.get(e.getKey()).getHeartbeatState().getGeneration())
                localEpStateMap.put(e.getKey(),e.getValue());
        }
        /* Notify the Failure Detector */
//        Gossiper.instance.notifyFailureDetector(remoteEpStateMap);
//        Gossiper.instance.applyStateLocally(remoteEpStateMap);

    }


}
