package ch.bbw.fabbwled.lands;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;

public class JsonSchemaTest extends FabledTestBase {
    static String getRootDirPath() {
        // I tested it with both opening fabblwed in IntelliJ and fabbled-backend in IntelliJ,
        // and it returned the same path both times. So this must be good!
        return System.getProperty("user.dir");
    }

    @Test
    void schemaValidJson() {
        try {
            new ObjectMapper().readValue(Paths.get(getRootDirPath() + "/section-schema.json").toFile(), Map.class);
        } catch (IOException e) {
            throw new RuntimeException("failed to open section-schema.json. this is a bug in the test, direct your complaints to nils", e);
        }
    }
}
