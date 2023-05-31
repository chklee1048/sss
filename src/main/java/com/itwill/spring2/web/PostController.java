package com.itwill.spring2.web;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.spring2.domain.Post;
import com.itwill.spring2.dto.PostCreateDto;
import com.itwill.spring2.dto.PostDetailDto;
import com.itwill.spring2.dto.PostListDto;
import com.itwill.spring2.dto.PostUpdateDto;
import com.itwill.spring2.service.PostService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor // 생성자에 의한 의존성 주입
@RequestMapping("/post") // PostController 클래스의 메서드들은 요청 주소가 /post로 시작
@Controller // DispatcherService
public class PostController {
    
//    비어있는 생성자 모두 호출
//    다 가지고 있고
//    셋터를 가지고 데이터 셋팅
//    우리가 만든 데이터를 거기에다가 넣어줌

    private final PostService postService;
    
    @GetMapping("/list")
    public void list(Model model) {
        log.info("list()");
        
        List<PostListDto> list = postService.read();
        
        log.info("list = {}",list);
        
        model.addAttribute("posts", list);
        
        
    }
    
    
    @GetMapping("/create")
    public void create(Model model) {
        log.info(" Get create()");
    }
    
    @PostMapping("/create")
    public String create(PostCreateDto dto) {
        log.info(" Post create({})", dto);
        
        int result = postService.create(dto);
        log.info("포스트 등록 결과 = {}", result);
        if (result == 1) {
            return "redirect:/post/list";
        } else {
            return "redirect:/post/create";
        }
        
    }
    
    @GetMapping("/detail")
    public void detail(Model model, long id) {
        log.info("detail()");
        
        PostDetailDto dto = postService.read(id);
        
        model.addAttribute("post", dto);
        
        
    }
    
    @GetMapping("/modify")
    public void modify(long id, Model model) {
        log.info("modify(id={})", id);
        
        PostDetailDto dto = postService.read(id);
        
        model.addAttribute("post",dto);
                
    }
    
    @PostMapping("/modify")
    public String modify(PostUpdateDto dto) {
        
        log.info("post modify(dto={})", dto);
        
        postService.update(dto);
        
        return "redirect:/post/list";
//        return "redirect:/post/detail?id="+dto.getId();
    }
    
    @PostMapping("/delete")
    public String delete(long id) {
        log.info("post delete(id={})", id);
        
        postService.delete(id);
        
        return "redirect:/post/list";   
                
    }
    
}
