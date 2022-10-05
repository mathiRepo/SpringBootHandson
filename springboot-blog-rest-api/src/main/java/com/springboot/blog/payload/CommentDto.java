package com.springboot.blog.payload;

import com.springboot.blog.entity.Post;
import lombok.*;

import java.io.Serializable;

@Data
public class CommentDto {
    private long cId;
    private String cName;
    private String cEmail;
    private String cBody;
    //private Post post;
}