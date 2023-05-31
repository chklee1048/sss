package com.itwill.spring2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.itwill.spring2.domain.Reply;
import com.itwill.spring2.dto.ReplyCreateDto;
import com.itwill.spring2.dto.ReplyReadDto;
import com.itwill.spring2.dto.ReplyUpdateDto;
import com.itwill.spring2.repository.ReplyRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor // final 필드를 초기화하는 생성자
@Service// 얘가 있어야 스프링 컨텍스트에 서비스 컴포넌트 객체로 등록
public class ReplyService {

    // @Autowired나 @RequiredArgsConstructor와 private final을 이용해서 주입 받을 수 있다.
    
    private final ReplyRepository replyRepository;// 스프링이 스프링 컨텍스트에서 아규먼트 있는 생성자 생성. 생성자 멤버변수 값을 스프링이 가지고 있음 
    // 생성자에 의한 의존성 주입

    public int create(ReplyCreateDto dto) {
        log.info("create(dto={})",dto);
        return replyRepository.insert(dto.toEntity());
    }

    public List<ReplyReadDto> read(long postId) {
        log.info("read(postId={})",postId);
        
        List<Reply> list = replyRepository.selectByPostId(postId);
        
//       return list.stream().map(ReplyReadDto::fromEntity).toList();
//       list.stream().map((x) -> ReplyReadDto.fromEntity(x)).toList();
//        매핑 시켜줌, 필터링 함
//        list.stream(): list 컬렉션을 스트림으로 변환합니다. 스트림은 컬렉션 요소를 처리하는 데 사용되는 연속적인 연산을 지원합니다.
//
//        map(ReplyReadDto::fromEntity): map은 스트림의 각 요소에 대해 특정 함수를 적용하여 새로운 요소를 생성합니다. 
//        ReplyReadDto::fromEntity는 ReplyReadDto 클래스에 정의된 fromEntity 메서드를 참조하는 메서드 레퍼런스입니다. 
//        이는 list의 각 요소에 fromEntity 메서드를 적용하여 ReplyReadDto 객체로 변환합니다.
//
//        toList(): map 연산을 통해 생성된 요소들을 리스트로 수집합니다. 
//        toList()는 스트림의 요소들을 리스트로 변환하는 Collectors의 정적 메서드입니다.
//
//        결과 반환: toList() 연산을 통해 생성된 리스트를 메서드의 반환문으로 반환합니다.
//        즉, list 컬렉션의 각 요소를 ReplyReadDto 객체로 변환한 후, 그 결과를 리스트로 반환하는 것입니다. 
//        ReplyReadDto::fromEntity는 ReplyReadDto 클래스에 정의된 fromEntity 
//        메서드를 호출하여 엔티티 객체를 ReplyReadDto 객체로 변환하는 역할을 합니다. 
//        반환된 리스트는 해당 메서드를 호출한 곳으로 전달되거나 다른 용도로 활용될 수 있습니다.
        
        List<ReplyReadDto> result = new ArrayList<>();
        
        for(Reply rrd : list) {
            ReplyReadDto dto = ReplyReadDto.fromEntity(rrd);
            result.add(dto);
        }
        
        return result;
    }
    
    public ReplyReadDto readById(long id) {
        log.info("readById(id={})",id);
        
        Reply reply = replyRepository.selectById(id);
        
        return ReplyReadDto.fromEntity(reply);
    }
    
    
    public int delete(long id) {
        log.info("delete(id={})", id);
        return replyRepository.delete(id);
    }
    
    public int update(long id, ReplyUpdateDto dto) {
        log.info("delete(id={}, dto={})", id, dto);
        // 
        int result = replyRepository.update(Reply.builder().id(id).reply_text(dto.getReplyText()).build());
        
        return result;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
