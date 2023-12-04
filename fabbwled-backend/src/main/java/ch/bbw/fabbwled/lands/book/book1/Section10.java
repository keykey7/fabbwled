package ch.bbw.fabbwled.lands.book.book1;




import ch.bbw.fabbwled.lands.book.SectionHandler;
import ch.bbw.fabbwled.lands.book.SectionId;
import ch.bbw.fabbwled.lands.book.SectionNode;
import ch.bbw.fabbwled.lands.exception.FabledBusinessException;
import ch.bbw.fabbwled.lands.service.PlayerSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Section10 implements SectionHandler {
    private final PlayerSession playerSession;

    @Override
    public SectionId getId() {
        return new SectionId(1, 10);
    }



    @Override
    public SectionNode getBody() {
        return SectionNode.root().text("""
                If  you  have  just  arrived  in  Yellowport,  tick  the  first  empty box above (use a pencil).
                The boxes are a record of the number of times you have visited the city.
                If this is your fourth visit, turn to 273.
                If you have visited the city fewer than or more than four times, read on.
                Yellowport is the second largest city in Sokara.
                It is mainly a trading  town,  and  is  known  for  its  exotic  goods  from  distant Ankon-Konu, way to the south.
                The Stinking River brings rich deposits of sulphur from the Lake of the Sea Dragon down to the town, where it is extracted and  stored  in  the  large  waterfront  warehouses  run  by  the merchants’  guild.
                From  here,  the  mineral  is  exported  all  over Harkuna.
                Unfortunately,  all  that  sulphur  has  its  drawbacks.
                The stink  is  abominable,  and  much  of  the  city  has  a  yellowish  hue.
                The river is so full of sulphur that it is virtually useless as a source of food or of drinking water.
                However, the demand for sulphur,especially from the sorcerous guilds, is great.Politically, much has changed in the past few years.
                The oldand  corrupt  king  of  Sokara,  Corin  VII,  has  been  deposed  and executed  in  a  military  coup.  General  Grieve  Marlock  and  thearmy  now  control  Sokara.
                The  old  Council  of  Yellowport  has been  ‘indefinitely  dissolved’  and  a  provost  marshal,  Marloes Marlock, the general’s brother, appointed as military governor of the town.
                You  can  buy  a  town  house  in  Yellowport  for  200  Shards.
                Owning  a  house  gives  you  a  place  to  rest,  and  to  store equipment.
                If  you  buy  one,  tick  the  box  by  the  town  houseoption and cross off 200 Shards from your Adventure Sheet.
                To leave Yellowport by sea, buy or sell ships and cargo, go to the harbourmaster.
                If  you  have  the  codeword  Artefact  and  the  Book  of  the Seven Sages, you can turn to 40.
                Choose from the following options:
                Visit your town house ! (if box ticked) turn to 300
                Visit the Gold Dust Tavern turn to 506
                Visit the temple of Maka turn to 141
                Visit the temple of Elnir turn to 316
                Visit the temple of Alvir and Valmir turn to 220
                Visit the temple of Tyrnait urn to 526
                Travel north-east towards Venefax turn to 621
                Head north-west to Trefoille turn to 233
                Follow the Stinking River north  turn to 82
                Strike out north-west, across country turn to 558
                      """).choice(x -> x.text("Seek an audience with the provost marshal"),x -> x.clickableTurnTo(523))
                .choice(x -> x.text("Visit the market"),x -> x.clickableTurnTo(30))
                .choice(x -> x.text("Visit the harbour master"), x-> x.clickableTurnTo(555))
                .choice(x -> x.text("Go the merchants' guild"), x -> x.clickableTurnTo(405))
                .choice(x -> x.text("Explore the city by day"), x -> x.clickableTurnTo(302))
                .choice(x -> x.text("Explore the city by night"), x -> x.clickableTurnTo(442))
                .activeIf(playerSession.getPlayer().customSectionStorage().customHouse());
    }


    @Override
    public int getMaxTicks() {
        return 4;
    }

    @Override
    public void onClick(int id) {
        if(playerSession.getPlayer().codeWords().contains("Assasin")) {
            playerSession.update(x -> x.withCurrentSection(SectionId.book1(50)));
            return;
        }
        playerSession.update(x -> x.withCustomSectionStorage(SectionId.book1(10),x.customSectionStorage().complicatedTick() + 1));
        if(playerSession.getPlayer().customSectionStorage(SectionId.book1(10)).complicatedTick() == 4) {
            playerSession.update(x -> x.withCurrentSection(SectionId.book1(273)));
            return;
        }
        if(!playerSession.getPlayer().customSectionStorage().customHouse()) {
            if(playerSession.getPlayer().shards() >= 200) {
                playerSession.update(x -> x.withCustomSectionStorage(SectionId.book1(10)).customHouse() = true);
            }
        }
        if(playerSession.getPlayer().codeWords().contains("Artefact") && playerSession.getPlayer().possessions().contains("Book of the Seven Sages")) {
            playerSession.update(x -> x.withCurrentSection(SectionId.book1(40)));
        }
        var nextSection = switch (ClickOptions.values()[id]) {
            case NEXT_SECTION -> {

            }
        };
        playerSession.update(x -> x.withCurrentSection(nextSection));
    }

    enum ClickOptions {
        NEXT_SECTION
    }
}

