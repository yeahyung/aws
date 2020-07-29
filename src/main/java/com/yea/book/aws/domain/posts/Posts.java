package com.yea.book.aws.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// Entity 에서는 @Data or @Setter 를 사용하지 않는다.
// 필드의 값을 변경하고 싶은 경우, 명확히 목적과 의도를 나타낼 수 있는 메소드를 추가해야 한다. ex) void cancelOrder(){ this.status = false; }
@Getter
@NoArgsConstructor
@Entity                 // 테이블과 링크될 클래스임을 나타낸다. 클래스의 카멜케이스 이름을 언더스코어 네이밍으로 테이블 이름을 매칭한다. SalesManager -> sales_manager table
public class Posts {    // 실제 DB 테이블과 매칭될 클래스

    @Id                 // 해당 테이블의 PK 필드
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @Column 테이블의 칼럼을 나타내며 굳이 선언하지 않아도 되지만, default value를 수정하고 싶을 경우(length=255 -> 500) or 타입을 TEXT 로 변경하고 싶은 경우 등에 사용한다.
    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    // 생성자와 사실 크게 다르지 않지만, 인스턴스를 생성할 때, 어떠한 값이 어떤 필드에 사용되는지 좀 더 명확하게 할 수 있다.
    @Builder
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this. author = author;
    }
}
