package com.jojoldu.book.domain.posts;

import com.jojoldu.book.domain.posts.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts,Long> {

}
