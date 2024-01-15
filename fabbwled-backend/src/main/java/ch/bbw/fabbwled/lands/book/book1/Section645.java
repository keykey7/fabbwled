package ch.bbw.fabbwled.lands.book.book1;

import ch.bbw.fabbwled.lands.book.SectionHandler;
import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.book.SectionNode;
import ch.bbw.fabbwled.lands.character.PlayerDto;
import org.springframework.stereotype.Component;

@Component
public class Section645 implements SectionHandler {
    @Override
    public SectionId getId() {
        return SectionId.book1(645);
    }

    @Override
    public SectionNode getBody(PlayerDto current) {
        return SectionNode.root()
                .text("""
                        You are brought before the druids’ leader, the Oak Druid, a
                        bearded fellow with earth and leaves all tangled up in his hair.
                        He asks you to perform a service for them.
                        ‘Take this oak staff to the Willow Druid in the forest of
                        Larun. The sacred grove where he lives will be hard to find, but
                        I’m sure you can do it. The Willow Druid will give you
                        something to bring back to me. When you return with it, I will
                        make you a better Wayfarer.’
                        """)
                .clickable(playerDto -> playerDto.addPossession("Oak Staff"), a -> a.text("Note the oak staff on your Adventure Sheet."))
                .clickableTurnTo(678);
    }
}
