package ch.bbw.fabbwled.lands.service;

import ch.bbw.fabbwled.lands.book.SectionId;
import lombok.Getter;
import lombok.With;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collections;
import java.util.Set;
import java.util.function.UnaryOperator;

/**
 * A simple session (cookie) based player state.
 */
@Getter
@Component
@SessionScope
public class PlayerSession {

	private PlayerDto player = new PlayerDto("Johanna Doe", SectionId.book1(15), Collections.emptySet());

	public void update(UnaryOperator<PlayerDto> modifier) {
		player = modifier.apply(player);
	}

	@With
	public record PlayerDto(String name,
							SectionId currentSection,
							Set<String> titlesAndHonours) {
	}
}
