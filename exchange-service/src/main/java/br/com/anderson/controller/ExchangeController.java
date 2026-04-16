package br.com.anderson.controller;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.anderson.model.Exchange;
import br.com.anderson.service.ExchangeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Exchange Service API", description = "API para gerenciamento de câmbio")
@RestController
@RequestMapping("/exchange-service")
@RequiredArgsConstructor
public class ExchangeController {

    private final ExchangeService exchangeService;

    @Operation(summary = "Converter moeda", description = "Converte um valor de uma moeda para outra")
    @GetMapping("/{amount}/{from}/{to}")
    public Exchange getExchange(
        @PathVariable BigDecimal amount, 
        @PathVariable String from, 
        @PathVariable String to) {

        return exchangeService.convert(amount, from, to);
    }

}
