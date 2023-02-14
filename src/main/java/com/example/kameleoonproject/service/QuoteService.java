package com.example.kameleoonproject.service;

import com.example.kameleoonproject.exception.QuoteNotFoundException;
import com.example.kameleoonproject.model.Quote;
import com.example.kameleoonproject.model.User;
import com.example.kameleoonproject.repository.QuoteRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class QuoteService {

    private final QuoteRepository quoteRepository;

    @Autowired
    public QuoteService (QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    @Transactional
    public Quote createQuote(User user, String content) {
        Quote quote = new Quote(user, content);
        return quoteRepository.save(quote);
    }

    public List<Quote> getAllQuotes() {
        return quoteRepository.findAll();
    }

    public Quote getQuote(Long id) {
        Optional<Quote> quote = quoteRepository.findById(id);
        return quote.orElseThrow(() -> new QuoteNotFoundException("Quote with id " + id + " not found"));
    }

    @Transactional
    public Quote updateQuote(Long id, String content) {
        Quote existingQuote = getQuote(id);
        existingQuote.setContent(content);
        existingQuote.setDateUpdated(new Date());
        return quoteRepository.save(existingQuote);
    }

    @Transactional
    public void deleteQuote(Long id) {
        quoteRepository.deleteById(id);
    }

    public Quote getRandomQuote() {
        List<Quote> quotes = quoteRepository.findAll();
        int size = quotes.size();
        int randomIndex = (int) (Math.random() * size);
        return quotes.get(randomIndex);
    }

    public List<Quote> getTopTenQuotes() {
        return quoteRepository.findTopTenQuotes();
    }

    public List<Quote> getFlopTenQuotes() {
        return quoteRepository.findFlopTenQuotes();
    }

}
