package message;
import data_structures.EndPointState;
import data_structures.InetAddressAndPort;

import java.io.Serializable;
import java.net.InetAddress;
import java.util.*;
public class GossipDigestAck implements Serializable {
    final List<InetAddressAndPort> gDigestList;
    final Map<InetAddressAndPort, EndPointState> epStateMap;
    public GossipDigestAck(List<InetAddressAndPort> gDigestList, Map<InetAddressAndPort, EndPointState> epStateMap)
    {
        this.gDigestList = gDigestList;
        this.epStateMap = epStateMap;
    }
    public List<InetAddressAndPort> getGossipDigestList()
    {
        return gDigestList;
    }
    public Map<InetAddressAndPort, EndPointState> getEndpointStateMap()
    {
        return epStateMap;
    }
}