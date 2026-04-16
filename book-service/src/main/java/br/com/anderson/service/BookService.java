package br.com.anderson.service;

import org.springframework.stereotype.Service;

import br.com.anderson.environment.InstanceInformationService;
import br.com.anderson.model.Book;
import br.com.anderson.proxy.ExchangeServiceProxy;
import br.com.anderson.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final ExchangeServiceProxy exchangeServiceProxy;
    private final InstanceInformationService instanceInformationService;

    public Book findById(Long id, String currency) {

        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));

        log.info("Calculando o preço convertido de {} USD para {}", book.getPrice().doubleValue(), currency);
        var exchange = exchangeServiceProxy.getExchangeRate(book.getPrice().doubleValue(), "USD", currency);

        book.setPrice(exchange.convertedValue());
        book.setCurrency(currency);
        book.setEnvironment("Book Port - " + instanceInformationService.retrieveServerPort() +
                " Host: " + instanceInformationService.retrieveHostName() + " | " +
                "Exchange Host - " + exchange.environment());

        return book;
    }
}
