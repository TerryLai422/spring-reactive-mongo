package com.thinkbox.reactive.controller;

import java.time.Duration;
import com.thinkbox.reactive.dto.QuoteDto;
import com.thinkbox.reactive.service.QuoteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/quotes")
public class QuoteController {

    @Autowired
    private QuoteService service;

    @GetMapping(produces=MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<QuoteDto> getQuotes(){
//        return service.getQuotes().delayElements(Duration.ofSeconds(2));
      return service.getQuotes();
    }

    @GetMapping("/{symbol}")
    public Mono<QuoteDto> getQuote(@PathVariable String symbol){
        return service.getQuote(symbol);
    }

    @PostMapping
    public Mono<QuoteDto> saveQuote(@RequestBody Mono<QuoteDto> quoteDtoMono){
        System.out.println("controller method called ...");
        return service.saveQuote(quoteDtoMono);
    }

    @PutMapping("/update/{id}")
    public Mono<QuoteDto> updateQuote(@RequestBody Mono<QuoteDto> quoteDtoMono,@PathVariable String id){
        return service.updateQuote(quoteDtoMono,id);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<Void> deleteQuote(@PathVariable String id){
        return service.deleteQuote(id);
    }
}
