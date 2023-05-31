package com.itwill.spring2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwill.spring2.domain.Post;
import com.itwill.spring2.dto.PostCreateDto;
import com.itwill.spring2.dto.PostDetailDto;
import com.itwill.spring2.dto.PostListDto;
import com.itwill.spring2.dto.PostUpdateDto;
import com.itwill.spring2.repository.PostRepository;
import com.itwill.spring2.repository.ReplyRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*
 *  appication-context.xml에서 <context:component-scan>설정에서 com.itwill.spring2.service
 *  패키지와 그 하위 패키지를 스캔(검색)
 *  
 */
@Slf4j
@Service// ->스프링 컨테이너에서 서비스 컴포넌트 객체를 생성하고 관리(필요한 곳에 주입)
@RequiredArgsConstructor // final 선언된 필드를 초기화하는 생성자
public class PostService {

    @Autowired
    private PostRepository postRepository;
    
    // 의존성 주입(Dependency Injection):
    // 1. 필드에 의한 의존성 주입 : @@Autowired 애너테이션 사용
    // 2. 생성자에 의한 의존성 주입
    //      (1) 필드를 final로 선언
    //      (2) final은 변수를 초기화 반드시 해야 됨. 할 수 있는 생성자를 작성
    // @RequiredArgsConstructor로 인해 변수 하나에 생성자가 만들어짐. 그래서 초기화를 함으로써 의존성 주입이 가능해짐
//    private final PostRepository postRepository;
    
    private final ReplyRepository replyRepository;
    
    // 포스트 목록 페이지
    public List<PostListDto> read(){
        log.info("read()");
        List<PostListDto> list = postRepository.selectWithReplyCount();
        
        log.info("read() list = {}",list);
        
        return list;
//        List<Post> list = postRepository.selectOrderByIdDesc();
//        return list.stream().map(PostListDto::fromEntity).toList();
        
//        List<PostListDto> result = new ArrayList<>();
        
//        for(Post p : list) {
//            PostListDto dto = PostListDto.fromEntity(p);
//            result.add(dto);
//        }
//        
//        return result;
        
        
//        map에 전달 map에 있는것이 fromEntity에 들어감 그 일은
//        PostListDto안에 있는 fromEntity 메서드 안의 build 돌아감. 그거를 리스트에 담음
    }
    
    // 상세보기 페이지
    public PostDetailDto read(long id) {
        log.info("read(id = {})", id);
        
        // db posts에서 검색
        Post entity = postRepository.selectById(id);
        
        // 검색한 내용을 PostDetailDto로 변환
        PostDetailDto dto = PostDetailDto.fromEntity(entity);
        
        
        // DB replies에서 댓글 개수를 검색한다.
        long count = replyRepository.selectReplyCountWithPostId(id);
        
        dto.setReplyCount(count);
        
        return dto;   
    }
    
    // 새 포스트 작성 페이지
    public int create(PostCreateDto post) {
        log.info("create((post = {})", post);
        
        // PostCreateDto 타입을 Post 타입으로 변환해서
        // 리포지토리 계층의 메서드를 호출 - DB Insert
        return postRepository.insert(post.toEntity());
    }
    
    // 포스트 업데이트
    public int update(PostUpdateDto post) {
        log.info("update(post = {})", post);
        
        return postRepository.updateTitleContent(post.toEntity());
        
    }
    
    public int delete(long id) {
        log.info("delete(id = {})", id);
        return postRepository.deleteById(id);
    }

}
