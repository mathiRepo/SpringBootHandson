package com.springboot.blog.payload;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * A DTO for the {@link com.springboot.blog.entity.Post} entity
 */
@Data
public class PostDto implements Serializable {
    private  Long id;
    private  String title;
    private  String description;
    private  String content;
    private Set<CommentDto> comments;
}