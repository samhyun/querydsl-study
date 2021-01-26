package com.example.querydsl.study.post.repo;

import com.example.querydsl.study.comment.dto.QCommentDto;
import com.example.querydsl.study.core.querydsl.CustomQuerydslRepositorySupport;
import com.example.querydsl.study.post.dto.PostDto;
import com.example.querydsl.study.post.dto.QPostDto;
import com.example.querydsl.study.post.entity.Post;
import com.example.querydsl.study.user.dto.QUserDto;
import com.example.querydsl.study.user.entity.QUser;
import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.example.querydsl.study.comment.entity.QComment.comment;
import static com.example.querydsl.study.post.entity.QPost.post;
import static com.example.querydsl.study.user.entity.QUser.user;

public class QPostRepositoryImpl extends CustomQuerydslRepositorySupport implements QPostRepository {

    public QPostRepositoryImpl() {
        super(Post.class);
    }

    private long countPosts(Predicate predicate) {
        return this.createDefaultQuery().where(predicate).groupBy(post.id).fetchCount();
    }

    private List<PostDto> transformPostDto(JPQLQuery<?> query) {
        return query.transform(
                GroupBy.groupBy(post.id)
                        .list(
                                this.createProjections()
                        )
        );
    }

    @Override
    public Page<PostDto> findPosts(Predicate predicate, Pageable pageable) {
        List<PostDto> posts = this.transformPostDto(
                this.applySorting(pageable, createDefaultQuery().where(inCoveringIndex(predicate, pageable)))
        );

        return new PageImpl<>(posts, pageable, this.countPosts(predicate));
    }

    @Override
    public PostDto getPost(long id) {
        return this.createDefaultQuery()
                .where(post.id.eq(id))
                .transform(
                        GroupBy.groupBy(post.id)
                        .as(
                                this.createProjections()
                        )
                ).get(id);
    }

    @Override
    public PostDto getPostWithComments(long id) {
        QUser commentWriter = new QUser("commentWriter");
        return createDefaultQuery()
                .leftJoin(comment.writer, commentWriter)
                .where(post.id.eq(id))
                .transform(
                        GroupBy.groupBy(post.id)
                                .as(
                                        new QPostDto(
                                                post.id,
                                                post.title,
                                                post.content,
                                                new QUserDto(user.id, user.nickname),
                                                post.createdAt,
                                                post.comments.size().longValue(),
                                                GroupBy.list(
                                                        new QCommentDto(
                                                                comment.id,
                                                                comment.content,
                                                                new QUserDto(commentWriter.id, commentWriter.nickname).skipNulls()
                                                        ).skipNulls()
                                                )
                                        )
                                )
                ).get(id);
    }

    private JPQLQuery<?> createDefaultQuery() {
        return from(post)
                .leftJoin(post.writer, user)
                .leftJoin(post.comments, comment);
    }

    private BooleanExpression inCoveringIndex(Predicate predicate, Pageable pageable) {
        List<Long> ids = this.applyPagination(pageable, from(post).select(post.id).where(predicate))
                .fetch();
        return post.id.in(ids);
    }

    private QPostDto createProjections() {
        return new QPostDto(
                post.id,
                post.title,
                post.content,
                new QUserDto(user.id, user.nickname),
                post.createdAt,
                post.comments.size().longValue());
    }
}
