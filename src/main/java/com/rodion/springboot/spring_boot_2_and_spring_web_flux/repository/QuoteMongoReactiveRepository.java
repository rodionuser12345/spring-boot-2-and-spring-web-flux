package com.rodion.springboot.spring_boot_2_and_spring_web_flux.repository;

import com.rodion.springboot.spring_boot_2_and_spring_web_flux.domain.Quote;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Flux;

public interface QuoteMongoReactiveRepository extends ReactiveSortingRepository<Quote, String> {
    Flux<Quote> findAllByIdNotNullOrderByIdAsc(final Pageable page);
}
