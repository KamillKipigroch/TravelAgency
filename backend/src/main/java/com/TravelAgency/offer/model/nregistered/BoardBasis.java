package com.TravelAgency.offer.model.nregistered;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table (name = "board_basis_t")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BoardBasis implements Serializable {
    @Id
    @SequenceGenerator(name = "s_board_basis",
            sequenceName = "s_board_basis",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "s_board_basis"
    )
    @Column(nullable = false, updatable = false)
    Long id;

    @ManyToOne
    @JoinColumn(name = "code_id")
    BoardBasisDetails code;

    Double price;

    @ManyToOne
    @JoinColumn(name = "offer_details_id")
    OfferDetails offerDetails;

    public BoardBasis(BoardBasisDetails code) {
        this.code = code;
    }
}
