package data_structures_test;


import data_structures.EndPointState;
import data_structures.HeartbeatState;

public class EndPointStateTest {

    public static void main(String[] args) {

        EndPointState epState = new EndPointState(new HeartbeatState(1));
        System.out.println(epState.toString());

    }
}
