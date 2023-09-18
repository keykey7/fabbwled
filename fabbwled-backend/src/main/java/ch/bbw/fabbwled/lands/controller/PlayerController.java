package ch.bbw.fabbwled.lands.controller;

import ch.bbw.fabbwled.lands.service.PlayerSession;
import ch.bbw.fabbwled.lands.character.Character.CharacterDto;
import ch.bbw.fabbwled.lands.character.Character;
import ch.bbw.fabbwled.lands.service.CharacterService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerSession playerSession;
    private final CharacterService characterService;

    /**
     * @return the current player session
     */
    @GetMapping("/api/player")
    public PlayerSession.PlayerDto whoami() {
        return playerSession.getPlayer();
    }

    @GetMapping("/api/player/characters/{bookId}/all")
    public List<CharacterDto> getAllCharacters(@PathVariable int bookId) {
        return characterService.getAllCharacters(bookId);
    }

    @GetMapping("/api/player/getCharacter")
    public Character.CharacterDto getCharacter() {
        return playerSession.getPlayer().character();
    }

    @PostMapping("/api/player/setCharacter")
    public ResponseEntity setCharacter(@RequestBody Character.CharacterDto character) {
        String validationResult = characterService.validateCharacter(character);
        if(!validationResult.isEmpty()) {
            return ResponseEntity.badRequest().body(validationResult);
        }
        playerSession.update(player -> {
            player = player.withCharacter(character);
            return player;
        });
        return ResponseEntity.ok(playerSession.getPlayer());
    }




}
