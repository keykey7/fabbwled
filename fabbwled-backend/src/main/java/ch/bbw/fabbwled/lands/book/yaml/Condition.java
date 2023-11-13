package ch.bbw.fabbwled.lands.book.yaml;

import ch.bbw.fabbwled.lands.character.ProfessionEnum;

public interface Condition {
    record HasTitle(String title) implements Condition {}

    record HasKeyword(String keyword) implements Condition {}

    record NeedsAtLeastShards(int amount) implements Condition {}

    record IsTickBoxDone(boolean isTickBoxDone) implements Condition {}

    record HasPossession(String possession) implements Condition {}

    record HasProfession(ProfessionEnum profession) implements Condition {}

}
