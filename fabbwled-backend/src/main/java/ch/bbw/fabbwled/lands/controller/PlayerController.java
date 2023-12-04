package ch.bbw.fabbwled.lands.controller;

import ch.bbw.fabbwled.lands.character.Character;
import ch.bbw.fabbwled.lands.service.CharacterService;
import ch.bbw.fabbwled.lands.character.PlayerDto;
import ch.bbw.fabbwled.lands.service.PlayerSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/player")
@CrossOrigin("http://localhost:5173")
public class PlayerController {

    private final PlayerSession playerSession;
    private final CharacterService characterService;

    @GetMapping()
    public PlayerDto whoami() {
        return playerSession.getPlayer();
    }

    @GetMapping("/{bookId}/all")
    public List<Character.CharacterCreateDto> getAllCharacters(@PathVariable int bookId) {
        return characterService.getAllCharacters(bookId);
    }

    @PostMapping(consumes = "application/json")
    public void setCharacter(@RequestBody PlayerDto createdPlayer) {
        characterService.validateInitialCreation(createdPlayer);
        playerSession.forceSetNewPlayer(createdPlayer);
    } // Should only be used when the player is created

    @PutMapping()
    public ResponseEntity<PlayerDto> updatePlayer(@RequestBody PlayerDto createdPlayer) {
        // this endpoint is used for hacking and disabled in strict mode
        playerSession.update(player -> {
            player = createdPlayer;
            return player;
        });
        return ResponseEntity.ok(playerSession.getPlayer());
    }




}
