package ch.bbw.fabbwled.lands.book.book1;

import ch.bbw.fabbwled.lands.book.yaml.SectionLoader;
import org.junit.jupiter.api.Test;

public class YamlSectionValidationTest {
    private final SectionLoader loader = new SectionLoader();

    @Test
    void loadAllSections() {
        loader.loadSections(loader.yamlFiles());
    }
}
