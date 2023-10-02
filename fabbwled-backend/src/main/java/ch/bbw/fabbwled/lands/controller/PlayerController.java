package ch.bbw.fabbwled.lands.controller;

import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.service.PlayerSession;
import ch.bbw.fabbwled.lands.character.Character;
import ch.bbw.fabbwled.lands.service.CharacterService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerSession playerSession;
    private final CharacterService characterService;

    @GetMapping("/api/player")
    public PlayerSession.PlayerDto whoami() {
        return playerSession.getPlayer();
    }

    @GetMapping("/api/player/characters/{bookId}/all")
    public List<Character.CharacterCreateDto> getAllCharacters(@PathVariable int bookId) {
        return characterService.getAllCharacters(bookId);
    }

    @PostMapping("/api/player/setCharacter")
    public ResponseEntity<Character.CharacterCreateDto> setCharacter(@RequestBody Character.CharacterCreateDto createdPlayer) {
        playerSession.setInitialCreation(true);
        playerSession.update(player -> {
            player = createdPlayer.player().withCurrentSection(SectionId.book1(1)).withTitlesAndHonours(Collections.emptySet()); 
        });
        playerSession.setInitialCreation(false);
        return ResponseEntity.ok(new Character.CharacterCreateDto(playerSession.getPlayer(),createdPlayer.description()));
    } // Should only be used when the player is created

    @PostMapping("/api/player/update")
    public ResponseEntity<PlayerSession.PlayerDto> updatePlayer(@RequestBody PlayerSession.PlayerDto createdPlayer) {
        playerSession.update(player -> {
            player = createdPlayer;
        });
        return ResponseEntity.ok(playerSession.getPlayer());
    }




}
