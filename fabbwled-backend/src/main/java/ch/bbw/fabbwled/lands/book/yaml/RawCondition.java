package ch.bbw.fabbwled.lands.book.yaml;

import ch.bbw.fabbwled.lands.character.ProfessionEnum;

public record RawCondition(String hasTitle, String hasKeyword, Integer needsAtLeastShards, Boolean isTickBoxDone, String hasPossession, ProfessionEnum hasProfession) {}


