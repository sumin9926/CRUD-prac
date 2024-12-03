package com.example.memo.controller;

import com.example.memo.dto.MemoRequestDto;
import com.example.memo.dto.MemoResponseDto;
import com.example.memo.entity.Memo;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController//클래스가 REST 컨트롤러임을 나타내며, HTTP 요청을 처리하고 JSON 또는 XML 형식의 응답을 반환하도록 한다.
/*클래스 레벨에 @RequestMapping을 사용하면, 해당 클래스의 모든 메소드에 대한 기본 URI를 설정할 수 있다.
다음과 같이 클래스에 @RequestMapping("/memos")를 적용하면, 이 클래스의 모든 메소드에 대한 기본 경로는 /memos가 된다.*/
@RequestMapping("/memos")//특정 URI(Uniform Resource Identifier)에 대한 HTTP 요청을 특정 메소드 또는 클래스에 매핑하는 역할을 한다.
public class MemoController {
    private final Map<Long, Memo> memoList=new HashMap<>();

    /*메모 생성 API*/
    /*
    * @RequestBody는 pring 프레임워크에서 제공하는 어노테이션으로, HTTP 요청의 본문(body)을 Java 객체로 변환해주는 역할을 한다.
    * 클라이언트가 전송한 JSON이나 XML 데이터를 서버 측에서 Java 객체로 매핑할 때 사용한다.
    * */
    @PostMapping//메모 생성이기 때문에 Post 사용
    public MemoResponseDto createMemo(@RequestBody MemoRequestDto dto){
        //식별자가 1씩 증가
        //Collections.max()는 ()안의 객체 내부에서 가장 큰 값 반환
        Long memoId=memoList.isEmpty() ? 1: Collections.max(memoList.keySet())+1;

        //요청받은 데이터로 Memo 객체 생성
        Memo memo=new Memo(memoId, dto.getTitle(), dto.getContents());

        //memoList(Inmemory Db)에 Memo 메모
        memoList.put(memoId,memo);

        return new MemoResponseDto(memo);
    }

    /*메모 단건 조회 API*/
    //"바인딩"은 프로그래밍에서 특정 값이나 변수, 객체 등을 서로 연결하거나 결합하는 과정을 의미한다.
    @GetMapping("/{id}")//URL 경로에 포함된 값을 메소드의 파라미터로 바인딩할 때 사용한다.
    //클라이언트가 /users/1 경로로 GET 요청을 보내면, id 파라미터는 1로 설정된다.
    public MemoResponseDto findMemoById(@PathVariable Long id){

        Memo memo=memoList.get(id);
        return new MemoResponseDto(memo);
    }

    /*메모 수정 API*/
    @PutMapping("/{id}")
    public MemoResponseDto updateMemoById(@PathVariable Long id, @RequestBody MemoRequestDto dto){
        Memo memo=memoList.get(id);
        memo.update(dto);
        return new MemoResponseDto(memo);
    }

    @DeleteMapping("/{id}")
    public void deleteMemo(@PathVariable Long id){
        memoList.remove(id);
    }
}
