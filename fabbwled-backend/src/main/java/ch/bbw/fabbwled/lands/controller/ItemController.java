package ch.bbw.fabbwled.lands.controller;

import ch.bbw.fabbwled.lands.service.PlayerSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/item")
@CrossOrigin("http://localhost:5173")
public class ItemController {
    private final PlayerSession playerSession;

    @PostMapping("/{id}/activate")
    public ResponseEntity activateItem(@PathVariable Long id) {

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{listindex}")
    public ResponseEntity deleteItem(@PathVariable int listindex) {
        playerSession.getPlayer().possessions().remove(listindex);
        return ResponseEntity.ok().build();
    }


}
