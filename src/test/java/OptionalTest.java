import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class OptionalTest {

    @Test
    void test_flatMap() {
        Optional<String> opt = Optional.of("Hello");
        Optional<String> opt2 = opt.flatMap(s -> Optional.of(s + " World"));
        Assertions.assertEquals("Hello World", opt2.get());
    }

    @Test
    void test_map() {
        Optional<String> opt = Optional.of("Hello");
        Optional<String> opt2 = opt.map(s -> s + " World");
        Assertions.assertEquals("Hello World", opt2.get());
    }

}
