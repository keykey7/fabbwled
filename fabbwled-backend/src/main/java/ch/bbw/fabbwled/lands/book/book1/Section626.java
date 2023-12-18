package ch.bbw.fabbwled.lands.book.book1;

import ch.bbw.fabbwled.lands.book.SectionHandler;
import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.book.SectionNode;
import ch.bbw.fabbwled.lands.character.PlayerDto;

public class Section626 implements SectionHandler {
    @Override
    public SectionId getId() {
        return SectionId.book1(626);
    }

    @Override
    public SectionNode getBody(PlayerDto current) {
        return SectionNode.root()
                .text("""
                        You recite a devotional prayer to the gods, calling on your faith
                        to aid you. The goblin folk are repelled by your piety, and
                        purity of spirit. Even Gobrash finds the strength to get out from
                        under you, and run off, such is your effect on them.
                        ‘Eeaurgh!’ snarls the queen, recoiling, ‘Please, your godliness
                        is harmful to us. Here, take this and leave!’
                        A jug of faery mead and an enchanted spear (COMBAT
                        +2) are pushed towards you.
                        The wall behind you shimmers and disappears – you step
                        through, back into the cold, night air of the Curstmoor. You
                        leave the faery mound far behind, and camp for the night. The
                        next day, you resume your travels.
                        """)
                .clickable(a -> a.addPossession("A jug of faery mead"), b -> b.text("Jug of faery mead"))
                .clickable(a -> {
                    a.withStats(stats -> stats.withCombatAdd(2));
                    a.addPossession("Enchanted spear");
                    return a;
                }, b -> b.text("Enchanted spear"))
                .choice(a -> a.text("Go north across country"), b -> b.clickableTurnTo(560))
                .choice(a -> a.text("Head east to the road"), b -> b.clickableTurnTo(558))
                .choice(a -> a.text("Go to Trefoille"), b -> b.clickableTurnTo(250))
                .choice(a -> a.text("Go to Marlock City"), b -> b.clickableTurnTo(100))
                .choice(a -> a.text("Head west towards the River Grimm"), b -> b.clickableTurnTo(99));
    }
}
