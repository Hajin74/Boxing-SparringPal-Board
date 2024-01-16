package project.first.comment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.first.board.BoardService;

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
    public String updateComment() {

        return "redirect:/";
    }

}
