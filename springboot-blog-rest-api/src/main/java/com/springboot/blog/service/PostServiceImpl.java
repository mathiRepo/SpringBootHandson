package com.springboot.blog.service;

import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.ResourceNotFound;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostDtoResponse;
import com.springboot.blog.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService{


    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto){

        Post post = mapToEntity(postDto);
        Post newPost = postRepository.save(post);
        postDto = mapToDto(newPost);
        return postDto;
    }

    @Override
    public PostDtoResponse getPosts(int pages, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pages,pageSize, sort);
        Page<Post> posts= postRepository.findAll(pageable);

        //Get Content from Pages
        List<Post>  pPosts = posts.getContent();
        List<PostDto> postDtos = pPosts.stream()
                .map(post -> mapToDto(post)).collect(Collectors.toList());

        PostDtoResponse postDtoResponse = new PostDtoResponse();
        postDtoResponse.setPostDtos(postDtos);
        postDtoResponse.setPageNo(posts.getNumber());
        postDtoResponse.setPageSize(posts.getSize());
        postDtoResponse.setLast(posts.isLast());
        postDtoResponse.setTotalPages(posts.getTotalPages());
        postDtoResponse.setTotalElements(posts.getTotalElements());
        return postDtoResponse;
    }


    private PostDto mapToDto(Post post){
        PostDto respDto = new PostDto();
        respDto.setPId(post.getId());
        respDto.setPTitle(post.getTitle());
        respDto.setPContent(post.getContent());
        respDto.setPDescription(post.getDescription());
        return respDto;
    }

    private Post mapToEntity(PostDto postDto){
        Post post = new Post();
        //post.setId(postDto.getPId());
        post.setContent(postDto.getPContent());
        post.setDescription(postDto.getPDescription());
        post.setTitle(postDto.getPTitle());

        return null;
    }

    @Override
    public PostDto getPostsById(Long id){

        Post postById =  postRepository.findById(id).orElseThrow(() ->new ResourceNotFound("Post"," ",id));

        return mapToDto(postById);
    }

    @Override
    public PostDto updatePost(PostDto newDto, Long id) {

        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Post","Update",id));
        post.setTitle(newDto.getPTitle());
        post.setDescription(newDto.getPDescription());
        post.setContent(newDto.getPContent());
        postRepository.save(post);
        return mapToDto(post);
    }

    @Override
    public PostDto deletePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Posts","Delete",id));
        postRepository.delete(post);
        return mapToDto(post);
    }

}
