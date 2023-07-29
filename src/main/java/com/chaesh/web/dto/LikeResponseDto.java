package com.chaesh.web.dto;

import com.chaesh.Domain.like.Like;
import com.chaesh.Domain.user.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LikeResponseDto {

    private Long id;
    private Member member;

    @Builder
    public LikeResponseDto(Like entity){
        this.id = entity.getId();
        this.member = entity.getMember();
    }


}
