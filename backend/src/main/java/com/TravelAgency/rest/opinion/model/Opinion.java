package com.TravelAgency.rest.opinion.model;

import com.TravelAgency.rest.image.opinion.model.OpinionImage;
import com.TravelAgency.rest.offer.model.Offer;
import com.TravelAgency.rest.user.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "opinion")
@NoArgsConstructor
@Getter
@Setter
public class Opinion implements Serializable {
    @Id
    @SequenceGenerator(name = "s_opinion",
            sequenceName = "s_opinion",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "s_opinion"
    )
    @Column(nullable = false, updatable = false)
    private Long id;

    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @PrimaryKeyJoinColumn
    @JsonIgnoreProperties({"id","userRole", "username", "authorities","accountNonExpired","credentialsNonExpired","accountNonLocked"})
    User user;

    @ManyToOne
    @JoinColumn(name = "offer_id")
    @JsonIgnore
    @PrimaryKeyJoinColumn
    Offer offer;

    private Double value;

    private String header;

    private String description;

    @OneToMany(mappedBy = "opinion")
    @JsonIgnoreProperties({"opinion"})
    @PrimaryKeyJoinColumn
    private Set<OpinionImage> opinionImages;

    private LocalDateTime createDate;

    private Boolean visible;


    public Opinion(User user, Offer offer, Double value, String description, LocalDateTime createDate) {
        this.user = user;
        this.offer = offer;
        this.value = value;
        this.description = description;
        this.createDate = createDate;
        this.visible = true;
    }
}
