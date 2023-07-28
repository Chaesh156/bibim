package com.chaesh.web;

import com.chaesh.Domain.security.dto.SessionUser;
import com.chaesh.service.posts.PostsService;
import com.chaesh.web.dto.PostsResponseDto;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class IndexController {
    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("posts",postsService.findAllDesc());
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if (user != null){
            model.addAttribute("userName",user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(Model model) {
        SessionUser user  = (SessionUser) httpSession.getAttribute("user");
        if (user != null){
            model.addAttribute("userName",user.getName());
        }

        return "posts-save";
    }

    @GetMapping("/like/{memberId}/{postsId}")
    public String {

    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        try{
            if (!dto.getMember().getEmail().equals(user.getEmail()))
                throw new IllegalArgumentException();
        }catch (Exception e){
            System.out.println("글 작성자 본인만 글을 수정할 수 있습니다");
            return "/";
        }
        model.addAttribute("post",dto);

        return "posts-update";
    }

}
