package ch.bbw.fabbwled.lands.book.yaml;

import ch.bbw.fabbwled.lands.service.PlayerSession;

public interface Condition {
    boolean isActive(PlayerSession session);

    record HasTitle(String title) implements Condition {
        @Override
        public boolean isActive(PlayerSession session) {
            return session.getPlayer().titlesAndHonours().contains(title);
        }
    }

    record HasKeyword(String keyword) implements Condition {
        @Override
        public boolean isActive(PlayerSession session) {
            // TODO: keywords
            return false;
        }
    }

    record NeedsAtLeastShards(int amount) implements Condition {
        @Override
        public boolean isActive(PlayerSession session) {
            return session.getPlayer().shards().getShardCount() >= amount;
        }
    }

    record IsTickBoxDone(boolean isTickBoxDone) implements Condition {
        @Override
        public boolean isActive(PlayerSession session) {
            // TODO: implement
            return false;
        }
    }

    record HasPossession(String possession) implements Condition {
        @Override
        public boolean isActive(PlayerSession session) {
            // TODO: implement
            return false;
        }
    }

}
