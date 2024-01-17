package project.first.post;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.first.board.Board;
import project.first.board.BoardForm;
import project.first.board.BoardService;
import project.first.comment.Comment;
import project.first.user.User;
import project.first.user.UserRequestDTO;

import java.util.List;

@Controller
@RequestMapping("/post")
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;
    private final BoardService boardService;

    @GetMapping("/createForm")
    public String showCreateForm(@RequestParam("boardId") Long boardId, Model model) {
        model.addAttribute("boardId", boardId);

        return "post/createForm";
    }

    @PostMapping("/create")
    public String createPost(@RequestParam("boardId") Long boardId, PostForm postForm) {
        Post post = Post.builder()
                    .title(postForm.getTitle())
                    .content(postForm.getContent())
                    .status(postForm.getStatus())
                    .build();

        postService.save(post, boardId);

        return "redirect:/post/posts?boardId=" + boardId;
    }

    @GetMapping
    public List<Post> getAllPost() {
        return postService.findAll();
    }

    @GetMapping("/posts")
    public String showPostsByBoardId(@RequestParam("boardId") Long boardId, Model model) {
        Board board = boardService.findOne(boardId);
        List<Post> posts = postService.findByBoard(boardId);

        model.addAttribute("posts", posts);
        model.addAttribute("board", board);

        return "post/postList";
    }

    @GetMapping("/{userName}")
    public List<Post> getPostByUser(@PathVariable("userName") String user) {
        return postService.findByUser(user);
    }

    @GetMapping("/detail/{postId}")
    public String getPostById(@PathVariable("postId") Long postId, Model model) {
        Post post = postService.findById(postId);
        List<Comment> comments = post.getComments();

        model.addAttribute("post", post);
        model.addAttribute("comments", comments);

        return "post/postDetail";
    }

    @GetMapping("/updateForm")
    public String showUpdateForm(@RequestParam("postId") Long postId, @RequestParam("boardId") Long boardId, Model model) {
        Post post = postService.findById(postId);
        model.addAttribute("post", post);

        return "post/updateForm";
    }

    @PostMapping("/update")
    public String updatePost(@RequestParam("boardId") Long boardId, @RequestParam("postId") Long postId, PostForm postForm) {
        postService.update(postId, postForm.getTitle(), postForm.getContent(), postForm.getStatus());

        return "redirect:/post/detail/" + postId;
    }

    @DeleteMapping("/delete/{id}")
    public String deletePost(@PathVariable("id") Long id) {
        Long boardId = postService.findById(id).getBoard().getId();
        postService.delete(id);

        return "redirect:/post/posts?boardId=" + boardId;
    }

}
