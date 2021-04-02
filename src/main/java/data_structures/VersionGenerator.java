package data_structures;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

public class VersionGenerator implements Serializable {
    private static final AtomicInteger version = new AtomicInteger(0);
    public static int getNextVersion() {
        return version.incrementAndGet();
    }
}
