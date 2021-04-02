package message;

import data_structures.InetAddressAndPort;

import java.io.*;

//import InetAddressAndPort;

public class GossipDigest implements Serializable {
    final InetAddressAndPort endpoint;
    final int generation;
    final int maxVersion;

    // Constructor to initialize the gossip digest
    public GossipDigest(InetAddressAndPort endpoint, int generation, int heartbeat){
        this.endpoint = endpoint;
        this.generation = generation;
        this.maxVersion = heartbeat;
    }

    // Return the Generation Difference or Application version difference
    public int compareTo(GossipDigest gDigest)
    {
        if (generation != gDigest.generation)
            return (generation - gDigest.generation);  // send entire hashmap
        return (maxVersion - gDigest.maxVersion); // just request
    }

    // Return the class members on request
    public InetAddressAndPort getEndpoint()
    {
        return endpoint;
    }

    public int getGeneration()
    {
        return generation;
    }

    public int getMaxVersion()
    {
        return maxVersion;
    }

    // Return the Payload digest information
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(endpoint);
        sb.append(":");
        sb.append(generation);
        sb.append(":");
        sb.append(maxVersion);
        return sb.toString();
    }

}