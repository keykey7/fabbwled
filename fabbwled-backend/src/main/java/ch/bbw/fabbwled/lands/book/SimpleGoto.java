package ch.bbw.fabbwled.lands.book;

import lombok.NonNull;

public record SimpleGoto(String description, @NonNull SectionId target) {
	public static SimpleGoto noDescription(SectionId target) {
		return new SimpleGoto(null, target);
	}
}
