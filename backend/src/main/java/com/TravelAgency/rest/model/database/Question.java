package com.TravelAgency.rest.model.database;

import com.TravelAgency.security.user.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table (name = "question")
@NoArgsConstructor
@Getter
@Setter
public class Question implements Serializable {
    @Id
    @SequenceGenerator(name = "s_question",
            sequenceName = "s_question",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "s_question"
    )
    @Column(nullable = false, updatable = false)
    Long id;

    @Column(updatable = false)
    String email;

    @Column(length = 3000)
    String message;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @PrimaryKeyJoinColumn
    User employee;

    @Column(length = 3000)
    String answerMessage;

    Boolean answered;
}
