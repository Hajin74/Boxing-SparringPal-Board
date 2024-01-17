package project.first.comment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.first.board.BoardService;
import project.first.post.PostService;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;
    private final BoardService boardService;

    @PostMapping("/create")
    public String createComment(@RequestParam("postId") Long postId, CommentForm commentForm) {
        Comment comment = new Comment();
        comment.setContent(commentForm.getContent());
        commentService.save(comment, postId);

        return "redirect:/post/detail/" + postId;
    }

    @PostMapping("/update")
    public String updateComment(@RequestParam("postId") Long postId, @RequestParam("commentId") Long commentId, CommentForm commentForm) {
        commentService.update(commentId, commentForm.getContent());

        return "redirect:/post/detail/" + postId;
    }

    @DeleteMapping("/delete/{commentId}")
    public String deleteComment(@PathVariable("commentId") Long commentId) {
        Comment comment = commentService.findOne(commentId);
        Long postId = comment.getPost().getId();
        commentService.delete(comment);

        return "redirect:/post/detail/" + postId;
    }
}
