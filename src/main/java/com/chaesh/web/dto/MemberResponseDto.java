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

    @Builder
    public MemberResponseDto(Member member){
        this.id = member.getId();
        this.name = member.getName();
        this.email = member.getEmail();
        this.picture = member.getPicture();
        this.role = member.getRole();
    }
}
