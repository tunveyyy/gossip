import java.util.concurrent.atomic.AtomicInteger;

public class VersionGenerator {
    private static final AtomicInteger version = new AtomicInteger(0);
    public static int getNextVersion() {
        return version.incrementAndGet();
    }
}
