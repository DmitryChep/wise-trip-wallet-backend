package com.wisetripwallet.model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ExpenseCategoryCompoundId implements Serializable {
    private Long expenseId;
    private Long categoryId;
}
