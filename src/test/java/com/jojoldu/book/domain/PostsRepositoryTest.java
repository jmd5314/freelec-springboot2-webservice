package com.jojoldu.book.domain;

import com.jojoldu.book.domain.posts.Posts;
import com.jojoldu.book.domain.posts.PostsRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PostsRepositoryTest {
    @Autowired
    PostsRepository postsRepository;
    @AfterEach
    public void cleanup(){
        postsRepository.deleteAll();
    }
    @Test
    public void 게시글_저장_불러오기(){
        String title = "테스트 게시글";
        String content = "테스트 본문";
        String author = "jojoldu@gmail.com";

        postsRepository.save(Posts.builder().title(title).content(content).author(author).build());

        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);
        Assertions.assertThat(posts.getTitle()).isEqualTo(title);
        Assertions.assertThat(posts.getContent()).isEqualTo(content);
    }

        @Test
        public void BaseTimeEntity_등록(){
            //given
            LocalDateTime now = LocalDateTime.of(2023,11,6,0,0,0);
            postsRepository.save(Posts.builder()
                    .title("title")
                    .content("content")
                    .author("author")
                    .build());
            //when
            List<Posts> postsList = postsRepository.findAll();
            //then
            Posts posts = postsList.get(0);
            System.out.println(">>>>>>>>>>> createDate="+posts.getCreatedDate()+", modifiedDate="+posts.getModifiedDate());

            Assertions.assertThat(posts.getCreatedDate()).isAfter(now);
            Assertions.assertThat(posts.getModifiedDate()).isAfter(now);
    }
}
