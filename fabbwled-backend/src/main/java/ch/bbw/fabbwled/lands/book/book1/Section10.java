package ch.bbw.fabbwled.lands.book.book1;


import ch.bbw.fabbwled.lands.book.SectionHandler;
import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.book.SectionNode;
import ch.bbw.fabbwled.lands.character.PlayerDto;
import ch.bbw.fabbwled.lands.service.PlayerSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class Section10 implements SectionHandler {

    @Override
    public SectionId getId() {
        return new SectionId(1, 10);
    }


    @Override
    public SectionNode getBody(PlayerDto current) {
        return SectionNode.root().text("""
                        If you have just arrived in Yellowport, tick the first empty box above (use a pencil).
                        Yellowport is the second largest city in Sokara.
                        It is mainly a trading town, and is known for its exotic goods from distant Ankon-Konu, way to the south.
                        The Stinking River brings rich deposits of sulphur from the Lake of the Sea Dragon down to the town, where it is extracted and stored in the large waterfront warehouses run by the merchants’ guild.
                        From here, the mineral is exported all over Harkuna.
                        Unfortunately, all that sulphur has its drawbacks.
                        The stink is abominable, and much of the city has a yellowish hue.
                        The river is so full of sulphur that it is virtually useless as a source of food or of drinking water.
                        However, the demand for sulphur, especially from the sorcerous guilds, is great. Politically, much has changed in the past few years.
                        The old and corrupt king of Sokara, Corin VII, has been deposed and executed in a military coup. General Grieve Marlock and the army now control Sokara.
                        The old Council of Yellowport has been ‘indefinitely dissolved’ and a provost marshal, Marloes Marlock, the general’s brother, appointed as military governor of the town.

                        Owning a house gives you a place to rest, and to store equipment.
                        If you buy one, tick the box by the town house option and cross off 200 Shards from your Adventure Sheet.
                        To leave Yellowport by sea, buy or sell ships and cargo, go to the harbourmaster.
                              """)

                .activeIf(current.hasCodeword("Assasin"), x -> x.clickableTurnTo(50))
                .activeIf(current.volatileSectionStore()==null, x -> x.clickable(p -> p.addTick().withVolatileSectionStore(true), y -> y.text("tick the first empty box above")))
                .activeIf(current.getTicks() == this.getMaxTicks(), x -> x.clickableTurnTo(273))
                .activeIf(current.shards() >= 200, t -> t.choice(x -> x.text("Buy townhouse for 200 shards"),  x ->  x.clickable( y -> y.addShards(-200).addPersistentSectionStorage(true), z -> z.text("You can buy a town house in Yellowport for 200 Shards."))))
                .activeIf(current.hasCodeword("Artefact") && current.possessions().contains("Book of the Seven Sages"), x -> x.clickableTurnTo(40))
                .activeIf(current.persistentSectionStore().get(current.currentSection())!=null, x -> x.clickableTurnTo(300))
                .choice(x -> x.text("Seek an audience with the provost marshal"), x -> x.clickableTurnTo(523))
                .choice(x -> x.text("Visit the market"), x -> x.clickableTurnTo(30))
                .choice(x -> x.text("Visit the harbour master"), x -> x.clickableTurnTo(555))
                .choice(x -> x.text("Go the merchants' guild"), x -> x.clickableTurnTo(405))
                .choice(x -> x.text("Explore the city by day"), x -> x.clickableTurnTo(302))
                .choice(x -> x.text("Explore the city by night"), x -> x.clickableTurnTo(442))
                .choice(x -> x.text("Visit the Gold Dust Tavern"), x -> x.clickableTurnTo(506))
                .choice(x -> x.text("Visit the temple of Maka"), x -> x.clickableTurnTo(141))
                .choice(x -> x.text("Visit the temple of Elnir"), x -> x.clickableTurnTo(316))
                .choice(x -> x.text("Visit the temple of Alvir and Valmir"), x -> x.clickableTurnTo(220))
                .choice(x -> x.text("Visit the temple of Tyrnai"), x -> x.clickableTurnTo(526))
                .choice(x -> x.text("Travel north-east towards Venefax"), x -> x.clickableTurnTo(621))
                .choice(x -> x.text("Head north-west to Trefoille"), x -> x.clickableTurnTo(233))
                .choice(x -> x.text("Follow the Stinking River north"), x -> x.clickableTurnTo(82))
                .choice(x -> x.text("Strike out north-west, across country"), x -> x.clickableTurnTo(558));

    }


    @Override
    public int getMaxTicks() {
        return 4;
    }


}

