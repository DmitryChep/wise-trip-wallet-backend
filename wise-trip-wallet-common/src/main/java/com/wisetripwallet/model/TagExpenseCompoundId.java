package com.wisetripwallet.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class TagExpenseCompoundId implements Serializable {
    private Long tagId;
    private Long expenseId;

    public TagExpenseCompoundId() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagExpenseCompoundId that = (TagExpenseCompoundId) o;
        return tagId.equals(that.tagId) && expenseId.equals(that.expenseId);
    }

    @Override
    public int hashCode() {
        return 31 * tagId.hashCode() + expenseId.hashCode();
    }
}
