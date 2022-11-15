package com.TravelAgency.rest.model;

import com.TravelAgency.security.user.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "opinion_t")
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

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    private Double value;

    private String description;

    private LocalDateTime createDate;

    private Boolean visible;


    public Opinion(User user,  Double value, String description, LocalDateTime createDate) {
        this.user = user;
//        this.product = product;
//        this.images = images;
        this.value = value;
        this.description = description;
        this.createDate = createDate;
        this.visible = true;
    }
}
