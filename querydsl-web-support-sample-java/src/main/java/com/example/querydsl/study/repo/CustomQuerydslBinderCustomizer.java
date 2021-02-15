package com.example.querydsl.study.repo;

import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface CustomQuerydslBinderCustomizer<T extends EntityPath<?>> extends QuerydslBinderCustomizer<T> {

    void addCustomization(QuerydslBindings bindings, T entityPath);

    @Override
    default void customize(QuerydslBindings bindings, T entityPath) {

        bindings.bind(Date.class).all((path, value) -> {
            List<? extends Date> dates = new ArrayList<>(value);
            if (dates.size() == 1) {
                return Optional.of(((DateTimePath) path).eq(dates.get(0)));
            } else {
                Date from = dates.get(0);
                Date to = dates.get(1);
                return Optional.of(((DateTimePath) path).between(from, to));
            }
        });


        bindings.bind(String.class).all((path, value) -> {
            List<? extends String> values = new ArrayList<>(value);
            if (values.size() == 1) {
                String searchWord = values.get(0);
                if (searchWord.startsWith("%")) {
                    return Optional.of(((StringPath) path).like(searchWord));
                } else {
                    return Optional.of(((StringPath) path).eq(searchWord));
                }
            } else {
                return Optional.of(((StringPath) path).in(values));
            }
        });

        this.addCustomization(bindings, entityPath);
    }
}
