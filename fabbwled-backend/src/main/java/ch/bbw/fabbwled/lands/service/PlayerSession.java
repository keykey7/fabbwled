package ch.bbw.fabbwled.lands.service;

import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.character.Character;
import ch.bbw.fabbwled.lands.character.Character.CharacterDto;
import ch.bbw.fabbwled.lands.character.ProfessionEnum;
import ch.bbw.fabbwled.lands.character.RankEnum;
import lombok.Getter;
import lombok.With;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.UnaryOperator;

/**
 * A simple session (cookie) based player state.
 */
@Getter
@Component
@SessionScope
public class PlayerSession {

    private PlayerDto player = new PlayerDto("Johanna Doe", SectionId.book1(15), Collections.emptySet(), new Character.CharacterDto("Liana The Swift", RankEnum.OUTCAST, ProfessionEnum.WAYFARER, 9,
            new Character.StatsDto(2, 5, 2, 3, 6, 4), List.of("spear", "leather jerkin (Defence +1)", "map"),
            """
                    Liana prefers to make her home in mountain grottos
                    and woodland groves rather than in the squalid streets
                    of cities. She has the agility of a gazelle, the cunning of
                    a fox and the ferocity of an eagle. She has heard of a 
                    City of Trees, deep within the forest of the Isle of 
                    "Druids."""
    ));

    public void update(UnaryOperator<PlayerDto> modifier) {
        player = modifier.apply(player);
    }

    @With
    public record PlayerDto(String name,
                            SectionId currentSection,
                            Set<String> titlesAndHonours,
                            CharacterDto character) {


        public int getDefence() {
            return this.character().rank().ordinal() + 1 + this.character().baseStats().combat(); // TODO add modifier for adding defence and combat (item perks) https://github.com/keykey7/fabbwled/issues/193
        }
    }

}
