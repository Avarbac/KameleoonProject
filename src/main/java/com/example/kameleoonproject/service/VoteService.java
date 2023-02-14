package com.example.kameleoonproject.service;

import com.example.kameleoonproject.exception.AlreadyVotedException;
import com.example.kameleoonproject.exception.QuoteNotFoundException;
import com.example.kameleoonproject.model.Quote;
import com.example.kameleoonproject.model.User;
import com.example.kameleoonproject.model.Vote;
import com.example.kameleoonproject.repository.QuoteRepository;
import com.example.kameleoonproject.repository.VoteRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class VoteService {

    private final VoteRepository voteRepository;
    private final QuoteRepository quoteRepository;

    @Autowired
    public VoteService(VoteRepository voteRepository, QuoteRepository quoteRepository) {

        this.voteRepository = voteRepository;
        this.quoteRepository = quoteRepository;
    }

    public Vote updateVote(Vote vote, boolean upvote) {
        Vote existingVote = vote;
        existingVote.setUpvote(upvote);
        return voteRepository.save(existingVote);
    }

    @Transactional
    public void vote(Long id, User user, Boolean isUpvote) {

        //Check whether a user have already voted
        Quote quote = quoteRepository.findById(id)
                .orElseThrow(() -> new QuoteNotFoundException("Quote with id " + id + " not found"));

        Optional<Vote> optionalVote = voteRepository.findVotesByQuoteId(id).stream()
                .filter(userVote -> userVote.getUser().getId().equals(user.getId()))
                .findFirst();

        if (optionalVote.isPresent() && optionalVote.get().isUpvote() == isUpvote) {
            throw new AlreadyVotedException("You`ve already voted for this quote");
        } else if (optionalVote.isPresent() && optionalVote.get().isUpvote() != isUpvote) {
            Vote vote = optionalVote.get();
            voteRepository.updateVote(isUpvote, vote.getId());

            quote.changeVoteCount(isUpvote);
        } else {
            Vote vote = new Vote(isUpvote, quote, user);
            voteRepository.save(vote);

            quote.changeAndSaveVoteCount(vote, isUpvote);
        }
        quoteRepository.save(quote);

    }

}