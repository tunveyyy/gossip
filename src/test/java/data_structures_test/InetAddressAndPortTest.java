package data_structures_test;

import data_structures.InetAddressAndPort;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressAndPortTest {
    public static void main(String args[]) throws UnknownHostException {
        InetAddressAndPort inp = new InetAddressAndPort(InetAddress.getLocalHost(), 5001);
        System.out.println(inp.toString());
    }
}
