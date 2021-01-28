package com.example.querydsl.study.post.controller;

import com.example.querydsl.study.post.dto.PostDto;
import com.example.querydsl.study.post.service.PostService;
import com.example.querydsl.study.user.dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.persistence.EntityManager;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Stream;

@WebMvcTest(controllers = {PostController.class})
public class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    @MockBean
    private EntityManager entityManager;

    @Autowired
    private ObjectMapper mapper;

    private DateFormat dateFormat;

    @BeforeEach
    void setUp() {
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
    }

    @Test
    public void call_get_post_list_api_with_created_at_parameter() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/posts")
                        .param("createdAt", dateFormat.format(new Date()))
        ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    public void call_save_post_api() throws Exception {
        PostDto postDto = PostDto.builder()
                .content("hihihihi")
                .title("hihihi")
                .writer(
                        UserDto.builder()
                                .id(1L)
                                .build()
                ).build();

        performSavePost(postDto).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @ParameterizedTest
    @MethodSource("invalidPostDataProvider")
    public void call_save_post_api_with_invalid_post_data(PostDto postDto) throws Exception {
        performSavePost(postDto).andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    private static Stream<PostDto> invalidPostDataProvider() {
        return Stream.of(
                PostDto.builder()
                        .content("hihihihi")
                        .title("hihihi")
                        .writer(
                                new UserDto()
                        ).build(),
                PostDto.builder()
                        .content("hihihihi")
                        .writer(
                                UserDto.builder()
                                        .id(1L)
                                        .build()
                        ).build(),
                PostDto.builder()
                        .title("hihihihi")
                        .writer(
                                UserDto.builder()
                                        .id(1L)
                                        .build()
                        ).build()

        );
    }

    private ResultActions performSavePost(PostDto postDto) throws Exception {
        return mockMvc.perform(
                MockMvcRequestBuilders.post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(postDto))
        );
    }

}
