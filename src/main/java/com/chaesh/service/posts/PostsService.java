package com.chaesh.service.posts;

import com.chaesh.Domain.exception.MemberNotExistException;
import com.chaesh.Domain.exception.PostsNotExistException;
import com.chaesh.Domain.likes.LikesRepository;
import com.chaesh.Domain.posts.PostRepository;
import com.chaesh.Domain.posts.Posts;
import com.chaesh.Domain.security.dto.SessionUser;
import com.chaesh.Domain.user.Member;
import com.chaesh.Domain.user.MemberRepository;
import com.chaesh.web.dto.PostsListResponseDto;
import com.chaesh.web.dto.PostsResponseDto;
import com.chaesh.web.dto.PostsSaveRequestDto;
import com.chaesh.web.dto.PostsUpdateRequestDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostRepository postsRepository;
    private final HttpSession httpSession;
    private final MemberRepository memberRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        Posts posts = requestDto.toEntity();
        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        Member member = memberRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new MemberNotExistException("해당 email을 가진 member가 없습니다. id=" + user.getEmail()));
        posts.setMember(member);

        return postsRepository.save(posts).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new PostsNotExistException("해당 게시글이 없습니다. id=" + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }
    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new PostsNotExistException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }


    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    public void delete(Long id){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new MemberNotExistException("해당 사용자가 없습니다. id =" + id));
        postsRepository.delete(posts);
    }
    public boolean checkSessionUserWithPostMember(Long postsId, SessionUser user) {
        Posts posts = postsRepository.findById(postsId)
                .orElseThrow(() -> new PostsNotExistException("해당 posts가 없습니다" + postsId));

        try{
            if(!posts.getMember().getEmail().equals(user.getEmail()))
                throw new IllegalArgumentException();
        }catch (Exception e)
        {
            System.out.println("글 작성자 본인만 글을 수정할 수 있습니다");
            return false;
        }
        return true;
    }
}
