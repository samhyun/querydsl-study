package com.example.querydsl.study.user.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class UserDto {

    @NotNull(groups = UserReference.class)
    private Long id;

    @NotNull(groups = UserSave.class)
    private String email;

    @NotNull
    private String nickname;

    private String password;

    private String lastName;

    private String firstName;

    private String mobile;

    @QueryProjection
    public UserDto(long id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }

    @QueryProjection
    public UserDto(long id, String email, String nickname) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
    }

    @QueryProjection
    @Builder
    public UserDto(long id, String email, String nickname, String lastName, String firstName, String mobile) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobile = mobile;
    }

    public String getName() {
        return this.lastName + this.firstName;
    }
}
