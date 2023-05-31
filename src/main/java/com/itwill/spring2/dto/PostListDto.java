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

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostListDto {
    private long id;
    private String title;
    private String content;
    private String author;
    private Timestamp created_time;
    // -> JSTL에서는 LocalDateTime 객체를 사용하지 못하기 때문에 timestamp
    private long rcnt;// 댓글 개수를 저장하기 위한 변수
    
    /** 컬럼 6개 짜리를 4개짜리로 만들어 주는 메서드. 
     * static인 이유 dto를 만들기전 그러니까 객체를 만들기전 호출하여 전달해야 하기때문에  */
    public static PostListDto fromEntity(Post entity) {
        return PostListDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .author(entity.getAuthor())
                // LocalDateTime을 Timestamp로 바꾸기 위해서
                .created_time(Timestamp.valueOf(entity.getCreated_time()))
                .build();
    }
    
}
