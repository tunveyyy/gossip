package message;

import data_structures.InetAddressAndPort;

import java.io.*;
import java.net.InetAddress;

//import InetAddressAndPort;

public class GossipDigest {
    final InetAddressAndPort endpoint;
    final int generation;
    final int maxVersion;

    // Constructor to initialize the gossip digest
    GossipDigest(InetAddressAndPort ep, int gen, int version){
        endpoint = ep;
        generation = gen;
        maxVersion = version;
    }

    // Return the Generation Difference or Application version difference
    public int compareTo(GossipDigest gDigest)
    {
        if (generation != gDigest.generation)
            return (generation - gDigest.generation);
        return (maxVersion - gDigest.maxVersion);
    }

    // Return the class members on request
    public InetAddressAndPort getEndpoint()
    {
        return endpoint;
    }

    int getGeneration()
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