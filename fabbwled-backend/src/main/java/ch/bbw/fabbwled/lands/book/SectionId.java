package ch.bbw.fabbwled.lands.book;

import ch.bbw.fabbwled.lands.exception.FabledBusinessException;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

/**
 * @param bookId unique book identifier (1-6)
 * @param sectionId unique section number
 */
@JsonSerialize(using = SectionId.Serializer.class)
@JsonDeserialize(using = SectionId.Deserializer.class)
public record SectionId(int bookId, int sectionId) {

	public SectionId {
		if (bookId != 1) {
			throw new IllegalArgumentException("currently we only support book 1 and not " + bookId);
		}
		if (sectionId < 1 || 680 < sectionId) {
			throw new IllegalArgumentException("fishy looking section number " + sectionId);
		}
	}

    public static SectionId parse(String content) {
        var parts = content.split(":");
        if (parts.length != 2) {
            throw new FabledBusinessException("Section ID must be in BOOK:SECTION format");
        }
        try {
            var book = Integer.parseInt(parts[0]);
            var section = Integer.parseInt(parts[1]);
            return new SectionId(book, section);
        } catch (NumberFormatException ex) {
            throw new FabledBusinessException("Invalid section ID format, must be BOOK:SECTION", ex);
        }
    }

	public static SectionId book1(int sectionId) {
		return new SectionId(1, sectionId);
	}

    @Override
    public String toString() {
        return this.bookId + ":" + this.sectionId;
    }

    public static class Serializer extends StdSerializer<SectionId> {
        public Serializer() {
            this(null);
        }
        public Serializer(Class<SectionId> t) {
            super(t);
        }

        @Override
        public void serialize(SectionId value, JsonGenerator gen, SerializerProvider provider) throws IOException {
            gen.writeString(value.toString());
        }
    }

    public static class Deserializer extends StdDeserializer<SectionId> {
        public Deserializer() {
            this(null);
        }

        public Deserializer(Class<?> vc) {
            super(vc);
        }

        @Override
        public SectionId deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
            return SectionId.parse(p.getValueAsString());
        }
    }

    public static class SectionIdKeyDeserializer extends KeyDeserializer {
        @Override
        public Object deserializeKey(String key, DeserializationContext ctxt) throws IOException {
            return SectionId.parse(key);
        }
    }
}
