package com.itwill.spring2.dto;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.itwill.spring2.domain.Reply;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ReplyReadDto {
    private long id;
    private long postId;
    private String replyText;
    private String writer;
    private Timestamp modifiedTime;
    
    // db에서 셀렉트한 객체를 replyReadDto로 변환하려고 합니다.
    public static ReplyReadDto fromEntity(Reply reply) {
        
        return ReplyReadDto.builder()
                .id(reply.getId())
                .postId(reply.getPost_id())
                .replyText(reply.getReply_text())
                .writer(reply.getWriter())
                .modifiedTime(Timestamp.valueOf(reply.getModified_time()))
                .build();
                
    } 
    
    
    
    
    
    
    
    
    
    
    
    
}
