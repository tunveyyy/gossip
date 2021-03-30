package Utils;

import java.util.TimerTask;

import data_structures.ApplicationState;
import data_structures.EndPointState;
import data_structures.MetaAppState;

public class HeartBeatState extends TimerTask {
    /**
     * The action to be performed by this timer task.
     */
    private EndPointState currEpState;
    public HeartBeatState(EndPointState ep){
        currEpState = ep;
    }

    @Override
    public void run() {
        //TODO
        if(currEpState.getApplicationState(ApplicationState.STATUS) == MetaAppState.STATUS_NORMAL) {
            currEpState.getHeartbeatState().updateHeartBeat();    
        }
        //TODO: fix required in addApplicationState under EndPointState.java
        // if(currEpState.getApplicationState(ApplicationState.STATUS) == MetaAppState.STATUS_BOOTSTRAPPING) {
        //     currEpState.addApplicationState(ApplicationState.STATUS, MetaAppState.STATUS_NORMAL);
        // }
        
    }
}
