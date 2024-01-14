package project.first.post;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.first.board.Board;
import project.first.board.BoardForm;
import project.first.user.User;
import project.first.user.UserRequestDTO;

import java.util.List;

@Controller
@RequestMapping("/post")
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;

    @PostMapping("/create")
    public String createPost(@RequestParam("boardId") Long boardId, PostForm postForm) {
        log.info("<< PostController - createPost 호출");

        Post post = new Post();
        post.setTitle(postForm.getTitle());
        post.setContent(postForm.getContent());
        post.setStatus(postForm.getStatus());
        postService.create(post, boardId);

        return "redirect:/board/" + boardId + "/postList"; // 게시글 목록 postList 페이지로 들어가야함
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

    @GetMapping("/createForm")
    public String showCreateForm() {
        log.info("<< Post - showCreateForm 메소드 >>");
        return "post/createForm";
    }
}
