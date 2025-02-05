package com.wisetripwallet.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

@Entity
@Table(name = "t_trip")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "trip_name", nullable = false)
    private String tripName;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "total_budget", precision = 10, scale = 2)
    private BigDecimal totalBudget;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL)
    private Set<Category> categories;

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL)
    private Set<Budget> budgets;

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL)
    private Set<Report> reports;

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL)
    private Set<Expense> expenses;

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL)
    private Set<TripCurrency> tripCurrencies;
}

