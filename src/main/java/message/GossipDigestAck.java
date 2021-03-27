package message;
import data_structures.EndPointState;
import data_structures.InetAddressAndPort;

import java.net.InetAddress;
import java.util.*;
public class GossipDigestAck
{
    final List<GossipDigest> gDigestList;
    final Map<InetAddressAndPort, EndPointState> epStateMap;
    public GossipDigestAck(List<GossipDigest> gDigestList, Map<InetAddressAndPort, EndPointState> epStateMap)
    {
        this.gDigestList = gDigestList;
        this.epStateMap = epStateMap;
    }
    public List<GossipDigest> getGossipDigestList()
    {
        return gDigestList;
    }
    public Map<InetAddressAndPort, EndPointState> getEndpointStateMap()
    {
        return epStateMap;
    }
}