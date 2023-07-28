package com.chaesh.web.dto;

import com.chaesh.Domain.posts.Posts;
import com.chaesh.Domain.user.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsResponseDto {

    private Long id;
    private Member member;
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsResponseDto(Posts entity){
        this.id = entity.getId();
        this.member = entity.getMember();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
