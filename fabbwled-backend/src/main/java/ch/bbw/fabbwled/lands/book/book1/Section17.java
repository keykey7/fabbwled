package ch.bbw.fabbwled.lands.book.book1;


import ch.bbw.fabbwled.lands.book.SectionHandler;
import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.book.SectionNode;
import ch.bbw.fabbwled.lands.character.AbilityEnum;
import ch.bbw.fabbwled.lands.character.PlayerDto;
import ch.bbw.fabbwled.lands.service.PlayerSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class Section17 implements SectionHandler {

    @Override
    public SectionId getId() {
        return new SectionId(1, 17);
    }



    @Override
    public SectionNode getBody(PlayerDto current) {
        return SectionNode.root().text("""
                        The horse and you hit the wall. There is a bright flash, and you
                        find you have passed straight through into the hill – it must be a
                        faery mound!
                        The horse you are riding abruptly changes shape in a puff of
                        smoke. You find yourself on the back of a little, knobbly limbed, white-faced goblin, who promptly collapses under your
                        weight.
                        You are in a cavern, lit by mouse-sized faery folk, who flit
                        about in the air blazing like fireflies. The other horses have also
                        turned into goblins, elves and faeries of all shapes and sizes.
                        ‘What have we here?,’ whispers a pale, dark-eyed elf woman,
                        dressed in silvery cobwebs and wearing a gold crown.
                        ‘An overweight mortal sitting on poor old Gobrash, your
                        majesty!’ groans the goblin you are sitting on.
                        You realize you are in great danger here – there’s no telling
                        what the faery folk will do to you. The queen signals to her people and they close in around you ominously.""")
                .clickableDifficultyRollWithOptions(current, AbilityEnum.SANCTITY, 9,
                        success -> success.clickableTurnTo(626),
                        failure -> failure.clickableTurnTo(268));
    }
}
