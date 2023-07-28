package com.chaesh.service.like;

import com.chaesh.Domain.like.Like;
import com.chaesh.Domain.like.LikeRepository;
import com.chaesh.Domain.posts.PostRepository;
import com.chaesh.Domain.security.dto.SessionUser;
import com.chaesh.Domain.user.Member;
import com.chaesh.Domain.user.MemberRepository;
import com.chaesh.web.dto.LikeResponseDto;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LikeService {

    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final HttpSession httpSession;
    private final MemberRepository memberRepository;

    @Transactional
    public Long save(LikeResponseDto requestDto){
        Like likes = requestDto.toEntity();
        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        Member member = memberRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("해당 email을 가진 member가 없습니다. id=" + user.getEmail()));
        likes.setMember(member);

        return likeRepository.save(likes).getId();
    }

    public int getLikeCount(){

    }
    public LikeResponseDto findById(Long id){

    }

}
