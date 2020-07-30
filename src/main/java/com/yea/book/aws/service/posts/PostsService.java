package com.yea.book.aws.service.posts;

import com.yea.book.aws.domain.posts.Posts;
import com.yea.book.aws.domain.posts.PostsRepository;
import com.yea.book.aws.web.dto.PostsResponseDto;
import com.yea.book.aws.web.dto.PostsSaveRequestDto;
import com.yea.book.aws.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {

    /*
    스프링에서 bean을 주입받는 방식
    1. Autowired
    2. Setter
    3. 생성자
    이 중 생성자로 주입받는 방식이 가장 권장된다.
    --> @RequiredArgsConstructor = final이 선언된 모든 필드를 인자값으로 하는 생성자를 생성한다.
     */

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        // Posts Entity와 PostsSaveRequestDto 는 거의 유사한 형태이다.
        // 하지만 Entity 클래스를 Request/Response 클래스로 절대 사용해서는 안 된다.
        // Entity 는 데이터베이스와 맞닿은 핵심 클래스이기 때문에.
        // Entity 클래스가 변경되면 여러 클래스에 영향을 끼친다.
        // Request/Response용 Dto는 View를 위한 클래스라 자주 변경이 된다.
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    // Dirty checking -> @Transaction 안에서 데이터베이스 데이터를 가져오면,
    // 트랜젝션이 끝나는 시점에 해당 테이블의 변경분을 반영한다.
    // 즉, Entity 객체의 값만 변경하면, 별도로 update 쿼리를 날리지 않아도 DB가 변경된다.
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        return new PostsResponseDto(entity);
    }
}
