package com.chaesh.web.dto;

import com.chaesh.Domain.posts.Posts;
import com.chaesh.Domain.user.Member;
import com.chaesh.Domain.user.MemberRepository;
import com.chaesh.Domain.user.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor

public class MemberResponseDto {

    private Long id;
    private String name;
    private String email;
    private String picture;
    private Role role;
    List<Posts> posts = new ArrayList<>();

    @Builder
    public MemberResponseDto(Member entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.picture = entity.getPicture();
        this.role = entity.getRole();
        this.posts = entity.getPosts();
    }
}
