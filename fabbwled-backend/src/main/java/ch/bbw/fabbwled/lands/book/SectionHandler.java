package ch.bbw.fabbwled.lands.book;

import ch.bbw.fabbwled.lands.character.PlayerDto;

/**
 * Implementations of this are expected to be annotated by {@code org.springframework.stereotype.Component}.
 * Each section implements the rendering and the decision logic by this.
 */
public interface SectionHandler {

	/**
	 * @return The unique ID of this section
	 */
	SectionId getId();

	/**
	 *
	 * @return the idempotent content of this section.
	 * @see SectionNode
	 */
	SectionNode getBody(PlayerDto current);

	/**
	 * In case this section requires ticks/checkboxes.
	 * @return checkboxes (default is none)
	 */
	default int getMaxTicks() {
		return 0;
	}
}
