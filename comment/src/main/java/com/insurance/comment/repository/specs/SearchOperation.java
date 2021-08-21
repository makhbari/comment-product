package com.insurance.comment.repository.specs;

public enum SearchOperation {
    EQUALITY, NEGATION, GREATER_THAN, GREATER_THAN_OR_EQUAL, LESS_THAN, LESS_THAN_OR_EQUAL, LIKE, STARTS_WITH, ENDS_WITH, CONTAINS;

    public static SearchOperation getSimpleOperation(final String input) {
        switch (input) {
            case "eq":
                return EQUALITY;
            case "!":
                return NEGATION;
            case ">":
                return GREATER_THAN;
            case ">=":
                return GREATER_THAN_OR_EQUAL;
            case "<":
                return LESS_THAN;
            case "<=":
                return LESS_THAN_OR_EQUAL;
            case "~":
                return LIKE;
            case "cn":
                return CONTAINS;
            default:
                return null;
        }
    }
}
