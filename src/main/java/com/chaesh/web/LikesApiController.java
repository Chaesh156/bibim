package com.chaesh.web;

import com.chaesh.service.like.LikesService;
import com.chaesh.web.dto.LikesSaveRequestDto;
import com.chaesh.web.dto.MemberResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController

public class LikesApiController {

    private final LikesService likesService;

    @PostMapping("/api/v1/likes/{memberId}/{postId}")
    public long save(@RequestBody LikesSaveRequestDto requestDto, @PathVariable Long memberId , @PathVariable Long postId){
        return likesService.save(requestDto, memberId, postId);
    }

    @GetMapping("/api/v1/likes/{postId}")
    public MemberResponseDto findMemberById(@PathVariable Long postId){
        return likesService.findMemberById(postId);
    }


    @DeleteMapping("/api/v1/likes/{id}")
    public Long delete(@PathVariable Long id){
        likesService.delete(id);
        return id;
    }
}
