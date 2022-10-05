package com.springboot.blog.service;

import com.springboot.blog.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto, long postId);

    List<CommentDto> getCommentsById(long postId);

    CommentDto getCommentByCommentId(long postId, long commentId);

    CommentDto updateCommentById(long postId, long commentId, CommentDto requestCommDto);

    String deleteComment(long postId, long commentId);
}
