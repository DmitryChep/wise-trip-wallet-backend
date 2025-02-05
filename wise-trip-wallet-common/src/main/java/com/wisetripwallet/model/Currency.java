package com.wisetripwallet.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "t_currency")
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    private String symbol;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "currency", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<TripCurrency> tripCurrencies;
}
