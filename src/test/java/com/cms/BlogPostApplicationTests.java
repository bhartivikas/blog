package com.cms;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.cms.entity.PostComment;

import lombok.extern.slf4j.Slf4j;

@Slf4j
//@SpringBootTest
class BlogPostApplicationTests {

	@Test
	void contextLoads() {

		PostComment c1 = new PostComment();

		PostComment c2 = new PostComment();

		Assertions.assertEquals(c1, c2);
		log.info("OBJ1: {}, OBJ2: {}", c1.hashCode(), c2.hashCode());
		
		c1.setCommentId(256L);
		c2.setCommentId(256L);
		log.info("OBJ1: {}, OBJ2: {}", c1.hashCode(), c2.hashCode());
		Assertions.assertEquals(c1, c2);

	}

}
