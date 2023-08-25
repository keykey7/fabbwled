package ch.bbw.fabbwled.lands.book;

/**
 * @param bookId unique book identifier (1-6)
 * @param sectionId unique section number
 */
public record SectionId(int bookId, int sectionId) {

	public SectionId {
		if (bookId != 1) {
			throw new IllegalArgumentException("currently we only support book 1 and not " + bookId);
		}
		if (sectionId < 1 || 680 < sectionId) {
			throw new IllegalArgumentException("fishy looking section number " + sectionId);
		}
	}

	public static SectionId book1(int sectionId) {
		return new SectionId(1, sectionId);
	}
}
