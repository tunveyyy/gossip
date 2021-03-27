package Utils;

import data_structures.EndPointState;
import data_structures.InetAddressAndPort;
import message.GossipDigest;
import message.GossipDigestAck;

import java.util.List;
import java.util.Map;

public class AckVerbHandler {
    public static final AckVerbHandler instance = new AckVerbHandler();

    public void doVerb(Message<GossipDigestAck> message) {
        InetAddressAndPort from = message.from();

        GossipDigestAck gDigestAckMessage = message.payload;
        List<GossipDigest> gDigestList = gDigestAckMessage.getGossipDigestList();
        Map<InetAddressAndPort, EndPointState> epStateMap = gDigestAckMessage.getEndpointStateMap();

        Map<InetAddressAndPort, EndPointState> deltaEpStateMap = new HashMap<InetAddressAndPort, EndPointState>();
        for (GossipDigest gDigest : gDigestList)
        {
            InetAddressAndPort addr = gDigest.getEndpoint();
            EndPointState localEpStatePtr = Gossiper.instance.getStateForVersionBiggerThan(addr, gDigest.getMaxVersion());
            if (localEpStatePtr != null)
                deltaEpStateMap.put(addr, localEpStatePtr);
        }

        Message<GossipDigestAck2> gDigestAck2Message = Message.out(GOSSIP_DIGEST_ACK2, new GossipDigestAck2(deltaEpStateMap));

        MessagingService.instance().send(gDigestAck2Message, from);

        // TODO: record last processed message here
    }
}
