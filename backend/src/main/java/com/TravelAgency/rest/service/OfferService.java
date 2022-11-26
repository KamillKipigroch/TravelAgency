package com.TravelAgency.rest.service;

import com.TravelAgency.rest.model.offer.Offer;
import com.TravelAgency.rest.model.opinion.Opinion;
import com.TravelAgency.rest.model.opinion.OpinionRequest;
import com.TravelAgency.rest.repository.OfferRepository;
import com.TravelAgency.security.user.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.module.FindException;
import java.time.LocalDateTime;
import java.util.List;

import static com.TravelAgency.comunicates.Communicates.*;


@Service
@AllArgsConstructor
public class OfferService {
    private final OfferRepository offerRepository;

    public List<Offer> findAll() {
        return offerRepository.findAll();
    }

    public Offer findById(Long id) {
        return offerRepository.findById(id)
                .orElseThrow(() -> new FindException(NOT_FOUND_WITH_ID + id));
    }

    public Offer findByOfferCode(String code) {
        return offerRepository.findByOfferCode(code)
                .orElseThrow(() -> new FindException(NOT_FOUND_WITH_NAME ));
    }

    public Offer add(OpinionRequest request) {
        var newObject = new Offer();
        newObject.setCreateDate(LocalDateTime.now());
        newObject.setVisible(true);
        return offerRepository.save(newObject);
    }

    public Offer update(Opinion opinion) {
        var update = offerRepository.findById(opinion.getId()).orElseThrow(() ->
                new FindException(NOT_FOUND_WITH_ID + opinion.getId()));
//        update.setVisible(setVisibleopinion.getValue());
        update.setDescription(opinion.getDescription());
        update.setVisible(opinion.getVisible());
        return offerRepository.save(update);
    }

    public void delete(Long id) {
        var object = offerRepository.findById(id).orElseThrow(() ->
                new FindException(NOT_FOUND_WITH_ID + id));
        object.setVisible(false);
        offerRepository.save(object);
    }
}
