package br.com.anderson.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import br.com.anderson.environment.InstanceInformationService;
import br.com.anderson.model.Exchange;
import br.com.anderson.repository.ExchangeRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExchangeService {

    private final ExchangeRepository repository;
    private final InstanceInformationService instanceInformationService;

    public Exchange convert(BigDecimal amount, String from, String to) {

        Exchange exchange = repository.findByFromAndTo(from, to);
        
        if (exchange == null) {
            throw new RuntimeException("Currency not found");
        }

        exchange.setConvertedValue(exchange.getConversionFactor().multiply(amount));
        exchange.setEnvironment(instanceInformationService.retrieveServerPort());

        return exchange;
    }
}
