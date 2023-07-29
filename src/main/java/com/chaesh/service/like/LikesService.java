package com.chaesh.service.like;

import com.chaesh.Domain.likes.Likes;
import com.chaesh.Domain.likes.LikesRepository;
import com.chaesh.Domain.posts.PostRepository;
import com.chaesh.Domain.posts.Posts;
import com.chaesh.Domain.user.Member;
import com.chaesh.Domain.user.MemberRepository;
import com.chaesh.web.dto.LikesSaveRequestDto;
import com.chaesh.web.dto.MemberResponseDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LikesService {

    private final LikesRepository likeRepository;
    private final PostRepository postRepository;
    private final HttpSession httpSession;
    private final MemberRepository memberRepository;

    @Transactional
    public Long save(LikesSaveRequestDto requestDto, Long memberId, Long postId){
        Likes likes = requestDto.toEntity();

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 id를 가진 member가 없습니다. id=" + memberId));
        Posts post = postRepository.findById(postId)
                        .orElseThrow(() -> new IllegalArgumentException("해당 id를 가진 게시글이 없습니다. id=" + postId));
        likes.setMember(member);
        likes.setPosts(post);

        return likeRepository.save(likes).getId();
    }

    public MemberResponseDto findMemberById(Long id){
        Likes entity = likeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 좋아요가 없습니다. id=" + id));

        Member member = memberRepository.findById(entity.getId())
                .orElseThrow(() -> new IllegalArgumentException("해당 id를 가진 member가 없습니다. id=" + entity.getId()));

        return new MemberResponseDto(member);
    }

    @Transactional(readOnly = true)
    public List<MemberResponseDto> findAllMember(Long id){

        return likeRepository.findAllMember(id).stream()
                .map(MemberResponseDto::new)
                .collect(Collectors.toList());
    }

    public void delete(Long id){
        Likes like = likeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 좋아요가 없습니다. id =" + id));
        likeRepository.delete(like);
    }

}
