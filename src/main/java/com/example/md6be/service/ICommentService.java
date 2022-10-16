package com.example.md6be.service;

import com.example.md6be.model.Comment;

import java.util.List;

public interface ICommentService extends IGeneralService<Comment>{

    @Override
    List<Comment> findAll();

    List<Comment> findAllByProductId(Long id);


}
