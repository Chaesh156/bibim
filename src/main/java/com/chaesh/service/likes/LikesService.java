package com.chaesh.service.likes;

import com.chaesh.Domain.exception.LikeToggleException;
import com.chaesh.Domain.exception.MemberNotExistException;
import com.chaesh.Domain.exception.PostsNotExistException;
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
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LikesService {

    private final LikesRepository likeRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long save(Long memberId, Long postId) throws RuntimeException{

        Optional<Likes> bymemberIdandpostsId = likeRepository.findByMemberAndPostsId(memberId, postId);
        if (bymemberIdandpostsId.isPresent())
            return -1l;

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotExistException("해당 id를 가진 member가 없습니다. id=" + memberId));
        Posts post = postRepository.findById(postId)
                        .orElseThrow(() -> new PostsNotExistException("해당 id를 가진 게시글이 없습니다. id=" + postId));

        Likes likes = Likes.builder()
                .build();
        likes.setMember(member);
        likes.setPosts(post);

        return likeRepository.save(likes).getId();
    }

    public MemberResponseDto findMemberById(Long id){
        Likes entity = likeRepository.findById(id)
                .orElseThrow(() -> new LikeToggleException("해당 좋아요가 없습니다. id=" + id));

        Member member = memberRepository.findById(entity.getId())
                .orElseThrow(() -> new MemberNotExistException("해당 id를 가진 member가 없습니다. id=" + entity.getId()));

        return new MemberResponseDto(member);
    }

    @Transactional(readOnly = true)
    public List<MemberResponseDto> findAllMemberByPostsId(Long postsid){
        Posts posts = postRepository.findById(postsid)
                .orElseThrow(() -> new PostsNotExistException("해당 id의 posts가 없습니다. : " + postsid));


        return posts.getLikes().stream()
                .map(entity -> {
                    return MemberResponseDto.builder()
                            .member(entity.getMember())
                            .build();
                })
                .collect(Collectors.toList());
    }

    public void delete(Long id){
        Likes like = likeRepository.findById(id).orElseThrow(() -> new LikeToggleException("해당 좋아요가 없습니다. id =" + id));
        likeRepository.delete(like);
    }

}
