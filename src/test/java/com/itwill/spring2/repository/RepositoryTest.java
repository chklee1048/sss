package com.itwill.spring2.repository;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.itwill.spring2.domain.Post;
import com.itwill.spring2.dto.PostListDto;
import com.itwill.spring2.jdbc.JdbcTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(SpringExtension.class) // Spring JUnit 테스트를 실행하는 메인 클래스
@ContextConfiguration(
        locations = {"file:src/main/webapp/WEB-INF/application-context.xml"})// 스프링 컨텍스트 환경 설정 파일의 경로와 이름.
public class RepositoryTest {
    
    @Autowired
    private PostRepository postRepository;
    
    
    
    
    @Test
    public void update() {
        Post post = Post.builder().id(50)
                .title("update title test1").content("update content2")
                .build();
        int result = postRepository.updateTitleContent(post);
        Assertions.assertEquals(1, result);
    }
    
    @Test
    public void deleteById() {
        
        
        int result = postRepository.deleteById(48);
        Assertions.assertEquals(1, result);
    }
    
    @Test
    public void testSelectById() {
        Post result1 = postRepository.selectById(1);
        Assertions.assertNotNull(result1);
//        Post result2 = postRepository.selectById(-1);
//        Assertions.assertNotNull(result2);
    }
    
    @Test
    public void testSelectOrderByIdDesc() {
        List<Post> list = postRepository.selectOrderByIdDesc();
        for(Post p :list) {
            log.info("list = {}", p);
        }
    }
    
    @Test
    public void testPostRepo() {
        Assertions.assertNotNull(postRepository);
        log.info("repo ={}", postRepository);
        
        
        Post post = Post.builder().title("mr").content("mmr").author("nmmnmrrrr").build();
        log.info("post = {}",post);
        
        int result = postRepository.insert(post);
        Assertions.assertEquals(1, result);
        
        
    }
}
