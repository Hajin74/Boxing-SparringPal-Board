package project.first.post;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public List<Post> getAllPost() {
        return postService.findAll();
    }

    @GetMapping("/{user}")
    public List<Post> getPostByUser(@PathVariable("user") String user) {
        return postService.findByUser(user);
    }

    @GetMapping("/{title}")
    public List<Post> getPostByTitle(@PathVariable("title") String title) {
        return postService.findByTitle(title);
    }

}
