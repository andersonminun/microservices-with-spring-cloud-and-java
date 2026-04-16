package br.com.anderson.service;

import org.springframework.stereotype.Service;

import br.com.anderson.environment.InstanceInformationService;
import br.com.anderson.model.Book;
import br.com.anderson.proxy.ExchangeServiceProxy;
import br.com.anderson.repository.BookRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final ExchangeServiceProxy exchangeServiceProxy;
    private final InstanceInformationService instanceInformationService;

    public Book findById(Long id, String currency) {

        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        var exchange = exchangeServiceProxy.getExchangeRate(book.getPrice().doubleValue(), "USD", currency);

        book.setPrice(exchange.convertedValue());
        book.setCurrency(currency);
        book.setEnvironment("Book Service - " + instanceInformationService.retrieveServerPort() + " | " +
                "Exchange Service - " + exchange.environment());

        return book;
    }
}
