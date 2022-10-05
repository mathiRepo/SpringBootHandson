package com.springboot.blog.service;

import com.springboot.blog.entity.Post;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostDtoResponse;

import java.util.List;

public interface PostService {

   public PostDto createPost(PostDto postDto);

   public PostDtoResponse getPosts(int pages, int pageSize, String sortBy,String sortDir);

   public PostDto getPostsById(Long id);

   PostDto updatePost(PostDto newDto, Long id);

   PostDto deletePost(Long id);
}
