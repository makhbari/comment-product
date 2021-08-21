package com.insurance.comment.repository.specs;

import com.insurance.comment.model.BaseEntity;
import com.insurance.comment.repository.filter.FilterDto;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public abstract class SpecificationsBuilder<T extends FilterDto, E extends BaseEntity> {
    protected List<SearchCriteria> params;

    public SpecificationsBuilder() {
        params = new ArrayList<>();
    }

    public SpecificationsBuilder with(
            String key, String operation, Object value) {
        return with(key, operation, value, "", "", null);
    }

    public SpecificationsBuilder with(
            String key, String operation, Object value, String join) {
        return with(key, operation, value, "", "", join);
    }

    public SpecificationsBuilder with(
            String key, String operation, Object value, String prefix, String suffix, String join) {
        if (prefix == null) {
            prefix = "";
        }

        if (suffix == null) {
            suffix = "";
        }

        SearchOperation op = SearchOperation.getSimpleOperation(operation);
        if (op != null) {
            if (op == SearchOperation.EQUALITY) {
                boolean startWithAsterisk = prefix.contains("*");
                boolean endWithAsterisk = suffix.contains("*");

                if (startWithAsterisk && endWithAsterisk) {
                    op = SearchOperation.CONTAINS;
                } else if (startWithAsterisk) {
                    op = SearchOperation.ENDS_WITH;
                } else if (endWithAsterisk) {
                    op = SearchOperation.STARTS_WITH;
                }
            }
            params.add(new SearchCriteria(key, op, value, join));
        }
        return this;
    }

    public abstract Specification<E> build(T filterDto);

    protected Specification<E> build(Function<SearchCriteria, EntitySpecification> function) {
        if (params.size() == 0) {
            return null;
        }

        Specification result = function.apply(params.get(0));

        for (int i = 1; i < params.size(); i++) {
            result = params.get(i).isOrPredicate()
                    ? Specification.where(result).or(function.apply(params.get(i)))
                    : Specification.where(result).and(function.apply(params.get(i)));
        }

        return result;
    }

}
