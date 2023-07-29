package com.chaesh.web.dto;

import com.chaesh.Domain.like.Like;
import com.chaesh.Domain.posts.Posts;
import com.chaesh.Domain.user.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor

public class LikeSaveRequestDto {

    private Member member;
    private Posts posts;

    @Builder
    public LikeSaveRequestDto(Member member, Posts posts) {
        this.member = member;
        this.posts = posts;
    }

    public Like toEntity() {
        return Like.builder()
                .member(member)
                .posts(posts)
                .build();
    }
}
