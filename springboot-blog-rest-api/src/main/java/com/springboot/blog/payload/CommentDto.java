package com.springboot.blog.payload;

import com.springboot.blog.entity.Post;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class CommentDto {
    private long id;

    @NotEmpty
    @Size(min = 2,message = "Name should have more than two characters")
    private String name;
    @Email(message = "Email field should have proper mail Id")
    private String email;

    @NotBlank
    private String body;
    //private Post post;
}