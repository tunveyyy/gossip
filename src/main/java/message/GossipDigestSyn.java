package message;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class GossipDigestSyn {
    final String clusterId;
    final String partioner;
    final List<GossipDigest> gDigests;

    // Constructor to initialize the Gossip digest Syn
    public GossipDigestSyn(String clusterId, String partioner, List<GossipDigest> gDigests) {
        System.out.println("SYN Object created");
        this.clusterId = clusterId;
        this.partioner = partioner;
        this.gDigests = gDigests;
    }

    // Return the gossip digest list
    public List<GossipDigest> getGossipDigests() {
        return gDigests;
    }
}