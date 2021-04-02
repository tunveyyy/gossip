package NodeState;

import data_structures.EndPointState;
import data_structures.InetAddressAndPort;
import message.GossipDigest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EndPointStateMap {
    public static Map<InetAddressAndPort, EndPointState> getEndPointStateMap() {
        return endPointStateMap;
    }

    public static Map<InetAddressAndPort,EndPointState> endPointStateMap = new HashMap<>();
    /*
    public static void setEndPointStateMap(Map<InetAddressAndPort, EndPointState> endPointStateMap) {
        EndPointStateMap.endPointStateMap = endPointStateMap;
    }*/


    public static List<GossipDigest> getGossipDigests(){
        List<GossipDigest> digests = new ArrayList<>();
        for(Map.Entry<InetAddressAndPort,EndPointState> entry : endPointStateMap.entrySet()) {
            GossipDigest digest = new GossipDigest(entry.getKey(),entry.getValue().getHeartbeatState().getGeneration(),entry.getValue().getHeartbeatState().getHeartbeatValue());
            digests.add(digest);
        }
        return digests;
    }
}
