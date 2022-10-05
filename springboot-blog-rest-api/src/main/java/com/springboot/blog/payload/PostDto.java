package com.springboot.blog.payload;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.springboot.blog.entity.Post} entity
 */
@Data
public class PostDto implements Serializable {
    private  Long pId;
    private  String pTitle;
    private  String pDescription;
    private  String pContent;
}