package com.example.kameleoonproject.repository;

import com.example.kameleoonproject.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {

    @Query(value =
            "Select * From quote q " +
            "order by q.votes_score " +
            "DESC limit 10",
            nativeQuery = true
    )
    List<Quote> findTopTenQuotes();

    @Query(value =
            "Select * From quote q " +
                    "order by q.votes_score " +
                    "ASC limit 10",
            nativeQuery = true
    )
    List<Quote> findFlopTenQuotes();
}
