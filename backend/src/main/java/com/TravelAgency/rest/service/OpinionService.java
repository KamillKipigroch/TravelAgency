package com.TravelAgency.rest.service;

import com.TravelAgency.rest.model.database.Offer;
import com.TravelAgency.rest.model.database.Opinion;
import com.TravelAgency.rest.model.dto.OpinionRequest;
import com.TravelAgency.rest.repository.OpinionRepository;
import com.TravelAgency.security.user.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.module.FindException;
import java.time.LocalDateTime;
import java.util.List;

import static com.TravelAgency.comunicates.Communicates.*;


@Service
@AllArgsConstructor
public class OpinionService {
    private final OpinionRepository opinionRepository;

    public List<Opinion> findAll() {
        return opinionRepository.findAll();
    }

    public Opinion findById(Long id) {
        return opinionRepository.findById(id)
                .orElseThrow(() -> new FindException(NOT_FOUND_WITH_ID + id));
    }

    public Opinion add(OpinionRequest request, User user, Offer offer) {
        if (opinionRepository.findOpinionByUserAndOffer(user, offer).isPresent()) {
            throw new FindException(USER_HAVE_OPINION_THIS_PRODUCT);
        }
        var newObject = new Opinion();
        newObject.setUser(user);
        newObject.setOffer(offer);
        newObject.setHeader(request.getHeader());
        newObject.setDescription(request.getDescription());
        newObject.setValue(request.getValue());
        newObject.setCreateDate(LocalDateTime.now());
        newObject.setVisible(true);
        return opinionRepository.save(newObject);
    }

    public Opinion update(Opinion opinion) {
        var update = opinionRepository.findById(opinion.getId()).orElseThrow(() ->
                new FindException(NOT_FOUND_WITH_ID + opinion.getId()));
        update.setValue(opinion.getValue());
        update.setDescription(opinion.getDescription());
        update.setVisible(opinion.getVisible());
        return opinionRepository.save(update);
    }

    public void delete(Long id) {
        var object = opinionRepository.findById(id).orElseThrow(() ->
                new FindException(NOT_FOUND_WITH_ID + id));
        object.setVisible(false);
        opinionRepository.save(object);
    }
}
