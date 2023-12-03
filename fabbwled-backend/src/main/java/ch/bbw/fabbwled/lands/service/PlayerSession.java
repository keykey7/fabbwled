package ch.bbw.fabbwled.lands.service;

import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.character.PlayerDto;
import ch.bbw.fabbwled.lands.service.effects.OnAttributeRefresh;
import ch.bbw.fabbwled.lands.service.effects.OnPlayerChange;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;
import java.util.Set;
import java.util.function.UnaryOperator;

/**
 * A simple session (cookie) based player state.
 */
@Component
@SessionScope
@RequiredArgsConstructor
public class PlayerSession {

    private final List<OnPlayerChange> allPlayerChangeEffects;

    private final Set<OnAttributeRefresh> allAttributeRefreshEffects;

    private PlayerDto player;

    public void forceSetNewPlayer(PlayerDto newPlayer) {
        player = newPlayer;
    }

    public PlayerDto getPlayer() {
        var basePlayer = player;
        for (var effect : allAttributeRefreshEffects) {
            basePlayer = effect.applyEffect(player);
        }
        return basePlayer; // the raw "player" is not exposed... only the one which has all effects (like DEFENCE) applied
    }

    public void update(UnaryOperator<PlayerDto> modifier) {
        var newPlayer = modifier.apply(player); // updating the raw player (not getPlayer)
        for (var effect : allPlayerChangeEffects) { // then run all automated rules on it
            newPlayer = effect.applyEffect(player, newPlayer);
        }
        player = newPlayer; // all rules passed, persist it
    }

    @PostConstruct
    public void setAnyPlayer() {
        // just convenience for now to always have a player...
        // can be dropped once we always start with the character-creation step
        player = PlayerDto.empty()
                .withCurrentSection(SectionId.book1(44));
    }
}
