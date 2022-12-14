package com.TravelAgency.rest.service;

import com.TravelAgency.LoadDatabase;
import com.TravelAgency.rest.model.database.Opinion;
import com.TravelAgency.rest.model.database.OpinionImage;
import com.TravelAgency.rest.repository.OpinionImageRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OpinionImageService {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
    private final OpinionImageRepository opinionImageRepository;

    public OpinionImage add(Opinion opinion, String url) {
        var newObject = new OpinionImage();
        try {
            newObject.setUrl(url);
            newObject.setVisible(true);
            newObject.setOpinion(opinion);
            return opinionImageRepository.save(newObject);
        }
        catch (Exception e){
            log.error("Upload image filed ! " + url);
        }
        return null;
    }
}
