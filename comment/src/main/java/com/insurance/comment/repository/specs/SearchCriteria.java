package com.insurance.comment.repository.specs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchCriteria {
    private String key;
    private SearchOperation operation;
    private Object value;
    private String join;
    private boolean orPredicate;

    public SearchCriteria(String key, SearchOperation operation, Object value, String join) {
        this.key = key;
        this.operation = operation;
        this.value = value;
        this.join = join;
    }
}
