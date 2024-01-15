package ch.bbw.fabbwled.lands.book.book1;

import ch.bbw.fabbwled.lands.book.yaml.YamlSectionLoader;
import org.junit.jupiter.api.Test;

class YamlSectionValidationTest {
    private final YamlSectionLoader loader = new YamlSectionLoader();

    @Test
    void loadAllSections() {
        loader.loadSections(loader.yamlFiles());
    }
}
