package com.example.kameleoonproject.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "vote")
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean upvote;

    @ManyToOne
    @JoinColumn(name = "quote_id")
    @JsonBackReference
    private Quote quote;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @DateTimeFormat
    private Date createdDate;

    public Vote(boolean upvote, Quote quote, User user) {
        this.upvote = upvote;
        this.quote = quote;
        this.user = user;
    }
}