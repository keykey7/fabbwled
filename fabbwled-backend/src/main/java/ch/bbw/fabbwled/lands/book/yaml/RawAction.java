package ch.bbw.fabbwled.lands.book.yaml;

import ch.bbw.fabbwled.lands.character.FightingType;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record RawAction(String text, @JsonProperty("if") RawCondition if_,

                        List<RawAction> then,

                        @JsonProperty("else") List<RawAction> else_,

                        List<RawChoice> choice,
                        String acquireKeyword,
                        Integer turnTo,
                        FightingType fight,
                        Boolean checkTickBox,String acquirePossession, Integer spendShards) {}



