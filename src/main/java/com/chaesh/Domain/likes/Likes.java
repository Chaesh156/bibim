package com.chaesh.Domain.likes;

import com.chaesh.Domain.BaseTimeEntity;
import com.chaesh.Domain.posts.Posts;
import com.chaesh.Domain.user.Member;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
public class Likes extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "POSTS_ID")
    private Posts posts;


    @Builder
    public Likes(){
    }

}
