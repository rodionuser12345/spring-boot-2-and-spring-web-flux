package com.rodion.springboot.spring_boot_2_and_spring_web_flux.configuration;

import com.rodion.springboot.spring_boot_2_and_spring_web_flux.domain.Quote;
import com.rodion.springboot.spring_boot_2_and_spring_web_flux.repository.QuoteMongoReactiveRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.function.Supplier;

@Component
public class QuoteDataLoader implements ApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(QuoteDataLoader.class);
    private final QuoteMongoReactiveRepository quoteMongoReactiveRepository;

    QuoteDataLoader(final QuoteMongoReactiveRepository quoteMongoReactiveRepository) {
        this.quoteMongoReactiveRepository = quoteMongoReactiveRepository;
    }

    @Override
    public void run(final ApplicationArguments args) {
        if (quoteMongoReactiveRepository.count().block() == 0L) {
            var idSupplier = getIdSequenceSupplier();
            var bufferedReader = new BufferedReader(
                    new InputStreamReader(getClass()
                            .getClassLoader()
                            .getResourceAsStream("pg2000.txt"))
            );
            Flux.fromStream(
                    bufferedReader.lines()
                            .filter(l -> !l.trim().isEmpty())
                            .map(l -> quoteMongoReactiveRepository.save(
                                    new Quote(idSupplier.get(),
                                            "El Quijote", l))
                            )
            ).subscribe(m -> log.info("New quote loaded: {}", m.block()));
            log.info("Repository contains now {} entries.",
                    quoteMongoReactiveRepository.count().block());
        }
    }

    private Supplier<String> getIdSequenceSupplier() {
        return new Supplier<>() {
            Long l = 0L;

            @Override
            public String get() {
                // adds padding zeroes
                return String.format("%05d", l++);
            }
        };
    }
}
