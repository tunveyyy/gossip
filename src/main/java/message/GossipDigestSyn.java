package message;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class GossipDigestSyn implements Serializable{
    final String clusterId;
    final String partitioner;
    final List<GossipDigest> gDigests;

    // Constructor to initialize the Gossip digest Syn
    public GossipDigestSyn(String clusterId, String partitioner, List<GossipDigest> gDigests) {
        System.out.println("SYN Object created");
        this.clusterId = clusterId;
        this.partitioner = partitioner;
        this.gDigests = gDigests;
    }

    // Return the gossip digest list  => All the keys of the hashmap
    public List<GossipDigest> getGossipDigests() {
        return gDigests;
    }
}