package message;

import java.net.InetAddress;
import java.util.*;
import data_structures.EndPointState;

public class GossipDigestAck2
{
    final Map<InetAddress, EndPointState> epStateMap;
    GossipDigestAck2(Map<InetAddress, EndPointState> epStateMap)
    {
        this.epStateMap = epStateMap;
    }
    Map<InetAddress, EndPointState> getEndpointStateMap()
    {
        return epStateMap;
    }
}   