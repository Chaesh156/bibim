package com.chaesh.service.like;

import com.chaesh.Domain.like.Like;
import com.chaesh.Domain.like.LikeRepository;
import com.chaesh.Domain.posts.PostRepository;
import com.chaesh.Domain.security.dto.SessionUser;
import com.chaesh.Domain.user.Member;
import com.chaesh.Domain.user.MemberRepository;
import com.chaesh.web.dto.LikeResponseDto;
import com.chaesh.web.dto.LikeSaveRequestDto;
import com.chaesh.web.dto.MemberResponseDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LikeService {

    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final HttpSession httpSession;
    private final MemberRepository memberRepository;

    @Transactional
    public Long save(LikeSaveRequestDto requestDto){
        Like likes = requestDto.toEntity();
        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        Member member = memberRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("해당 email을 가진 member가 없습니다. id=" + user.getEmail()));
        likes.setMember(member);

        return likeRepository.save(likes).getId();
    }

    public MemberResponseDto findMemberById(Long id){
        Member entity = memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 좋아요가 없습니다. id=" + id));

        return new MemberResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<LikeResponseDto> findAllMember(Long id){
        return likeRepository.findAllMember().stream()
                .map(LikeResponseDto::new)
                .collect(Collectors.toList());
    }

    public void delete(Long id){
        Like like = likeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 좋아요가 없습니다. id =" + id));
        likeRepository.delete(like);
    }

}
