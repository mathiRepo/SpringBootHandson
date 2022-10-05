package com.springboot.blog.controller;

import com.springboot.blog.entity.Comment;
import com.springboot.blog.exception.BlogApiException;
import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(
            @RequestBody CommentDto commentDto,
            @PathVariable("postId") long postId
    ){
        commentService.createComment(commentDto,postId);
        return new ResponseEntity<>(commentDto, HttpStatus.CREATED);
    }

    @GetMapping("/{postId}/comments")
    public ResponseEntity<List<CommentDto>> getCommentspostId(
            @PathVariable (value = "postId") long postId){
        List<CommentDto> comments = commentService.getCommentsById(postId);
        return new ResponseEntity<>(comments,HttpStatus.ACCEPTED);
    }

    @GetMapping("/{postId}/comment/{commentId}")
    public ResponseEntity<CommentDto> getCommentByIdPostId(
            @PathVariable(value = "postId") long postId,
            @PathVariable(value = "commentId") long commentId
    ){
        System.out.println("PostId : "+postId+" commentId : "+commentId);
        return new ResponseEntity<>(commentService.getCommentByCommentId(postId,commentId),HttpStatus.OK);

    }

    @PutMapping("/{postId}/comment/{commentId}")
    public ResponseEntity<CommentDto> updateComment(
            @PathVariable(value = "postId") long postId,
            @PathVariable(value = "commentId") long commentId,
            @RequestBody CommentDto commentDto
    ){
        commentService.updateCommentById(postId, commentId, commentDto);
        return new ResponseEntity<>(commentDto,HttpStatus.CREATED);
    }

    @DeleteMapping("/{postId}/comment/{commentId}")
    public String deleteComment(
            @PathVariable(value = "postId") long postId,
            @PathVariable(value = "commentId") long commentId
    ){

        return commentService.deleteComment(postId,commentId);
    }
}
