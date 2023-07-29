package com.chaesh.web.dto;

import com.chaesh.Domain.likes.Likes;
import com.chaesh.Domain.posts.Posts;
import com.chaesh.Domain.user.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor

public class LikesSaveRequestDto {

    private Member member;
    private Posts posts;

    @Builder
    public LikesSaveRequestDto(Member member, Posts posts) {
        this.member = member;
        this.posts = posts;
    }

    public Likes toEntity() {
        return Likes.builder()
                .build();
    }
}
