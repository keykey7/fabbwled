package ch.bbw.fabbwled.lands.book.yaml;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

public class YamlSectionLoaderTest implements WithAssertions {
    private final YamlSectionLoader loader = new YamlSectionLoader();

    @Test
    void loadSingleSection() {
        var content = loader.loadSections(loader.yamlFiles());
        var s1 = content.stream().filter(s -> s.id().sectionId() == 1).findFirst().orElseThrow();

        assertThat(s1.actions()).hasSize(2);
        assertThat(s1.actions().get(1)).isOfAnyClassIn(Action.TurnToAction.class);
    }
}
