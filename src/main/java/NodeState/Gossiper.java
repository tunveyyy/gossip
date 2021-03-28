package NodeState;

import data_structures.ApplicationState;
import data_structures.EndPointState;
import data_structures.InetAddressAndPort;
import message.GossipDigest;
import message.GossipDigestSyn;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

public class Gossiper {

    private static String stateFile = "state.txt";

    public static void examineGossiper(List<GossipDigest> gDigestList, List<InetAddressAndPort>deltaGossipDigestList, Map<InetAddressAndPort, EndPointState> deltaEndPointStateMap){

        Map<InetAddressAndPort, EndPointState> localStateMap = EndPointStateMap.getEndPointStateMap();

        for(GossipDigest gossipDigest : gDigestList) {

            // TODO: Application version
            if(localStateMap.containsKey(gossipDigest.getEndpoint())) {
                int remoteGeneration = gossipDigest.getGeneration();
                int localGeneration = localStateMap.get(gossipDigest.getEndpoint()).getHeartbeatState().getGeneration();
                if(localGeneration < remoteGeneration)
                    deltaGossipDigestList.add(gossipDigest.getEndpoint());
                else if(localGeneration == remoteGeneration){
                    int remoteHeartbeat = gossipDigest.getMaxVersion();
                    int localHeartbeat = localStateMap.get(gossipDigest.getEndpoint()).getHeartbeatState().getHeartbeatValue();
                    if(localHeartbeat < remoteHeartbeat) {
                        deltaGossipDigestList.add(gossipDigest.getEndpoint());
                    }
                    else if(localHeartbeat > remoteHeartbeat){
                        deltaEndPointStateMap.put(gossipDigest.getEndpoint(), localStateMap.get(gossipDigest.getEndpoint()));
                    }
                }
            }
            else {
                deltaGossipDigestList.add(gossipDigest.getEndpoint());
            }
        }
    }

    public static void saveState() {

        try {
            BufferedWriter bwr = new BufferedWriter(new FileWriter(new File(stateFile)));

            Map<InetAddressAndPort, EndPointState> localState = EndPointStateMap.getEndPointStateMap();

            for(Map.Entry<InetAddressAndPort, EndPointState> e: localState.entrySet()) {
                StringBuilder sb = new StringBuilder();
                sb.append(e.getKey().address);
                sb.append(":");
                sb.append(e.getKey().port);
                sb.append(":");
                sb.append(e.getValue().getHeartbeatState().getGeneration());
                sb.append(":");
                sb.append(e.getValue().getHeartbeatState().getHeartbeatValue());
                sb.append(":");
                sb.append(e.getValue().getApplicationState(ApplicationState.APPLICATION_VERSION));
                bwr.write(sb.toString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String pullState() throws FileNotFoundException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines( Paths.get(stateFile), StandardCharsets.UTF_8))
        {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return contentBuilder.toString();
    }
}
