package com.example.kameleoonproject.controller;


import com.example.kameleoonproject.model.Quote;
import com.example.kameleoonproject.model.User;
import com.example.kameleoonproject.service.QuoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/quotes")
public class QuoteController {

    private final QuoteService quoteService;

    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }


    @PostMapping("/addQuote")
    public ResponseEntity<Quote> createQuote(@AuthenticationPrincipal User user,
                                            @RequestParam String content) {

        Quote quote = quoteService.createQuote(user, content);

        return new ResponseEntity<>(quote, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Quote>> getAllQuotes() {
         List<Quote> quoteList = quoteService.getAllQuotes();
         return new ResponseEntity<>(quoteList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quote> getQuote(@PathVariable Long id) {
        Quote quote = quoteService.getQuote(id);

        return new ResponseEntity<>(quote, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Quote> updateQuote(@PathVariable Long id,
                             @RequestParam String content) {
        Quote quote = quoteService.updateQuote(id, content);
        return new ResponseEntity<>(quote, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteQuote(@PathVariable Long id) {

        quoteService.deleteQuote(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/random")
    public ResponseEntity<Quote> getRandomQuote() {
        Quote quote = quoteService.getRandomQuote();
        return new ResponseEntity<>(quote, HttpStatus.OK);

    }

    @GetMapping("/top10")
    public ResponseEntity<List<Quote>> getTopTenQuotes() {
        List<Quote> quoteList = quoteService.getTopTenQuotes();
        return new ResponseEntity<>(quoteList, HttpStatus.OK);
    }

    @GetMapping("/flop10")
    public ResponseEntity<List<Quote>> getFlopTenQuotes() {
        List<Quote> quoteList = quoteService.getFlopTenQuotes();
        return new ResponseEntity<>(quoteList, HttpStatus.OK);
    }

}
