package com.TravelAgency;

import com.TravelAgency.rest.model.country.Country;
import com.TravelAgency.rest.repository.CountryRepository;
import com.TravelAgency.security.user.model.User;
import com.TravelAgency.security.user.model.UserRole;
import com.TravelAgency.security.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, CountryRepository countryRepository) {


        return args -> {
/*
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
            }*/
            if (countryRepository.findAll().size() < 1) {
                log.info("Preloading " + countryRepository.save(new Country("Albania", "AL")));
                log.info("Preloading " + countryRepository.save(new Country("Bulgaria", "BG")));
                log.info("Preloading " + countryRepository.save(new Country("Croatia", "HR")));
                log.info("Preloading " + countryRepository.save(new Country("Cuba", "CU")));
                log.info("Preloading " + countryRepository.save(new Country("Cyprus", "CY")));
                log.info("Preloading " + countryRepository.save(new Country("Dominican Republic", "DO")));
                log.info("Preloading " + countryRepository.save(new Country("Egypt", "EG")));
                log.info("Preloading " + countryRepository.save(new Country("Georgia", "GE")));
                log.info("Preloading " + countryRepository.save(new Country("Greece", "GR")));
                log.info("Preloading " + countryRepository.save(new Country("Italy", "IT")));
                log.info("Preloading " + countryRepository.save(new Country("Madagascar", "MG")));
                log.info("Preloading " + countryRepository.save(new Country("Mayotte","TY")));
                log.info("Preloading " + countryRepository.save(new Country("Maldives","MV")));
                log.info("Preloading " + countryRepository.save(new Country("Mexico", "MX")));
                log.info("Preloading " + countryRepository.save(new Country("Oman","OM")));
                log.info("Preloading " + countryRepository.save(new Country("Poland","PL")));
                log.info("Preloading " + countryRepository.save(new Country("Portugal","PT")));
                log.info("Preloading " + countryRepository.save(new Country("Spain","ES")));
                log.info("Preloading " + countryRepository.save(new Country("Thailand","TH")));
                log.info("Preloading " + countryRepository.save(new Country("Turkey", "TR")));
                log.info("Preloading " + countryRepository.save(new Country("United Arab Emirates","AE")));
                log.info("Preloading " + countryRepository.save(new Country("United States", "US")));
                log.info("Preloading " + countryRepository.save(new Country("Zimbabwe", "ZW")));
            }
            /*if (regionRepository.findAll().size() < 1 && countryRepository.findAll().size() > 0) {
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
                ));*/


            if (userRepository.findAll().isEmpty()) {
                BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
                log.info("Preloading " + userRepository.save(new User("admin@admin.pl", bCryptPasswordEncoder.encode("admin@admin.pl"), UserRole.Admin)));
                log.info("Preloading " + userRepository.save(new User("user@user.pl", bCryptPasswordEncoder.encode("user@user.pl"), UserRole.User)));
            }
        };

    }

    ;
}

