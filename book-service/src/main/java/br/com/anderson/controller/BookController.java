package br.com.anderson.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.anderson.model.Book;
import br.com.anderson.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;


@Tag(name = "Book Controller", description = "Endpoints para gerenciamento de livros")
@RestController
@RequestMapping("book-service")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @Operation(summary = "Buscar livro por ID e moeda", description = "Retorna um livro com o preço convertido para a moeda especificada")
    @GetMapping("/{id}/{currency}")
    public Book findBook(@PathVariable Long id, @PathVariable String currency) {
        return bookService.findById(id, currency);
    }
}
