package com.chaesh.web.dto;

import com.chaesh.Domain.like.Like;
import lombok.Builder;

public class LikeSaveResponseDto {

    private String member_id;
    private String post_id;

    @Builder
    public PostsSaveRequestDto(String member_id, String post_id){
        this.member_id = member_id;
        this.post_id = post_id;
    }

    public Like toEntity(){
        return Like.Builder()
    }
}
