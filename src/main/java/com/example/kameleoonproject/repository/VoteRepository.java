package com.example.kameleoonproject.repository;

import com.example.kameleoonproject.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {


    List<Vote> findVotesByQuoteId(Long quoteId);

    @Modifying
    @Query(value = "Update vote as v set v.upvote = ? where v.id = ?", nativeQuery = true)
    void updateVote(Boolean upvote, Long id);
}