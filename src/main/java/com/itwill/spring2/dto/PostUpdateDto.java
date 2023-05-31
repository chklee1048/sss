package com.itwill.spring2.dto;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.itwill.spring2.domain.Post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


// field 이름을 선언할 때는 요청 파라미터 이름과 같게.
//@RequiredArgsConstructor 파이널 변수 해결 
@Data // get, set, toString, equals, hashcode
@Builder// 빌더 패턴
public class PostUpdateDto {
    private long id;
    private String title;
    private String content;
    // -> JSTL에서는 LocalDateTime 객체를 사용하지 못하기 때문에 timestamp
    
    public Post toEntity() {
        
        return Post.builder()
                .id(this.id)
                .title(this.title)
                .content(this.content)
                .build();
    } 
}
