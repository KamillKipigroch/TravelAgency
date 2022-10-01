package com.TravelAgency.offer.model.nregistered;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table (name = "board_basis_detail_t")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BoardBasisDetails implements Serializable {
    @Id
    @SequenceGenerator(name = "s_board_basis_detail",
            sequenceName = "s_board_basis_detail",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "s_board_basis_detail"
    )
    @Column(nullable = false, updatable = false)
    Long id;
    @Column(nullable = false, unique = true)
    String code;

    Boolean visible;
    public BoardBasisDetails(String code) {
        this.code = code;
        this.visible = false;
    }
}
