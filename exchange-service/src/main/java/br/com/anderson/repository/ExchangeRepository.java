package br.com.anderson.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.anderson.model.Exchange;

public interface ExchangeRepository extends JpaRepository<Exchange, Long> {

    Exchange findByFromAndTo(String from, String to);
}
