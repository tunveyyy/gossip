package message;

import java.io.Serializable;
import java.net.InetAddress;
import java.util.*;
import data_structures.EndPointState;
import data_structures.InetAddressAndPort;

public class GossipDigestAck2 implements Serializable{
    final Map<InetAddressAndPort, EndPointState> epStateMap;
    public GossipDigestAck2(Map<InetAddressAndPort, EndPointState> epStateMap)
    {
        this.epStateMap = epStateMap;
    }
    public Map<InetAddressAndPort, EndPointState> getEndpointStateMap()
    {
        return epStateMap;
    }
}   