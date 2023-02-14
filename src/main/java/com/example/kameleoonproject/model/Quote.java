package com.example.kameleoonproject.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;


import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "quote")
@NoArgsConstructor
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String content;

    @CreationTimestamp
    private Date dateCreated;

    @LastModifiedDate
    private Date dateUpdated;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private User user;

    @OneToMany(mappedBy = "quote")
    @JsonManagedReference
    private List<Vote> votes;

    @Column(name = "votes_score")
    private int votesScore;

    public Quote(User user, String content) {
        this.content = content;
        this.user = user;
        this.dateCreated = new Date();
    }

    public void changeVoteCount(boolean upvote) {
        this.votesScore = upvote ? votesScore +2 : votesScore -2;
    }

    public void changeAndSaveVoteCount(Vote vote, boolean upvote) {
        this.votes.add(vote);
        this.votesScore = upvote ? votesScore +1 : votesScore -1;
    }

}
