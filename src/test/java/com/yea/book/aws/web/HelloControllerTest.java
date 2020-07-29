package com.yea.book.aws.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class) // JUnit 에 내장된 실행자 외에 다른 실행자 실행시킨다.
// SpringRunner 실행시킴, 스프링 부트 테스트와 JUnit 사이의 연결자 역할
@WebMvcTest(controllers = HelloController.class)
// Web MVC 에 집중할 수 있도록 하는 어노테이션
// @Controller, @ControllerAdvice 사용 가능
// @Service, @Component, @Repository 사용 불가
public class HelloControllerTest {

    @Autowired
    // 스프링이 관리하는 빈을 주입 받는다.
    private MockMvc mvc;

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        // /hello 주소로 HTTP GET 요청
        mvc.perform(get("/hello"))
                .andExpect(status().isOk()) // HTTP Status 200
                .andExpect(content().string(hello)); // return value "string"
    }

    @Test
    public void helloDto() throws Exception{
        String name = "hello";
        int amount = 1000;

        // param API 테스트 할 때 사용될 요청 파라미터 설정, String 만 허용 된다.
        mvc.perform(get("/hello/dto")
        .param("name", name)
        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
                            // jsonPath $를 기준으로 필드명을 명시한다.
    }
}
