public class EndPointStateTest {

    public static void main(String[] args) {

        EndPointState epState = new EndPointState(new HeartbeatState(1));
        System.out.println(epState.toString());

    }
}
