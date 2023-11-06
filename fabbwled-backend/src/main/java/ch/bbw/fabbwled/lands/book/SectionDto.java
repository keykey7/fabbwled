package ch.bbw.fabbwled.lands.book;

/**
 * Datastructure to render a section of a book.
 * @param id the unique identifier
 * @param ticks (optional) checkboxes of this section
 * @param body a tree-struture to render the textual representation of this section in the current player state
 */
public record SectionDto(SectionId id, SectionTicks ticks, SectionNode body) {

	public String asPlainText() {
		var text = "---------- \033[1m" + id().sectionId() + "\033[22m ";
		text += ticks().asPlainText();
		text += "----------\n";
		text += body().asPlainText();
		return text;
	}

	/**
	 * Section Ticks are checkboxes displayed next to the section number.
	 * @param total amount of checkboxes
	 * @param ticked amount of checked checkboxes
	 */
	public record SectionTicks(int total, int ticked) {
		public SectionTicks {
			if (total < 0) {
				throw new IllegalStateException("tick-boxes must be non-negative");
			}
			if (ticked > total) {
				throw new IllegalStateException("cannot tick that many");
			}
		}

		public static SectionTicks none() {
			return new SectionTicks(0, 0);
		}

		public String asPlainText() {
			if (total == 0) {
				return "";
			}
			return "☒".repeat(ticked) +
					"☐".repeat(total - ticked) + " ";
		}
	}
}
