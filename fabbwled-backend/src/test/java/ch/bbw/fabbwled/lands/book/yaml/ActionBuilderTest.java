package ch.bbw.fabbwled.lands.book.yaml;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

public class ActionBuilderTest implements WithAssertions {
    private final ActionBuilder builder = new ActionBuilder();

    @Test
    void singleText() {
        var raw = fromSingleQuoteJson("{'text': 'hello'}");
        var built = builder.build(raw);
        assertThat(built).isEqualTo(new Action.TextAction("hello"));
    }

    // Using single quote JSON makes it easier to write in a string literal.
    private RawAction fromSingleQuoteJson(String json) {
        var mapper = new ObjectMapper(new JsonFactory());
        try {
            return mapper.readValue(json.replaceAll("'", "\""), RawAction.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
