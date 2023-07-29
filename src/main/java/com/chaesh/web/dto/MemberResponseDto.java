package com.chaesh.web.dto;

import com.chaesh.Domain.user.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor

public class MemberResponseDto {

    private Long id;

    @Builder
    public MemberResponseDto(Member entity){
        this.id = entity.getId();
    }
}
