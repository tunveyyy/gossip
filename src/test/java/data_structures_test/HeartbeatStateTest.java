package data_structures_test;

import data_structures.HeartbeatState;

public class HeartbeatStateTest {


    public static void main(String args[]) {
        HeartbeatState heartbeat = new HeartbeatState(1);
        System.out.println(heartbeat.toString());

        heartbeat.updateHeartbeat();
        System.out.println(heartbeat.toString());
    }
}
