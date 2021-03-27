import java.net.*;
import java.io.*;
import java.util.*;

public class Node {
    private Socket socket = null;
    private DataInputStream  input   = null;
    private DataOutputStream out     = null;

    static final ApplicationState[] STATES = ApplicationState.values();
    private final static int tGossip = 1000;    // 1000 milli sec
//    public static final Node instance = new Node(true);


    final Set<InetAddressAndPort> liveEndpoints = new HashSet<>();
    final Set<InetAddressAndPort> seeds = new HashSet<>();
    final Map<InetAddressAndPort, EndPointState> endpointStateMap = new HashMap<>();

    public Node(String address, int port)
    {
        try
        {
            socket = new Socket(address, port);
            System.out.println("Connected");

            input = new DataInputStream(System.in);
            out = new DataOutputStream(socket.getOutputStream());
        }
        catch (UnknownHostException u)
        {
            System.out.println(u);
        }
        catch (IOException i)
        {
            System.out.println(i);
        }

        String line = "";
        while(!line.equals("over"))
        {
            try
            {
                line = input.readLine();
                out.writeUTF(line);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try{
            input.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[])
    {

        Node client = new Node("127.0.0.1", 5000);
    }

}
