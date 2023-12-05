package ch.bbw.fabbwled.lands.book.yaml;


import ch.bbw.fabbwled.lands.character.ProfessionEnum;
import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.service.PlayerSession;


public interface Condition {
    boolean isActive(PlayerSession session, SectionId section);

    record HasTitle(String title) implements Condition {
        @Override
        public boolean isActive(PlayerSession session, SectionId section) {
            return session.getPlayer().titlesAndHonours().contains(title);
        }
    }

    record HasKeyword(String keyword) implements Condition {
        @Override
        public boolean isActive(PlayerSession session, SectionId section) {
            return session.getPlayer().codeWords().contains(keyword);
        }
    }

    record NeedsAtLeastShards(int amount) implements Condition {
        @Override
        public boolean isActive(PlayerSession session, SectionId section) {
            return session.getPlayer().shards() >= amount;
        }
    }

    record IsTickBoxDone(boolean isTickBoxDone) implements Condition {
        @Override
        public boolean isActive(PlayerSession session, SectionId section) {

            return session.getPlayer().tickBoxes().get(section) == 1;
        }
    }

    record HasPossession(String possession) implements Condition {
        @Override
        public boolean isActive(PlayerSession session, SectionId section) {
            return session.getPlayer().possessions().contains(possession);
        }
    }


    record HasProfession(ProfessionEnum profession) implements Condition {}

}
