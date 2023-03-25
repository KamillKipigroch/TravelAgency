package com.TravelAgency.rest.model.database;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "order_status")
@NoArgsConstructor
@Getter
@Setter
public class OrderStatus implements Serializable {
    @Id
    @SequenceGenerator(name = "s_order_status",
            sequenceName = "s_order_status",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "s_order_status"
    )
    @Column(nullable = false, updatable = false)
    private Long id;

    private String name;

    private Boolean visible;

    private Boolean itsFinal;

    @Column(nullable = false)
    private Integer level;
}
