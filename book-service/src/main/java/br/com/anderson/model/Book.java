package br.com.anderson.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
@Entity
@Table(name = "book")
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "longtext")
    private String title;

    @Column(columnDefinition = "longtext")
    private String author;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "launch_date", nullable = false)
    private Date launchDate;

    @Column(nullable = false, precision = 65, scale = 2)
    private BigDecimal price;

    @Transient
    private String currency;

    @Transient
    private String environment;
}
