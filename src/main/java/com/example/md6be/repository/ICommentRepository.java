package com.example.md6be.repository;

import com.example.md6be.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICommentRepository extends JpaRepository<Comment, Long> {
    @Override
    List<Comment> findAll();

    List<Comment> findAllByProductId(Long id);

}
