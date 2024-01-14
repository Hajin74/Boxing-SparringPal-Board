package project.first.post;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.first.user.User;
import project.first.user.UserRequestDTO;

import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/create")
    public ResponseEntity<Post> createPost(@RequestBody PostRequestDTO postRequestDTO) {
        Post post = new Post();
        post.setTitle(postRequestDTO.getTitle());
        post.setContent(postRequestDTO.getContent());
        post.setStatus(postRequestDTO.getStatus());
        postService.create(post);

        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @GetMapping
    public List<Post> getAllPost() {
        return postService.findAll();
    }

    @GetMapping("/{userName}")
    public List<Post> getPostByUser(@PathVariable("userName") String user) {
        return postService.findByUser(user);
    }

    @PostMapping("/modify/{postId}")
    public ResponseEntity<Boolean> modifyPost(@PathVariable Long postId, @RequestBody PostRequestDTO postRequestDTO) {
        Post post = new Post();
        post.setId(postId);
        post.setTitle(postRequestDTO.getTitle());
        post.setContent(postRequestDTO.getContent());

        postService.modify(post);

        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deletePost(@PathVariable("id") Long id) {
        postService.delete(id);

        return new ResponseEntity<>(true, HttpStatus.OK);
    }

}
