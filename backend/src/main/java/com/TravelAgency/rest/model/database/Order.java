package com.TravelAgency.rest.model.database;

import com.TravelAgency.security.user.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "order_t")
@NoArgsConstructor
@Getter
@Setter
public class Order implements Serializable {
    @Id
    @SequenceGenerator(name = "s_order",
            sequenceName = "s_order",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "s_order"
    )
    @Column(nullable = false, updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_status_id")
    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name = "deadline")
    private OfferAvailability deadline;

    @ManyToOne
    @JoinColumn(name = "room")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Boolean visible;
}
