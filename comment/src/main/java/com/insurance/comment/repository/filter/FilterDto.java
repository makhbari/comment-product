package com.insurance.comment.repository.filter;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Getter
@Setter
public class FilterDto {
    public static final int DEFAULT_PAGE_SIZE = 20;
    private Integer pageSize;
    private Integer pageNumber;
    private String sortBy;
    private String sortDirection;

    public Pageable getPageable() {
        return getPageable(null, null);
    }

    public Pageable getPageable(String defaultSortBy, Sort.Direction defaultSortDirection) {
        if (sortBy == null) {
            return getDefaultPageable(defaultSortBy, defaultSortDirection);
        } else {
            return PageRequest.of(
                    pageNumber == null ? 0 : pageNumber,
                    pageSize == null ? DEFAULT_PAGE_SIZE : pageSize,
                    Sort.by(findSortDirection(), sortBy)
            );
        }
    }

    public Sort.Direction findSortDirection() {
        if ("asc".equalsIgnoreCase(sortDirection)) {
            return Sort.Direction.ASC;
        } else if ("desc".equalsIgnoreCase(sortDirection)) {
            return Sort.Direction.DESC;
        } else {
            return Sort.Direction.DESC;
        }
    }

    private PageRequest getDefaultPageable(String defaultSortBy, Sort.Direction defaultSortDirection) {
        if (defaultSortBy != null) {
            Sort by = Sort.by(defaultSortDirection == null ? Sort.Direction.DESC : defaultSortDirection, defaultSortBy);
            return PageRequest.of(
                    pageNumber == null ? 0 : pageNumber,
                    pageSize == null ? DEFAULT_PAGE_SIZE : pageSize,
                    by);
        } else {
            return PageRequest.of(
                    pageNumber == null ? 0 : pageNumber,
                    pageSize == null ? DEFAULT_PAGE_SIZE : pageSize);
        }
    }
}
