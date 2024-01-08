package ch.bbw.fabbwled.lands.service;

import java.util.List;

import org.springframework.stereotype.Service;
import ch.bbw.fabbwled.lands.marketplace.MarketPlace;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MarketPlaceService {

    public List<MarketPlace> displayMarketplace(List<MarketPlace> marketPlaceValues) {
        return marketPlaceValues;
    }

}
