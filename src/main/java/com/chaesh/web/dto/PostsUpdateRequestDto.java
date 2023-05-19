package com.chaesh.web.dto;



import com.chaesh.Domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsUpdateRequestDto {
    private String title;
    private String content;

    @Builder
    public PostsUpdateRequestDto(Posts entity){
        this.title = entity.getTitle();
        this.content = entity.getContent();
    }
}
