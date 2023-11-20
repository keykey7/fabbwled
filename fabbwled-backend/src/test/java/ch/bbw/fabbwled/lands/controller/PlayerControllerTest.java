package ch.bbw.fabbwled.lands.controller;

import ch.bbw.fabbwled.lands.FabledTestBase;
import ch.bbw.fabbwled.lands.character.Character;
import ch.bbw.fabbwled.lands.service.PlayerSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.reactive.server.WebTestClient;

@AutoConfigureMockMvc
class PlayerControllerTest extends FabledTestBase {

    @Autowired
    WebTestClient client;

    @Test
    void whoami() {
        client.get()
                .uri("/api/player")
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(PlayerSession.PlayerDto.class);
    }

    @Test
    void getAndSetCharacters() {
        var any = client.get()
                .uri("/api/player/{bookId}/all", 1)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBodyList(Character.CharacterCreateDto.class)
                .value(list -> assertThat(list).isNotEmpty())
                .returnResult()
                .getResponseBody()
                .get(0);
        client.post()
                .uri("/api/player")
                .bodyValue(any)
                .exchange()
                .expectStatus()
                .is2xxSuccessful();
    }
}
