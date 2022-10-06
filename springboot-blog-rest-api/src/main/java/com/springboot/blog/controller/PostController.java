package com.springboot.blog.controller;

import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostDtoResponse;
import com.springboot.blog.service.PostService;
import com.springboot.blog.service.PostServiceImpl;
import com.springboot.blog.utils.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService PostService;


    public PostController(PostServiceImpl postServiceImpl) {
        this.PostService = postServiceImpl;
    }


    @PostMapping
    public ResponseEntity<PostDto> savePost(@Valid  @RequestBody PostDto  postDto){
        System.out.println("Inside Controller");
        PostDto responseDto = PostService.createPost(postDto);
        System.out.println("outside service ");
        return new ResponseEntity<>(responseDto, HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<PostDtoResponse> getPosts(
            @RequestParam(value = "pages" , defaultValue = AppConstants.DEFAULT_PAGES) int pages,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGESIZE) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORTBY) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORTDIR, required = false) String sortDir)
    {
        return new ResponseEntity<>(PostService.getPosts(pages,pageSize,sortBy,sortDir),HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok(PostService.getPostsById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@Valid @PathVariable(value = "id") Long id,@RequestBody PostDto postDto){
        return new ResponseEntity<>(PostService.updatePost(postDto,id),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PostDto> deletePost(@PathVariable(value = "id") Long id){
        return new ResponseEntity<>(PostService.deletePost(id),HttpStatus.ACCEPTED);
    }




}
