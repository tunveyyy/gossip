package handlers;

import java.util.*;

import NodeState.Gossiper;
import data_structures.EndPointState;
import data_structures.InetAddressAndPort;
import message.GossipDigest;
import message.GossipDigestAck;
import message.GossipDigestSyn;

public class SynVerbHandler {

    public GossipDigestAck doVerb(List<GossipDigest> gDigestList) {

        List<GossipDigest> deltaGossipDigestList = new ArrayList<>();  //expects to get it from the node
        Map<InetAddressAndPort, EndPointState> deltaEpStateMap = new HashMap<>(); //Send data to the node which it does not have

        System.out.println("Examining the Gossip");
        Gossiper.examineGossiper(gDigestList, deltaGossipDigestList, deltaEpStateMap);

        return new GossipDigestAck(deltaGossipDigestList, deltaEpStateMap);

        // TODO: record last processed timestamp here
    }

    public GossipDigestAck generateAck(List<GossipDigest> gDigestList) {

        return  doVerb(gDigestList);

    }
}
