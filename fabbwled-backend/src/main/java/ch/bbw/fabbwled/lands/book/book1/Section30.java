package ch.bbw.fabbwled.lands.book.book1;

import ch.bbw.fabbwled.lands.book.SectionHandler;

import ch.bbw.fabbwled.lands.book.SectionId;

import ch.bbw.fabbwled.lands.book.SectionNode;

import ch.bbw.fabbwled.lands.character.PlayerDto;

import org.springframework.stereotype.Component;

@Component

public class Section30 implements SectionHandler {
    @Override

    public SectionId getId() {

        return SectionId.book1(30);

    }

    @Override

    public SectionNode getBody(PlayerDto current) {

        return SectionNode.root()

                .text("The market is large and busy. At the corner of Brimstone Plaza,\n" +
                        "gigantic braziers burn sweet-smelling incense in an attempt to\n" +
                        "overpower the rotten-egg smell that permeates the whole city.\n" +
                        "There are many stalls and goods to chose from. You may buy\n" +
                        "any of the items listed, as long as you have the money and the\n" +
                        "space to carry it. You may also sell any items you own that are\n" +
                        "listed below, for the price stated – if you do, don’t forget to\n" +
                        "cross them off your Adventure Sheet.\n" +
                        "Items with no purchase price are not available locally.\n" +
                        "Armour To buy To sell")

                .item("Leather (Defence +1)")
                .activeIf(current.shards() >= 50, x -> x.clickable(p -> p.addShards(-50)
                        .addPossession("Leather (Defence +1)"), a -> a.text("50 Shards")))
                .activeIf(current.possessions()
                        .contains("Leather (Defence +1)"), x -> x.clickable(p -> p.addShards(45)
                        .removePossession("Leather (Defence +1)"), a -> a.text("45 Shards")));
    }

}
