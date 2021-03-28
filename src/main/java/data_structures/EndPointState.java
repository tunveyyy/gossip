package data_structures;



import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class EndPointState {
    private HeartbeatState hbState;
    private final AtomicReference<Map<ApplicationState, MetaAppState>> applicationState;

    private boolean isActive;
    private long updateTimestamp;       /**/

    public EndPointState(HeartbeatState hbState) {
        this(hbState, new EnumMap<ApplicationState, MetaAppState>(ApplicationState.class));
    }

    EndPointState(HeartbeatState hbState, Map<ApplicationState, MetaAppState> appStates) {
        this.hbState = hbState;
        this.applicationState = new AtomicReference<Map<ApplicationState, MetaAppState>>(new EnumMap<ApplicationState, MetaAppState>(appStates));
        updateTimestamp = System.nanoTime();
        isActive = true;
    }

    public HeartbeatState getHeartbeatState() {
        return hbState;
    }

    public void setHeartbeatState(HeartbeatState hbState) {
        updateTimestamp();
        this.hbState = hbState;
    }

    public MetaAppState getApplicationState(ApplicationState key) {
        return applicationState.get().get(key);
    }

    public Set<Map.Entry<ApplicationState, MetaAppState>> states() {
        return applicationState.get().entrySet();
    }

    public void addApplicationState(ApplicationState key, MetaAppState value) {
        addApplicationStates(Collections.singletonMap(key, value));
    }

    public void addApplicationStates(Map<ApplicationState, MetaAppState> values) {
        addApplicationStates(values.entrySet());
    }

    public void addApplicationStates(Set<Map.Entry<ApplicationState, MetaAppState>> values)
    {
        while (true) {
            Map<ApplicationState, MetaAppState> orig = applicationState.get();
            Map<ApplicationState, MetaAppState> copy = new EnumMap<ApplicationState, MetaAppState>(orig);

            for (Map.Entry<ApplicationState, MetaAppState> value : values)
                copy.put(value.getKey(), value.getValue());

            if (applicationState.compareAndSet(orig, copy))
                return;
        }
    }

    public long getUpdateTimestamp() {
        return updateTimestamp;
    }

    void updateTimestamp() {
        updateTimestamp = System.nanoTime();
    }

    public boolean isNormalState() {
        return getStatus().equals(MetaAppState.STATUS_NORMAL);
    }
    public String getStatus() {
        MetaAppState status = getApplicationState(ApplicationState.STATUS);
        return status.value;
    }
    public String toString() {
        return "EndpointState: HeartBeatState = " + hbState + ", AppStateMap = " + applicationState.get();
    }

}
