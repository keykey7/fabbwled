package ch.bbw.fabbwled.lands.book.yaml;

import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.exception.FabledTechnicalException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SectionLoader {
    private final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

    public List<YamlSection> loadSections() {
        return yamlFiles().flatMap(this::processFile).collect(Collectors.toList());
    }

    private Stream<YamlSection> processFile(YamlFile file) {
        var builder = new ActionBuilder();

        try (var content = file.content()) {
            var contentString = new String(content.readAllBytes(), StandardCharsets.UTF_8);
            System.out.println(contentString);
            if (contentString.trim().isEmpty()) {
                // An empty file is not a valid YAML array, but we don't want to fill the file with dummy [].
                // So just return nothing.
                // FIXME(nils-heydecker): This should be removed after every YAML file has content.
                return Stream.empty();
            }
            var rawSection = Arrays.stream(mapper.readValue(contentString, RawSection[].class));

            return rawSection.map((section) -> {
                try {
                    var actions = builder.buildActions(section.content());
                    return new YamlSection(new SectionId(1, section.number()), actions);
                } catch (FabledTechnicalException e) {
                    throw new FabledTechnicalException("Invalid section: " + section.number(), e);
                }
            });
        } catch (JsonProcessingException e) {
            throw new FabledTechnicalException("Invalid YAML file: " + file.filename(), e);
        } catch (IOException e) {
            throw new FabledTechnicalException("Failed to load YAML file: " + file.filename(), e);
        } catch (FabledTechnicalException e) {
            throw new FabledTechnicalException("Error in YAML file: " + file.filename(), e);
        }
    }

    private Stream<YamlFile> yamlFiles() {
        var files = List.of("001-036.yaml",
                            "037-072.yaml",
                            "073-108.yaml",
                            "109-144.yaml",
                            "145-180.yaml",
                            "181-216.yaml",
                            "217-252.yaml",
                            "253-288.yaml",
                            "289-324.yaml",
                            "325-360.yaml",
                            "361-396.yaml",
                            "397-432.yaml",
                            "433-468.yaml",
                            "469-504.yaml",
                            "505-540.yaml",
                            "541-576.yaml",
                            "577-612.yaml",
                            "613-648.yaml",
                            "649-680.yaml"
        );

        return files.stream()
                .map(filename -> new YamlFile(filename,
                                              SectionLoader.class.getClassLoader()
                                                      .getResourceAsStream("sections/" + filename)
                ));
    }

    record YamlFile(String filename, InputStream content) {}
}
