package com.example.querydsl.study.core.querydsl;

import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.Objects;

public abstract class CustomQuerydslRepositorySupport extends QuerydslRepositorySupport {

    protected <T> JPQLQuery<T> applySorting(Pageable pageable, JPQLQuery<T> query) {
        return this.applySorting(pageable.getSort(), query);
    }

    protected <T> JPQLQuery<T> applyPagination(Pageable pageable, JPQLQuery<T> query) {
        return Objects.requireNonNull(getQuerydsl()).applyPagination(pageable, query);
    }

    protected <T> JPQLQuery<T> applySorting(Sort sort, JPQLQuery<T> query) {
        return Objects.requireNonNull(getQuerydsl()).applySorting(sort, query);
    }

    public CustomQuerydslRepositorySupport(Class<?> domainClass) {
        super(domainClass);
    }
}
