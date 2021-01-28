package com.example.querydsl.study.post.repo;

import com.example.querydsl.study.post.entity.Post;
import com.querydsl.core.types.Predicate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.convert.ApplicationConversionService;
import org.springframework.core.MethodParameter;
import org.springframework.data.querydsl.SimpleEntityPathResolver;
import org.springframework.data.querydsl.binding.QuerydslBindingsFactory;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.querydsl.QuerydslPredicateArgumentResolver;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.ServletWebRequest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;

import static com.example.querydsl.study.post.entity.QPost.post;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class PostQuerydslPredicateTest {

    private QuerydslPredicateArgumentResolver resolver;

    private MockHttpServletRequest request;

    private DateFormat dateFormat;

    @BeforeEach
    void setUp() {
        this.resolver = new QuerydslPredicateArgumentResolver(new QuerydslBindingsFactory(SimpleEntityPathResolver.INSTANCE),
                Optional.of(new ApplicationConversionService()));
        this.request = new MockHttpServletRequest();

        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
    }

    @Test
    public void between_createdAt_test() throws Exception {
        Date startAt = Date.from(LocalDateTime.now().minus(30, ChronoUnit.DAYS).atZone(ZoneId.systemDefault()).toInstant());
        Date endAt = new Date();
        request.addParameter("createdAt", dateFormat.format(startAt));
        request.addParameter("createdAt", dateFormat.format(endAt));

        Object predicate = resolver.resolveArgument(
                new MethodParameter(PostApi.class.getMethod("find", Predicate.class), 0),
                null,
                new ServletWebRequest(request),
                null);

        assertNotNull(predicate);
        assertEquals(post.createdAt.between(startAt, endAt), predicate);
    }

    interface PostApi {

        void find(@QuerydslPredicate(root = Post.class, bindings = QPostRepositoryImpl.class) Predicate predicate);
    }


}
