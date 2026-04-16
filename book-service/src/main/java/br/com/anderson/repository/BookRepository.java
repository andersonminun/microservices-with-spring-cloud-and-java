package br.com.anderson.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.anderson.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
