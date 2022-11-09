package com.TravelAgency;

import com.TravelAgency.offer.model.database.AirportDetailsDTO;
import com.TravelAgency.offer.model.database.BoardBasisDetailsDTO;
import com.TravelAgency.offer.model.database.CountryDTO;
import com.TravelAgency.offer.model.database.RegionDTO;
import com.TravelAgency.offer.repository.*;
import com.amadeus.exceptions.ResponseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(AirportDetailsRepository airportDetailsRepository, BoardBasisDetailsRepository boardBasisDetailsRepository,
                                   CountryRepository countryRepository, RegionRepository regionRepository, RoomRepository roomDetails) throws ResponseException {


        return args -> {

            if (boardBasisDetailsRepository.findAll().size() < 1) {
                log.info("Preloading " + boardBasisDetailsRepository.save(new BoardBasisDetailsDTO("All inclusive")));
                log.info("Preloading " + boardBasisDetailsRepository.save(new BoardBasisDetailsDTO("According to programme")));
                log.info("Preloading " + boardBasisDetailsRepository.save(new BoardBasisDetailsDTO("Breakfast")));
                log.info("Preloading " + boardBasisDetailsRepository.save(new BoardBasisDetailsDTO("Full board")));
                log.info("Preloading " + boardBasisDetailsRepository.save(new BoardBasisDetailsDTO("Half board")));
                log.info("Preloading " + boardBasisDetailsRepository.save(new BoardBasisDetailsDTO("No board")));
            }
            if (airportDetailsRepository.findAll().size() < 1) {
                log.info("Preloading " + airportDetailsRepository.save(new AirportDetailsDTO("Augustów")));
                log.info("Preloading " + airportDetailsRepository.save(new AirportDetailsDTO("Białystok")));
                log.info("Preloading " + airportDetailsRepository.save(new AirportDetailsDTO("Bydgoszcz")));
                log.info("Preloading " + airportDetailsRepository.save(new AirportDetailsDTO("Częstochowa")));
                log.info("Preloading " + airportDetailsRepository.save(new AirportDetailsDTO("Ełk")));
                log.info("Preloading " + airportDetailsRepository.save(new AirportDetailsDTO("Gdańsk")));
                log.info("Preloading " + airportDetailsRepository.save(new AirportDetailsDTO("Gliwice")));
                log.info("Preloading " + airportDetailsRepository.save(new AirportDetailsDTO("Głubczyce")));
                log.info("Preloading " + airportDetailsRepository.save(new AirportDetailsDTO("Grajewo")));
                log.info("Preloading " + airportDetailsRepository.save(new AirportDetailsDTO("Katowice")));
                log.info("Preloading " + airportDetailsRepository.save(new AirportDetailsDTO("Kraków")));
                log.info("Preloading " + airportDetailsRepository.save(new AirportDetailsDTO("Leszno")));
                log.info("Preloading " + airportDetailsRepository.save(new AirportDetailsDTO("Łomża")));
                log.info("Preloading " + airportDetailsRepository.save(new AirportDetailsDTO("Łódź")));
                log.info("Preloading " + airportDetailsRepository.save(new AirportDetailsDTO("Opole")));
                log.info("Preloading " + airportDetailsRepository.save(new AirportDetailsDTO("Piotrków Trybunalski")));
                log.info("Preloading " + airportDetailsRepository.save(new AirportDetailsDTO("Poznań")));
                log.info("Preloading " + airportDetailsRepository.save(new AirportDetailsDTO("Rzeszów")));
                log.info("Preloading " + airportDetailsRepository.save(new AirportDetailsDTO("Suwałki")));
                log.info("Preloading " + airportDetailsRepository.save(new AirportDetailsDTO("Szczecin")));
                log.info("Preloading " + airportDetailsRepository.save(new AirportDetailsDTO("Szczuczyn")));
                log.info("Preloading " + airportDetailsRepository.save(new AirportDetailsDTO("Warsaw")));
                log.info("Preloading " + airportDetailsRepository.save(new AirportDetailsDTO("Warsaw - Modlin")));
                log.info("Preloading " + airportDetailsRepository.save(new AirportDetailsDTO("Warszawa - Radom")));
                log.info("Preloading " + airportDetailsRepository.save(new AirportDetailsDTO("Wrozław")));
                log.info("Preloading " + airportDetailsRepository.save(new AirportDetailsDTO("Zielona Góra")));
            }
            if (countryRepository.findAll().size() < 1) {
                log.info("Preloading " + countryRepository.save(new CountryDTO("Albania")));
                log.info("Preloading " + countryRepository.save(new CountryDTO("Azores")));
                log.info("Preloading " + countryRepository.save(new CountryDTO("Bali")));
                log.info("Preloading " + countryRepository.save(new CountryDTO("Bulgaria")));
                log.info("Preloading " + countryRepository.save(new CountryDTO("Cabo Verde")));
                log.info("Preloading " + countryRepository.save(new CountryDTO("Canary Islands")));
                log.info("Preloading " + countryRepository.save(new CountryDTO("Croatia")));
                log.info("Preloading " + countryRepository.save(new CountryDTO("Cuba")));
                log.info("Preloading " + countryRepository.save(new CountryDTO("Cyprus")));
                log.info("Preloading " + countryRepository.save(new CountryDTO("Dominican Republic")));
                log.info("Preloading " + countryRepository.save(new CountryDTO("Egypt")));
                log.info("Preloading " + countryRepository.save(new CountryDTO("Georgia")));
                log.info("Preloading " + countryRepository.save(new CountryDTO("Greece")));
                log.info("Preloading " + countryRepository.save(new CountryDTO("Italy")));
                log.info("Preloading " + countryRepository.save(new CountryDTO("Madagascar")));
                log.info("Preloading " + countryRepository.save(new CountryDTO("Madeira")));
                log.info("Preloading " + countryRepository.save(new CountryDTO("Maldives")));
                log.info("Preloading " + countryRepository.save(new CountryDTO("Mexico")));
                log.info("Preloading " + countryRepository.save(new CountryDTO("Oman")));
                log.info("Preloading " + countryRepository.save(new CountryDTO("Poland")));
                log.info("Preloading " + countryRepository.save(new CountryDTO("Portugal")));
                log.info("Preloading " + countryRepository.save(new CountryDTO("Spain")));
                log.info("Preloading " + countryRepository.save(new CountryDTO("Thailand")));
                log.info("Preloading " + countryRepository.save(new CountryDTO("Turkey")));
                log.info("Preloading " + countryRepository.save(new CountryDTO("United Arab Emirates")));
                log.info("Preloading " + countryRepository.save(new CountryDTO("United States of America")));
                log.info("Preloading " + countryRepository.save(new CountryDTO("Zanzibar")));
            }
            if (regionRepository.findAll().size() < 1 && countryRepository.findAll().size() > 0) {
                List<CountryDTO> countryDTOList = countryRepository.findAll();
                countryDTOList.forEach(countryDTO -> {
                    log.info("Preloading " + regionRepository.save(
                            new RegionDTO(
                                    countryDTO,
                                    "")
                    ));
                });

                log.info("Preloading " + regionRepository.save(
                        new RegionDTO(
                                countryRepository.findByCode("Albania").orElse(null),
                                "Durres")
                ));
                log.info("Preloading " + regionRepository.save(
                        new RegionDTO(
                                countryRepository.findByCode("Bulgaria").orElse(null),
                                "Golden Sands")
                ));
                log.info("Preloading " + regionRepository.save(
                        new RegionDTO(
                                countryRepository.findByCode("Bulgaria").orElse(null),
                                "Primorsko")
                ));
                log.info("Preloading " + regionRepository.save(
                        new RegionDTO(
                                countryRepository.findByCode("Bulgaria").orElse(null),
                                "Sozopol")
                ));
                log.info("Preloading " + regionRepository.save(
                        new RegionDTO(
                                countryRepository.findByCode("Bulgaria").orElse(null),
                                "Sunny Beach")
                ));
                log.info("Preloading " + regionRepository.save(
                        new RegionDTO(
                                countryRepository.findByCode("Canary Islands").orElse(null),
                                "Fuerteventura")
                ));

                log.info("Preloading " + regionRepository.save(
                        new RegionDTO(
                                countryRepository.findByCode("Canary Islands").orElse(null),
                                "La Gomera")
                ));
                log.info("Preloading " + regionRepository.save(
                        new RegionDTO(
                                countryRepository.findByCode("Canary Islands").orElse(null),
                                "Lanzarote")
                ));
                log.info("Preloading " + regionRepository.save(
                        new RegionDTO(
                                countryRepository.findByCode("Canary Islands").orElse(null),
                                "Gran Canaria")
                ));
                log.info("Preloading " + regionRepository.save(
                        new RegionDTO(
                                countryRepository.findByCode("Canary Islands").orElse(null),
                                "La Palma")
                ));
                log.info("Preloading " + regionRepository.save(
                        new RegionDTO(
                                countryRepository.findByCode("Canary Islands").orElse(null),
                                "Tenerife")
                ));
                log.info("Preloading " + regionRepository.save(
                        new RegionDTO(
                                countryRepository.findByCode("Cuba").orElse(null),
                                "Varadero")
                ));
                log.info("Preloading " + regionRepository.save(
                        new RegionDTO(
                                countryRepository.findByCode("Cyprus").orElse(null),
                                "Larnaca")
                ));
                log.info("Preloading " + regionRepository.save(
                        new RegionDTO(
                                countryRepository.findByCode("Cyprus").orElse(null),
                                "Paphos")
                ));
                log.info("Preloading " + regionRepository.save(
                        new RegionDTO(
                                countryRepository.findByCode("Dominican Republic").orElse(null),
                                "Punta Cana")
                ));
                log.info("Preloading " + regionRepository.save(
                        new RegionDTO(
                                countryRepository.findByCode("Egypt").orElse(null),
                                "Hurghada")
                ));
                log.info("Preloading " + regionRepository.save(
                        new RegionDTO(
                                countryRepository.findByCode("Egypt").orElse(null),
                                "Marsa Alam")
                ));
                log.info("Preloading " + regionRepository.save(
                        new RegionDTO(
                                countryRepository.findByCode("Egypt").orElse(null),
                                "Taba")
                ));
                log.info("Preloading " + regionRepository.save(
                        new RegionDTO(
                                countryRepository.findByCode("Greece").orElse(null),
                                "Chalkidiki")
                ));
                log.info("Preloading " + regionRepository.save(
                        new RegionDTO(
                                countryRepository.findByCode("Greece").orElse(null),
                                "Crete")
                ));
                log.info("Preloading " + regionRepository.save(
                        new RegionDTO(
                                countryRepository.findByCode("Greece").orElse(null),
                                "Kos")
                ));
                log.info("Preloading " + regionRepository.save(
                        new RegionDTO(
                                countryRepository.findByCode("Greece").orElse(null),
                                "Mykonos")
                ));
                log.info("Preloading " + regionRepository.save(
                        new RegionDTO(
                                countryRepository.findByCode("Greece").orElse(null),
                                "Samos")
                ));
                log.info("Preloading " + regionRepository.save(
                        new RegionDTO(
                                countryRepository.findByCode("Greece").orElse(null),
                                "Zakynthos")
                ));
                log.info("Preloading " + regionRepository.save(
                        new RegionDTO(
                                countryRepository.findByCode("Greece").orElse(null),
                                "Corfu")
                ));
                log.info("Preloading " + regionRepository.save(
                        new RegionDTO(
                                countryRepository.findByCode("Greece").orElse(null),
                                "Kassandra")
                ));
                log.info("Preloading " + regionRepository.save(
                        new RegionDTO(
                                countryRepository.findByCode("Greece").orElse(null),
                                "Lesbos")
                ));
                log.info("Preloading " + regionRepository.save(
                        new RegionDTO(
                                countryRepository.findByCode("Greece").orElse(null),
                                "Rhodes")
                ));
                log.info("Preloading " + regionRepository.save(
                        new RegionDTO(
                                countryRepository.findByCode("Greece").orElse(null),
                                "Thassos")
                ));
                log.info("Preloading " + regionRepository.save(
                        new RegionDTO(
                                countryRepository.findByCode("Italy").orElse(null),
                                "Calabria")
                ));
                log.info("Preloading " + regionRepository.save(
                        new RegionDTO(
                                countryRepository.findByCode("Italy").orElse(null),
                                "Ischia")
                ));
                log.info("Preloading " + regionRepository.save(
                        new RegionDTO(
                                countryRepository.findByCode("Madagascar").orElse(null),
                                "Nosy Be")
                ));
                log.info("Preloading " + regionRepository.save(
                        new RegionDTO(
                                countryRepository.findByCode("Oman").orElse(null),
                                "Muscat")
                ));
                log.info("Preloading " + regionRepository.save(
                        new RegionDTO(
                                countryRepository.findByCode("Oman").orElse(null),
                                "Salalah")
                ));
                log.info("Preloading " + regionRepository.save(
                        new RegionDTO(
                                countryRepository.findByCode("Poland").orElse(null),
                                "Gdańsk")
                ));
                log.info("Preloading " + regionRepository.save(
                        new RegionDTO(
                                countryRepository.findByCode("Poland").orElse(null),
                                "Góry")
                ));
                log.info("Preloading " + regionRepository.save(
                        new RegionDTO(
                                countryRepository.findByCode("Portugal").orElse(null),
                                "Algarve")
                ));
                log.info("Preloading " + regionRepository.save(
                        new RegionDTO(
                                countryRepository.findByCode("Portugal").orElse(null),
                                "Lisbon riviera")
                ));
                log.info("Preloading " + regionRepository.save(
                        new RegionDTO(
                                countryRepository.findByCode("Spain").orElse(null),
                                "Costa Blanca")
                ));
                log.info("Preloading " + regionRepository.save(
                        new RegionDTO(
                                countryRepository.findByCode("Spain").orElse(null),
                                "Ibiza")
                ));
                log.info("Preloading " + regionRepository.save(
                        new RegionDTO(
                                countryRepository.findByCode("Spain").orElse(null),
                                "Costa del Sol")
                ));
                log.info("Preloading " + regionRepository.save(
                        new RegionDTO(
                                countryRepository.findByCode("Spain").orElse(null),
                                "Majorca")
                ));
                log.info("Preloading " + regionRepository.save(
                        new RegionDTO(
                                countryRepository.findByCode("Thailand").orElse(null),
                                "Khao Lak")
                ));
                log.info("Preloading " + regionRepository.save(
                        new RegionDTO(
                                countryRepository.findByCode("Thailand").orElse(null),
                                "Krabi")
                ));
                log.info("Preloading " + regionRepository.save(
                        new RegionDTO(
                                countryRepository.findByCode("Thailand").orElse(null),
                                "Phuket")
                ));
                log.info("Preloading " + regionRepository.save(
                        new RegionDTO(
                                countryRepository.findByCode("Tunisia").orElse(null),
                                "Djerba")
                ));
                log.info("Preloading " + regionRepository.save(
                        new RegionDTO(
                                countryRepository.findByCode("Tunisia").orElse(null),
                                "Hammamet")
                ));
                log.info("Preloading " + regionRepository.save(
                        new RegionDTO(
                                countryRepository.findByCode("Tunisia").orElse(null),
                                "Monastir")
                ));
                log.info("Preloading " + regionRepository.save(
                        new RegionDTO(
                                countryRepository.findByCode("Tunisia").orElse(null),
                                "Sousse")
                ));
                log.info("Preloading " + regionRepository.save(
                        new RegionDTO(
                                countryRepository.findByCode("Turkey").orElse(null),
                                "Alanya")
                ));
                log.info("Preloading " + regionRepository.save(
                        new RegionDTO(
                                countryRepository.findByCode("Turkey").orElse(null),
                                "Belek")
                ));
                log.info("Preloading " + regionRepository.save(
                        new RegionDTO(
                                countryRepository.findByCode("Turkey").orElse(null),
                                "Didyma")
                ));
                log.info("Preloading " + regionRepository.save(
                        new RegionDTO(
                                countryRepository.findByCode("Turkey").orElse(null),
                                "Kemer")
                ));
                log.info("Preloading " + regionRepository.save(
                        new RegionDTO(
                                countryRepository.findByCode("Turkey").orElse(null),
                                "Marmaris")
                ));
                log.info("Preloading " + regionRepository.save(
                        new RegionDTO(
                                countryRepository.findByCode("Turkey").orElse(null),
                                "Antalya")
                ));
                log.info("Preloading " + regionRepository.save(
                        new RegionDTO(
                                countryRepository.findByCode("Turkey").orElse(null),
                                "Bodrum")
                ));
                log.info("Preloading " + regionRepository.save(
                        new RegionDTO(
                                countryRepository.findByCode("Turkey").orElse(null),
                                "Finike")
                ));
                log.info("Preloading " + regionRepository.save(
                        new RegionDTO(
                                countryRepository.findByCode("Turkey").orElse(null),
                                "Kusadasi")
                ));
                log.info("Preloading " + regionRepository.save(
                        new RegionDTO(
                                countryRepository.findByCode("Turkey").orElse(null),
                                "Side")
                ));
                log.info("Preloading " + regionRepository.save(
                        new RegionDTO(
                                countryRepository.findByCode("United States of America").orElse(null),
                                "Florida")
                ));
            }

        };
    }
}
