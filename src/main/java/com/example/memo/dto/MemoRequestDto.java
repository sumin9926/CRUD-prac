package com.example.memo.dto;

import lombok.Getter;

//requestDto: 요청 데이터를 처리하는 객체
@Getter
public class MemoRequestDto {
    private String title;
    private String contents;
}
