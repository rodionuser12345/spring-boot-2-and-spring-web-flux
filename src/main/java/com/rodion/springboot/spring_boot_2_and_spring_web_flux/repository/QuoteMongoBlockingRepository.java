package com.rodion.springboot.spring_boot_2_and_spring_web_flux.repository;

import com.rodion.springboot.spring_boot_2_and_spring_web_flux.domain.Quote;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface QuoteMongoBlockingRepository extends PagingAndSortingRepository<Quote, String> {
    List<Quote> findAllByIdNotNullOrderByIdAsc(final Pageable page);
}

