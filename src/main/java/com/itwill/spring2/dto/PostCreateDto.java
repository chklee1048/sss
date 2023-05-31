package com.itwill.spring2.dto;

import com.itwill.spring2.domain.Post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// @RequiredArgsConstructor 파이널 변수 해결 
@Data // get, set, toString, equals, hashcode
@AllArgsConstructor // 모든 필드를 아규먼트로 갖는 생성자
@NoArgsConstructor // 기본 생성자
@Builder// 빌더 패턴
public class PostCreateDto {
    
    // 해당 jsp의 name을 적어주자
    private String title;
    private String content;
    private String author;
    
    // PostCreateDto 타입의 객체를 Post 타입의 객체로 변환해서 리턴.
    public Post toEntity() {
        
        return Post.builder()
                .title(this.title)
                .content(this.content)
                .author(this.author)
                .build();
    } 
    
}
