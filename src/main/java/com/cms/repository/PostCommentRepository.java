package com.cms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cms.entity.PostComment;

public interface PostCommentRepository extends JpaRepository<PostComment, Long> {

}
