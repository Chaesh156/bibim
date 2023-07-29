package com.chaesh.web;

import com.chaesh.service.like.LikeService;
import com.chaesh.web.dto.LikeResponseDto;
import com.chaesh.web.dto.LikeSaveRequestDto;
import com.chaesh.web.dto.MemberResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController

public class LikeApiController {

    private final LikeService likeService;

    @PostMapping("/api/v1/like")
    public long save(@RequestBody LikeSaveRequestDto requestDto){
        return likeService.save(requestDto);
    }
    @GetMapping("/api/v1/like/{id}")
    public MemberResponseDto findMemberById(@PathVariable Long id){
        return likeService.findMemberById(id);
    }

    @DeleteMapping("/api/v1/like/{id}")
    public Long delete(@PathVariable Long id){
        likeService.delete(id);
        return id;
    }
}
