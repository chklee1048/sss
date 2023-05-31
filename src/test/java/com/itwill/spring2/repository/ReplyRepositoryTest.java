package com.itwill.spring2.repository;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.itwill.spring2.domain.Reply;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(SpringExtension.class) // Spring JUnit 테스트를 실행하는 메인 클래스
@ContextConfiguration(
        locations = {"file:src/main/webapp/WEB-INF/application-context.xml"})// 스프링 컨텍스트 환경 설정 파일의 경로와 이름.
public class ReplyRepositoryTest {

    @Autowired
    private ReplyRepository replyRepository;
    
    
    @Test
    public void testSelectReplyCountWithPostId() {
        long result = replyRepository.selectReplyCountWithPostId(1);
        
        log.info("result = {}", result);
    }
    
//    @Test
    public void testDelete() {
        int result = replyRepository.delete(1);
        
        Assertions.assertEquals(1, result);
        log.info("result = {}", result);
    }
    
//    @Test
    public void testUpdate() {
        int result = replyRepository.update(Reply.builder()
                                                .id(2)
                                                .reply_text("수정한 내용")
                                                .build());
        
        Assertions.assertEquals(1, result);
        log.info("result = {}", result);
    }
    
//    @Test
    public void testInsert() {
        int result = replyRepository.insert(Reply.builder()
                                                .post_id(2)
                                                .reply_text("두번째 댓글 내용")
                                                .writer("두번째 댓글 작성자")
                                                .build());
        Assertions.assertEquals(1, result);
        log.info("result = {}", result);
    }
    
    
    
//    @Test
    public void testSelectById() {
        List<Reply> result1 = replyRepository.selectByPostId(1);
        Assertions.assertNotNull(result1);
        
        for(Reply r : result1) {
            log.info(r.toString());
        }

    } 
}
