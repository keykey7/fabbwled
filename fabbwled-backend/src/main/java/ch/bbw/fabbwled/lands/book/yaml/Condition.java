package ch.bbw.fabbwled.lands.book.yaml;

public interface Condition {
    record HasTitle(String title) implements Condition {}

    record HasKeyword(String keyword) implements Condition {}

    record NeedsAtLeastShards(int amount) implements Condition {}

    record HasPossession(String possession) implements Condition {}
}
