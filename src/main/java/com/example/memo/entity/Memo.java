package com.example.memo.entity;

import com.example.memo.dto.MemoRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor //모든 필드를 매개변수로 가지는 생성자를 자동으로 생성한다.
public class Memo {

    private Long id;
    private String title;
    private String contents;

    public void update(MemoRequestDto dto){
        this.title= dto.getTitle();
        this.contents= dto.getContents();
    }

}
