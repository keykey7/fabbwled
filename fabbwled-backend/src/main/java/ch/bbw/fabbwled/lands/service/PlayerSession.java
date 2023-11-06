package ch.bbw.fabbwled.lands.service;

import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.character.Character;
import ch.bbw.fabbwled.lands.character.ProfessionEnum;
import ch.bbw.fabbwled.lands.character.RankEnum;
import ch.bbw.fabbwled.lands.character.Resurrection;
import ch.bbw.fabbwled.lands.exception.FabledBusinessException;
import lombok.Getter;
import lombok.Setter;
import lombok.With;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.UnaryOperator;

/**
 * A simple session (cookie) based player state.
 */
@Getter
@Component
@SessionScope
public class PlayerSession {

    private final CharacterService characterService = new CharacterService();


    private final Character.CharacterCreateDto initialCharacter = characterService.getAllCharacters(1).get(0);
    private PlayerDto player = initialCharacter.player();
    @Setter
    private boolean initialCreation;

    public void validateInitialCreation(PlayerDto playerDto) {
        Character.BaseStatsDto stats = playerDto.baseStats();
        int charisma = stats.charisma();
        int combat = stats.combat();
        int magic = stats.magic();
        int sanctity = stats.sanctity();
        int scouting = stats.scouting();
        int thievery = stats.thievery();
        if (charisma < 1 || charisma > 6
                || combat < 1 || combat > 6
                || magic < 1 || magic > 6
                || sanctity < 1 || sanctity > 6
                || scouting < 1 || scouting > 6
                || thievery < 1 || thievery > 6) {
            throw new FabledBusinessException("Stats can only range between 1 and 6");
        }
        if (!playerDto.rank().equals(RankEnum.OUTCAST)) {
            throw new FabledBusinessException("Character possession size not allowed over 12");
        }
    }

    public void validatePlayer(PlayerDto playerDto) {
        try {
            if (this.initialCreation) {
                validateInitialCreation(playerDto);
            }
            if (playerDto.shards().shardCount() > player.shards().shardCount()){
                int ShardsAmount = playerDto.shards().shardCount() - player.shards().shardCount();
                playerDto.shards().addShards(ShardsAmount);
            }
            if (playerDto.shards().shardCount() < player.shards().shardCount()){
                int ShardsAmount = player.shards().shardCount() - playerDto.shards().shardCount();
                playerDto.shards().subtractShards(ShardsAmount);
            }
            if (playerDto.possessions().size() > 12) {
                throw new FabledBusinessException("Character possession size not allowed over 12");
            }
        } catch (FabledBusinessException e) {
            throw new FabledBusinessException(e);
        }

    }

    public void update(UnaryOperator<PlayerDto> modifier) {
        var temporaryPlayer = modifier.apply(player);
        validatePlayer(temporaryPlayer);
        player = modifier.apply(player);
    }

    @With
    public record PlayerDto(String name,
                            SectionId currentSection,
                            Set<String> titlesAndHonours,
                            RankEnum rank,
                            ProfessionEnum profession,
                            int stamina,
                            Character.BaseStatsDto baseStats,
                            List<String> possessions, 
                            ShardSystem shards,
                            Map<SectionId, Integer> tickBoxes,
                            Set<String> codeWords,
                            Resurrection resurrectionArrangement
                            ) {


        public int getDefence() {
            return this.rank().getRankNumber() + this.baseStats().combat();
        }
    }

}
