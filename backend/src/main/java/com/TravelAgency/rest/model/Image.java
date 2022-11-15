package com.TravelAgency.rest.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "image_t")
@NoArgsConstructor
@Getter
@Setter
public class Image implements Serializable {
    @Id
    @SequenceGenerator(name = "s_image", sequenceName = "s_image", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_image")
    @Column(nullable = false, updatable = false)
    private Long id;

    private String code;

    private Boolean visible;


    public Image(String code) {
        this.code = code;
        this.visible = true;
    }
}

