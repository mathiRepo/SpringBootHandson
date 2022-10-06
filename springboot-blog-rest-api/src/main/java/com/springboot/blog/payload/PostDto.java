package com.springboot.blog.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

/**
 * A DTO for the {@link com.springboot.blog.entity.Post} entity
 */
@Data
public class PostDto implements Serializable {
    private  Long id;

    //title shoud not be null or empty
    //title should have atleast 2 characters
    @NotEmpty
    @Size(min = 2, message = "Post title should have atleast 2 Characters")
    private  String title;

    @NotEmpty
    @Size(min = 10, message = "Post description should have atleast 10 characters")
    private  String description;
    @NotEmpty
    private  String content;
    private Set<CommentDto> comments;
}