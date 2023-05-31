package com.itwill.spring2.web;

import java.lang.module.ResolutionException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.spring2.domain.Reply;
import com.itwill.spring2.dto.ReplyCreateDto;
import com.itwill.spring2.dto.ReplyReadDto;
import com.itwill.spring2.dto.ReplyUpdateDto;
import com.itwill.spring2.service.ReplyService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reply")
public class ReplyController {
    
    private final ReplyService replyService;
    
    
//    @Data
//    @AllArgsConstructor
//    public class Test{
//        private int age;
//        private String name;
//        
//    }
//    
//    @GetMapping
//    public Test hello() {
//        log.info("hello()");
//        
//        return new Test(99, "tetronine") ;
//    }
    
    
    /*
     * 호출해서 스프링이(디서) 여기까지 옴 근데 dto가 있네? 기본 생성자로 생성함. @RequestBody로 dto setter 호출안에 데이터 채움. 하나 더
     * jackson-bind 라이브러리로 데이터를 파싱함. json 형식 문자열을 파싱함. 쉼표 단위로 파라미터를 파싱 함.
     */
    
    /*
     * axios.post를 사용하여 데이터를 서버로 전송할 때, data 객체의 내용이 요청의 본문 데이터로 전송되며, 서버는 이
     * 데이터를 @RequestBody 어노테이션을 사용하여 컨트롤러 메서드의 매개변수에 매핑합니다.
     * 
     * 따라서 컨트롤러에서 @RequestBody 어노테이션을 사용하여 매개변수로 DTO 객체를 받는다면, axios.post의 data 객체와
     * 연동되어 데이터가 전달되는 것이 맞습니다. 서버는 받은 데이터를 기반으로 적절한 처리를 수행하고, 응답으로 ResponseEntity
     * 객체를 반환할 수 있습니다.
     */
    @PostMapping
    public ResponseEntity<Integer> createReply(@RequestBody ReplyCreateDto dto  ){
        
        log.info("createReply(dto={})", dto);
        
        int result = replyService.create(dto);
        
        return ResponseEntity.ok(result);
    }
    
    @GetMapping("/all/{postId}")
    public ResponseEntity<List<ReplyReadDto>> read(@PathVariable long postId){
        
        log.info("get read(post={})", postId);
        
        List<ReplyReadDto> list = replyService.read(postId);
        
        
        /*jsp jstl fmt는 java.sql.datatime, java.sql.timestamp는 잘 바꿔줌, localdatatime은 못 바꿔줌 그래서 에러남.*/
        /* 자바8에서 만든 localdatatime은 잭슨이가 몰름 문자열로 못 만들겠어요 */
        
        log.info("# of replis = {}", list.size());
        
        return ResponseEntity.ok(list);
    }  
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deleteReply(@PathVariable long id  ){
        
        log.info("deleteReply(id={})", id);
        
        int result = replyService.delete(id);
        
        return ResponseEntity.ok(result);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ReplyReadDto> readById(@PathVariable long id){
        log.info("readById(id={})",id);
        
        ReplyReadDto dto = replyService.readById(id);
        
        log.info("dto = {}", dto);
        
        return ResponseEntity.ok(dto);
        
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Integer> updateReply(@PathVariable long id, @RequestBody ReplyUpdateDto dto){
        log.info("updateReply(id={}, dto={})",id, dto);
        
        int result = replyService.update(id, dto);
        
        return ResponseEntity.ok(result);
        
    }
    
}
