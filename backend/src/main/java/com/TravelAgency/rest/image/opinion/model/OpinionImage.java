package com.TravelAgency.rest.image.opinion.model;

import com.TravelAgency.rest.opinion.model.Opinion;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "opinion_image")
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
    @PrimaryKeyJoinColumn
    private Opinion opinion;

    private String url;

    private Boolean visible;
}
