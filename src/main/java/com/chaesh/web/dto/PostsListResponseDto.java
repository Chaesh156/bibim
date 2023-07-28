package com.chaesh.web.dto;

import com.chaesh.Domain.posts.Posts;
import com.chaesh.Domain.user.Member;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
    public class PostsListResponseDto {
        private Long id;
        private Member member;
        private String title;
        private String author;
        private LocalDateTime modifiedDate;

        public PostsListResponseDto(Posts entity) {
            this.id = entity.getId();
            this.member = entity.getMember();
            this.title = entity.getTitle();
            this.author = entity.getAuthor();
            this.modifiedDate = entity.getModifiedDate();
        }
    }
