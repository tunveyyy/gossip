package Utils;

import java.util.TimerTask;

import NodeState.EndPointStateMap;
import data_structures.ApplicationState;
import data_structures.EndPointState;
import data_structures.InetAddressAndPort;
import data_structures.MetaAppState;

public class HeartBeatState extends TimerTask {
    /**
     * The action to be performed by this timer task.
     */
    private InetAddressAndPort selfKey;
    public HeartBeatState(InetAddressAndPort selfKey){
        this.selfKey = selfKey;
    }

    @Override
    public void run() {
        //TODO
//        System.out.println("\n ip address: "+selfKey);
        System.out.println("inside hbstate: "+EndPointStateMap.getEndPointStateMap().entrySet());;
        EndPointState currEpState = EndPointStateMap.getEndPointStateMap().get(selfKey);
        if(currEpState.getApplicationState(ApplicationState.STATUS) == MetaAppState.STATUS_NORMAL) {
            System.out.println("updating heartbeat: "+currEpState.toString());
            currEpState.getHeartbeatState().updateHeartBeat();
        }
        //TODO: fix required in addApplicationState under EndPointState.java
        if(currEpState.getApplicationState(ApplicationState.STATUS) == MetaAppState.STATUS_BOOTSTRAPPING) {
            currEpState.addApplicationState(ApplicationState.STATUS, MetaAppState.STATUS_NORMAL);
        }
        
    }
}
