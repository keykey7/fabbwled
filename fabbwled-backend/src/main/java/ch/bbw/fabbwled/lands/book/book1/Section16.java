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
public class Section16 implements SectionHandler {

    @Override
    public SectionId getId() {
        return new SectionId(1, 16);
    }


    @Override
    public int getMaxTicks() {
        return 2;
    }

    @Override
    public SectionNode getBody(PlayerDto current) {
        return SectionNode.root().text("""
                        You remain quiet as a mouse, behind a pile of coins. After a
                        long wait, the sea dragon slithers into the water, and swims out
                        on some errand. You have some time to loot the hoard.
                        After you have taken the third treasure, you hear the sea dragon
                        returning. Quickly you climb up through the hole in the roof
                        on to an island in the middle of the lake. From there you
                        manage to get a lift on a passing boat, and make it safely to
                        Cadmium village.""")
                .activeIf(current.getTicks() == this.getMaxTicks(), x -> x.clickableTurnTo(251))
                .activeIf(current.volatileSectionStore() == null, x -> x.clickable(p -> p.addTick().withVolatileSectionStore(true), y -> y.text("tick the  empty box above")))
                .activeIf(current.hasCodeword("Avenge"), i -> i.clickableTurnTo(648))
                .activeIf(current.volatileSectionStore() != null && current.volatileSectionStore().equals(3), c -> c.clickableTurnTo(135))
                .activeElse(h -> {
                    h.clickable(p -> p.withVolatileSectionStore(0), u -> u.text("Pick item"));
                    h
                            .choice(c -> c.text("Enchanted sword (COMBAT +3)"), x -> x.clickable(p -> p.addPossession("Enchanted sword (COMBAT +3)").withVolatileSectionStore((int) p.volatileSectionStore() + 1), n -> n.text("Get possession")))
                            .choice(c -> c.text("Plate armour (Defence +5)"), e -> e.clickable(p -> p.addPossession("Plate armour (Defence +5)").withVolatileSectionStore((int) p.volatileSectionStore() + 1), x -> x.text("Get possession")))
                            .choice(c -> c.text("500 Shards"), g -> g.clickable(p -> p.addShards(500).withVolatileSectionStore((int) p.volatileSectionStore() + 1), x -> x.text("Get shards")))
                            .choice(c -> c.text("Magic mandolin (CHARISMA +2)"), y -> y.clickable(p -> p.addPossession("Magic mandolin (CHARISMA +2)").withVolatileSectionStore((int) p.volatileSectionStore() + 1), x -> x.text("Get possession")))
                            .choice(c -> c.text("Gold compass (SCOUTING +2)"), r -> r.clickable(p -> p.addPossession("Gold compass (SCOUTING +2)").withVolatileSectionStore((int) p.volatileSectionStore() + 1), x -> x.text("Get possession")))
                            .choice(c -> c.text("Magic lockpicks (THIEVERY +2)"), m -> m.clickable(p -> p.addPossession("Magic lockpicks (THIEVERY +2)").withVolatileSectionStore((int) p.volatileSectionStore() + 1), x -> x.text("Get possession")))
                            .choice(c -> c.text("Silver holy symbol (SANCTITY +2)"), b -> b.clickable(p -> p.addPossession("Silver holy symbol (SANCTITY +2)").withVolatileSectionStore((int) p.volatileSectionStore() + 1), x -> x.text("Get possession")));
                });


    }
}
