package com.chaesh.Domain.like;

import com.chaesh.Domain.BaseTimeEntity;
import com.chaesh.Domain.posts.Posts;
import com.chaesh.Domain.user.Member;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Like extends BaseTimeEntity {

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
    public Like(String member_id, String posts_id){
    }

}
