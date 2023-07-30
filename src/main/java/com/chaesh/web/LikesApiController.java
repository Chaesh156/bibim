package com.chaesh.web;

import com.chaesh.service.likes.LikesService;
import com.chaesh.web.dto.LikesSaveRequestDto;
import com.chaesh.web.dto.MemberResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController

public class LikesApiController {

    private final LikesService likesService;

    @PostMapping("/api/v1/likes/{memberId}/{postId}")
    public long save(@PathVariable Long memberId , @PathVariable Long postId){
        return likesService.save(memberId, postId);
    }

    @GetMapping("/api/v1/likes/{postId}")
    public MemberResponseDto findMemberById(@PathVariable Long postId){
        return likesService.findMemberById(postId);
    }

    @GetMapping("/api/v1/likes/list/{postsId}")
    public List<MemberResponseDto> findAllMember(@PathVariable Long postsId){
        return likesService.findAllMemberByPostsId(postsId);
    }

    @DeleteMapping("/api/v1/likes/{id}")
    public Long delete(@PathVariable Long id){
        likesService.delete(id);
        return id;
    }
}
