package com.cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@RequiredArgsConstructor
@EnableJpaAuditing(auditorAwareRef = "auditorAware") // We are enabling AUDITING
@SpringBootApplication
public class BlogPostApplication {

//	private final PostRepository postRepository;
//	private final PostCommentRepository postCommentRepository;

	public static void main(String[] args) {
		SpringApplication.run(BlogPostApplication.class, args);
	}

//	@EventListener(classes = ApplicationReadyEvent.class)
//	public void onReady() {
//
////		var post = new Post();
////		post.setTitle("First Post");
////		post.setContent("First Post Content");
////		this.postRepository.save(post);
//
//		var optionalPost = this.postRepository.findById(1L);
//
//		if (optionalPost.isPresent()) {
//			var post = optionalPost.get();
//
//			var comment = new PostComment();
//			comment.setComment("First Post Comment");
//			comment.setPost(post);
//
//			this.postCommentRepository.save(comment);
//
//		}
//
//	}

}
