package com.example.querydsl.study.repo;

import com.example.querydsl.study.entity.Post;
import com.querydsl.core.types.Predicate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.MethodParameter;
import org.springframework.data.querydsl.SimpleEntityPathResolver;
import org.springframework.data.querydsl.binding.QuerydslBindingsFactory;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.querydsl.QuerydslPredicateArgumentResolver;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.ServletWebRequest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;

import static com.example.querydsl.study.entity.QPost.post;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PostQuerydslBindingTest {

    private QuerydslPredicateArgumentResolver resolver;

    private MockHttpServletRequest request;

    private DateFormat dateFormat;

    @BeforeEach
    void setUp() {
        this.resolver = new QuerydslPredicateArgumentResolver(new QuerydslBindingsFactory(SimpleEntityPathResolver.INSTANCE),
                Optional.of(new DefaultFormattingConversionService()));
        this.request = new MockHttpServletRequest();

        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
    }

    @Test
    public void binding_date_parameters_test() throws Exception {
        Date startAt = Date.from(LocalDateTime.now().minus(30, ChronoUnit.DAYS).atZone(ZoneId.systemDefault()).toInstant());
        Date endAt = new Date();

        request.addParameter("createdAt", dateFormat.format(startAt));
        request.addParameter("createdAt", dateFormat.format(endAt));
        request.addParameter("modifiedAt", dateFormat.format(startAt));

        Object predicate = resolver.resolveArgument(
                new MethodParameter(PostApi.class.getMethod("find", Predicate.class), 0),
                null,
                new ServletWebRequest(request),
                null);

        assertNotNull(predicate);
        assertEquals(post.createdAt.between(startAt, endAt).and(post.modifiedAt.eq(startAt)), predicate);
    }

    interface PostApi {

        void find(@QuerydslPredicate(root = Post.class, bindings = PostRepository.class) Predicate predicate);
    }
}
