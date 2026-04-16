package br.com.anderson.dto;

import java.math.BigDecimal;

public record Exchange(
    Long id,
    String from,
    String to,
    BigDecimal conversionFactor,
    BigDecimal convertedValue,
    String environment
) {}
