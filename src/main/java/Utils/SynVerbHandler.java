package Utils;

import java.util.*;

import data_structures.EndPointState;
import data_structures.InetAddressAndPort;
import message.GossipDigest;
import message.GossipDigestAck;
import message.GossipDigestSyn;

public class SynVerbHandler {
    public static final SynVerbHandler instance = new SynVerbHandler();

    public void doVerb(Message<GossipDigestSyn> message) {
        InetAddressAndPort from = message.from();

        GossipDigestSyn gDigestMessage = message.payload;
        List<GossipDigest> gDigestList = gDigestMessage.getGossipDigests();
        List<GossipDigest> deltaGossipDigestList = new ArrayList<>();
        Map<InetAddressAndPort, EndPointState> deltaEpStateMap = new HashMap<>();
        Gossiper.instance.examineGossiper(gDigestList, deltaGossipDigestList, deltaEpStateMap);

        Message<GossipDigestAck> gDigestAckMessage = Message.out(GOSSIP_DIGEST_ACK, new GossipDigestAck(deltaGossipDigestList, deltaEpStateMap));
        /* send message via socket */
        MessagingService.instance().send(gDigestAckMessage, from);

        // TODO: record last processed timestamp here
    }
}
