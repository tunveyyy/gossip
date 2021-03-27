public class MetaAppState {

    public final static String STATUS_BOOTSTRAPPING = "BOOT";
//    public final static String STATUS_BOOTSTRAPPING_REPLACE = "BOOT_REPLACE";
    public final static String STATUS_NORMAL = "NORMAL";
    public final static String STATUS_LEAVING = "LEAVING";
    public final static String STATUS_LEFT = "LEFT";
//    public final static String STATUS_MOVING = "MOVING";

    public final static String REMOVING_TOKEN = "removing";
    public final static String REMOVED_TOKEN = "removed";

    public final static String HIBERNATE = "hibernate";
    public final static String SHUTDOWN = "shutdown";

    // values for ApplicationState.REMOVAL_COORDINATOR
//    public final static String REMOVAL_COORDINATOR = "REMOVER";

    public final int version;
    public final String value;

    private MetaAppState(String value, int version) {
        this.version = version;
        this.value = value;
    }

    private MetaAppState(String value) {
        this.value = value;
        this.version = 1;      // require version generator
    }


}
