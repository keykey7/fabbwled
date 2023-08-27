package ch.bbw.fabbwled.lands.book;

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
	SectionNode getBody();

	/**
	 * Indicated player interaction with this section. The id is an internal identifier of this section.
	 * @param id an action identifier as defined by {@code #getBody}.
	 */
	void onClick(int id);

	/**
	 * In case this section requires ticks/checkboxes.
	 * @return checkboxes (default is none)
	 */
	default SectionDto.SectionTicks getTicks() {
		return SectionDto.SectionTicks.none();
	}
}
