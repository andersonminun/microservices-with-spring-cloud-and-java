package br.com.anderson.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.anderson.dto.Exchange;

@FeignClient(name = "exchange-service", url = "${EXCHANGE_SERVICE_SERVICE_HOST:http://host.docker.internal}:8000")
public interface ExchangeServiceProxy {

    @GetMapping("/exchange-service/{amount}/{from}/{to}")
    Exchange getExchangeRate(
        @PathVariable Double amount,
        @PathVariable String from,
        @PathVariable String to
    );
}
