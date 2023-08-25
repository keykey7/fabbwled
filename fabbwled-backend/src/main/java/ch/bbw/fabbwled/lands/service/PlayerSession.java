package ch.bbw.fabbwled.lands.service;

import ch.bbw.fabbwled.lands.book.SectionId;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

/**
 * A simple session (cookie) based player state.
 */
@Data
@Component
@SessionScope
public class PlayerSession {

	private String name = "Johanna Doe";

	private SectionId currentSection = SectionId.book1(15);

}
