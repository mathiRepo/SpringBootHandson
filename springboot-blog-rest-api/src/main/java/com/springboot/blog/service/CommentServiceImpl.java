package com.springboot.blog.service;

import com.springboot.blog.entity.Comment;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.BlogApiException;
import com.springboot.blog.exception.ResourceNotFound;
import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.repository.CommentRepository;
import com.springboot.blog.repository.PostRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService{


    private CommentRepository commentRepository;
    private PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository,
                                PostRepository postRepository
    ) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public CommentDto createComment(CommentDto commentDto, long postId) {
        //Retrieve post entity by id
        Comment comment = toCommentEntity(commentDto);
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFound("CreateComment","id",postId));

        comment.setPost(post);
        Comment newComment = commentRepository.save(comment);
        return toCommentDto(newComment);
    }

    @Override
    public List<CommentDto> getCommentsById(long postId) {
        List<Comment> comments = commentRepository.getCommentByPostId(postId);
        List<CommentDto> commentDtos = comments.stream()
                .map(comment->toCommentDto(comment)).collect(Collectors.toList());
        return commentDtos;
    }

    @Override
    public CommentDto getCommentByCommentId(long postId, long commentId) {

        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFound("Post","Id",postId));

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFound("Comment","Id",commentId));

        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"Comment does not belong to the post");
        }
        System.out.println("PostId : "+postId+" commentId : "+commentId);
        return toCommentDto(comment);
    }

    @Override
    public CommentDto updateCommentById(long postId, long commentId, CommentDto requestCommDto) {

        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFound("Post","Id",postId)
        );
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFound("Comment","Id",commentId)
        );
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"Comment does not belong to the post");
        }

        comment.setBody(requestCommDto.getCBody());
        comment.setName(requestCommDto.getCName());
        comment.setEmail(requestCommDto.getCEmail());

        Comment commentResp = commentRepository.save(comment);

        return toCommentDto(commentResp);
    }

    @Override
    public String deleteComment(long postId, long commentId) {

        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFound("Post","Id",postId)
        );
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFound("Comment","Id",commentId)
        );
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"Comment does not belong to the post");
        }
        commentRepository.deleteById(commentId);
        return "Comment Deleted SuccessFully";
    }


    private Comment toCommentEntity(CommentDto commentDto){
        Comment comment = new Comment();
        comment.setId(commentDto.getCId());
        comment.setBody(commentDto.getCBody());
        comment.setName(commentDto.getCName());
        comment.setEmail(commentDto.getCEmail());
        return comment;
    }

    private CommentDto toCommentDto(Comment  comment){
        CommentDto commentDto = new CommentDto();
        commentDto.setCId(comment.getId());
        commentDto.setCName(comment.getName());
        commentDto.setCEmail(comment.getEmail());
        commentDto.setCBody(comment.getBody());
        return commentDto;
    }
}