package ch.bbw.fabbwled.lands.book;

import java.util.Collections;
import java.util.List;

public interface FabledSection {

	SectionId getId();

	String getText();

	default List<SimpleGoto> getGoto() {
		return Collections.emptyList();
	}

}
