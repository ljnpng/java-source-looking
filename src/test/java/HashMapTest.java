import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class HashMapTest {

    @Test
    public void testPut() {
        Map<Integer, String> m = new HashMap<>();
        m.put(1, "a");
        m.put(16 + 1, "b");
        m.put(16 * 2 + 1, "c");
        m.put(16 * 3+ 1, "d");
        m.put(16 * 4+ 1, "e");
        m.put(16 * 5+ 1, "f");
        m.put(16 * 6+ 1, "g");
        m.put(16 * 7+ 1, "h");
        m.put(16 * 8+ 1, "j");
        m.put(16 * 9+ 1, "i");
    }
}
