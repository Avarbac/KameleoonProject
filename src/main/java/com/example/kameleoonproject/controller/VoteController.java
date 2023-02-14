package com.example.kameleoonproject.controller;

import com.example.kameleoonproject.model.User;
import com.example.kameleoonproject.service.VoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/vote")
public class VoteController {

    private VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping("/{id}/upvote")
    @ResponseStatus(HttpStatus.OK)
    public void upvote(@PathVariable Long id, @AuthenticationPrincipal User user) {
        voteService.vote(id, user, true);
    }

    @PostMapping("/{id}/downvote")
    @ResponseStatus(HttpStatus.OK)
    public void downvote(@PathVariable Long id, @AuthenticationPrincipal User user) {
        voteService.vote(id, user, false);
    }
}
