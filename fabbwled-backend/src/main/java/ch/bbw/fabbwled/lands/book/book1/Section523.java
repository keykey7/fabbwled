package ch.bbw.fabbwled.lands.book.book1;

import ch.bbw.fabbwled.lands.book.SectionHandler;
import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.book.SectionNode;
import ch.bbw.fabbwled.lands.character.AbilityEnum;
import ch.bbw.fabbwled.lands.character.PlayerDto;
import org.springframework.stereotype.Component;

import java.util.List;

import static ch.bbw.fabbwled.lands.character.ProfessionEnum.*;

@Component
public class Section523 implements SectionHandler {
    @Override
    public SectionId getId() {
        return SectionId.book1(523);
    }

    @Override
    public SectionNode getBody(PlayerDto current) {
        return SectionNode.root()
                .text("")
                .activeIf(current.hasCodeword("Assassin"), a -> a
                        .text("If you have the codeword Assassin, ")
                        .clickableTurnTo(27)
                        .text(" immediately. "))
                .activeElseIf(current.hasTitleOrHonor("Protector of Sokara"), a -> a
                        .text("If not, if you have the title Protector of Sokara, you are sent in to " +
                                "see the provost marshal immediately – ")
                        .clickableTurnTo(95)
                        .text(".\n"))
                .activeElse(a -> a
                        .text("Otherwise, you have to wait several hours to be seen by one of " +
                                "the provost marshal’s aides, a certain Captain Royzer.\n")
                        .activeIf(current.hasCodeword("Artery"), b -> b
                                .text("If you have the codeword Artery, Royzer sends you in to see " +
                                        "Marloes Marlock immediately – ")
                                .clickableTurnTo(456)
                                .text(".\n"))
                        .activeElseIf(List.of(WARRIOR, ROGUE, TROUBADOUR).contains(current.profession()), b -> b
                                .text("If you are a Warrior, Rogue or Troubadour, Royzer will let you in for the ")
                                .clickable(c -> c.addShards(-5).withCurrentSectionId(191),
                                        c -> c.text("modest bribe of 5 Shards – turn to 191"))
                                .text(".\n"))
                        .activeElse(b -> b
                                .text("If you are a Wayfarer, Priest or Mage, you will have to " +
                                        "convince the captain of your loyalty to Sokara first. ")
                                .clickableDifficultyRoll(current, AbilityEnum.CHARISMA, 9)
                                .text(". ")
                                .activeIfDifficultySuccess(current, 9, c -> c
                                        .text("If you are successful, you can then bribe him – ")
                                        .clickable(d -> d.addShards(-5).withCurrentSectionId(191), d -> d
                                                .text("pay 5 Shards and turn to 191"))
                                        .text(".\n")))
                        .text("If you fail your CHARISMA roll or do not wish to pay the " +
                                "bribe, Captain Royzer dismisses you rudely. ")
                        .clickableTurnTo(10)
                        .text("."));
    }
}
