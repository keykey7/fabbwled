package ch.bbw.fabbwled.lands.book.yaml;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

public class YamlSectionLoaderTest implements WithAssertions {
    private final YamlSectionLoader loader = new YamlSectionLoader();

    @Test
    void loadSingleSection() {
        var content = loader.loadSections(loader.yamlFiles());
        var s15 = content.stream().filter(s -> s.id().sectionId() == 15).findFirst().orElseThrow();

        assertThat(s15.actions()).hasSize(2);
        assertThat(s15.actions().get(0)).isOfAnyClassIn(Action.TextAction.class);
        assertThat(s15.actions().get(1)).isOfAnyClassIn(Action.IfAction.class);
    }
}
