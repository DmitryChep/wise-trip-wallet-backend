package com.wisetripwallet.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

@Entity
@Table(name = "t_tag_expense")
public class TagExpense {

    @EmbeddedId
    private TagExpenseCompoundId id;

    @ManyToOne
    @MapsId("tagId")
    @JoinColumn(name = "tag_id", nullable = false, insertable = false, updatable = false)
    private Tag tag;

    @ManyToOne
    @MapsId("expenseId")
    @JoinColumn(name = "expense_id", nullable = false, insertable = false, updatable = false)
    private Expense expense;
}
