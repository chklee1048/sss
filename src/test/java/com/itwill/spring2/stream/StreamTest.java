package com.itwill.spring2.stream;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

// 스프링 컨텍스트(application-context.xml 또는 servlet-context.xml)를 사용하지 않는
// 단위 테스트에서는 @ExtenWith, @ContextConfiguration 애너테이션을 사용할 필요가 없음
public class StreamTest {
    
    @Test
    public void test() {
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7);
        System.out.println(numbers);
        
        // numbers에서 홀수들만 필터링한 결과
        List<Integer> odds = numbers.stream()
                .filter((x) ->{return x % 2 == 1;}) // 블록 안에서는 세미콜론 써야 됨
//                .filter(number -> number % 2 == 1) // 세미 콜론 생략 해야 됨
                .toList();
        
        for(int a : odds) {
            System.out.println(a);
        }
        
        // number에서 원소들의 제곱들로 이루어진 리스트
        // 리스트에 있는 값들을 아규먼트로 적용
        List<Integer> squares = numbers.stream()
                .map((x) -> {return x* x;})
                .toList();
        
        System.out.println(squares);
        
        // 홀수들의 제곱
        List<Integer> oddsSquares = numbers.stream()
                .filter((x) ->{return x % 2 == 1;})
                .map((x) -> {return x* x;})
                .toList();
        System.out.println(oddsSquares);
        
        List<String> lang = Arrays.asList("Java","SQL","JavaScript");
        
        // lang가 가지고 있는 문자열들의 길이를 원소로 갖는 리스트
        List<Integer> langSize = lang.stream()
//                .map(x -> x.length() )
                .map(String::length)// x에서 메서드 호출할때, 아규먼트 없음
                .toList();
        
        System.out.println(langSize);
        
        List<LocalDateTime> times = Arrays.asList(
                LocalDateTime.now()
                , LocalDateTime.of(2023, 5,24,11,30,0)
                , LocalDateTime.of(2023, 5,23,10,30,0)
                , LocalDateTime.of(2023, 5,22,9,30,0)
                );
        
        
        System.out.println(times);
        
        List<Timestamp> stamps = times.stream()
//                .map((x)-> Timestamp.valueOf(x))//
                .map(Timestamp::valueOf) /* 아규먼트 전달 */
                .toList();
        System.out.println(stamps);
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }
}
