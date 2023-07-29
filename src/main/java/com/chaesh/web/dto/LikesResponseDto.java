package com.chaesh.web.dto;

import com.chaesh.Domain.likes.Likes;
import com.chaesh.Domain.user.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LikesResponseDto {

    private Long id;
    private Member member;

    @Builder
    public LikesResponseDto(Likes entity){
        this.id = entity.getId();
        this.member = entity.getMember();
    }


}
