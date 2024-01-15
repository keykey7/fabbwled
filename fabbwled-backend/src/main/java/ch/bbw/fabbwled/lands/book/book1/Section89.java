package ch.bbw.fabbwled.lands.book.book1;

import ch.bbw.fabbwled.lands.book.SectionHandler;
import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.book.SectionNode;
import ch.bbw.fabbwled.lands.character.AbilityEnum;
import ch.bbw.fabbwled.lands.character.PlayerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Section89 implements SectionHandler {
    @Override
    public SectionId getId() {
        return new SectionId(1, 89);
    }

    @Override
    public SectionNode getBody(PlayerDto current) {
        return SectionNode.root().text("""
                        The undead pirate reaches for you, but it is too slow. Then it
                        speaks, its voice seeming to sound inside your head.
                        ‘Damn you, and your kind, thieving sea centaur! I curse you
                        forever – may you never swim again in the seas you love. You
                        will walk for the rest of your life as a human, doomed to wander
                        the surface world, unable to find the peace of coral caves!’
                        Quickly, you swim away, unable to believe your luck! What
                        a convenient curse. You find yourself already changing back to a
                        human, and you make it to your ship, coughing and spluttering.
                        Your crew haul you aboard. The gems are worth 300 Shards –
                        add that much to your money. You sail on, lucky to be still
                        human.""")
                .clickable(d -> d.addShards(300).withCurrentSectionId(507), d -> d
                        .text("Turn to 507"));
    }
}
