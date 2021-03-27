package Utils;

import data_structures.EndPointState;
import data_structures.InetAddressAndPort;
import message.GossipDigestAck2;

public class Ack2VerbHandler {
    public static final Ack2VerbHandler instance = new Ack2VerbHandler();

    public void doVerb(Message<GossipDigestAck2> message)
    {
        Map<InetAddressAndPort, EndPointState> remoteEpStateMap = message.payload.getEndpointStateMap();
        /* Notify the Failure Detector */
        Gossiper.instance.notifyFailureDetector(remoteEpStateMap);
        Gossiper.instance.applyStateLocally(remoteEpStateMap);

        // TODO:
    }


}
