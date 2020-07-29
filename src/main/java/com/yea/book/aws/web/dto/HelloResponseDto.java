package com.yea.book.aws.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter // 선언된 모든 필드의 get method 를 자동으로 생성해준다.
@RequiredArgsConstructor // 선언된 모든 final 필드가 포함된 생성자를 생성해준다.
// final 이 없는 필드는 생성자에 포함되지 않는다.
public class HelloResponseDto {

    private final String name;
    private final int amount;
}