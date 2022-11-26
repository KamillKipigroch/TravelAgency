package com.TravelAgency.rest.model.opinionImage;

import com.TravelAgency.rest.model.opinion.Opinion;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "opinion_image_t")
@NoArgsConstructor
@Getter
@Setter
public class OpinionImage implements Serializable {
    @Id
    @SequenceGenerator(name = "s_opinion_image",
            sequenceName = "s_opinion_image",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "s_opinion_image"
    )
    @Column(nullable = false, updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "opinion_id")
    @JsonIgnore
    private Opinion opinion;

    private String url;

    private Boolean visible;
}
