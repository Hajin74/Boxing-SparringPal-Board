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

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/post")
@RequiredArgsConstructor
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

        return "redirect:/post/detail/" + post.getId();
    }

    @GetMapping
    public List<Post> getAllPost() {
        return postService.findAll();
    }

    @GetMapping("/posts")
    public String showPostsByBoardId(@RequestParam("boardId") Long boardId, Model model) {
        Board board = boardService.findOne(boardId);
        List<Post> posts = postService.findByBoard(boardId);
//        Collections.reverse(posts);

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
    public String updatePost(@RequestParam("postId") Long postId, PostForm postForm) {
        postService.update(postId, postForm.getTitle(), postForm.getContent(), postForm.getStatus());

        return "redirect:/post/detail/" + postId;
    }

    @DeleteMapping("/delete/{id}")
    public String deletePost(@PathVariable("id") Long id) {
        Long boardId = postService.findById(id).getBoard().getId();
        Post post = postService.findById(id);
        postService.delete(post);

        return "redirect:/post/posts?boardId=" + boardId;
    }

    @GetMapping("/search")
    public String searchPost(@RequestParam("title") String title, @RequestParam("boardId") Long boardId, Model model) {
        List<Post> posts = postService.findByTitle(title, boardId);
        System.out.println(posts.size());
        model.addAttribute("posts", posts);
        model.addAttribute("title", title);
        model.addAttribute("board", boardService.findOne(boardId));

        return "post/postList";
    }


}
