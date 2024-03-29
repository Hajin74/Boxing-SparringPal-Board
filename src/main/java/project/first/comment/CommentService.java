package project.first.comment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.first.post.Post;
import project.first.post.PostRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    // 댓글 생성 및 수정
    @Transactional
    public void save(Comment comment, Long postId) {
        Post post =  postRepository.findById(postId);
        comment.setPost(post);
        commentRepository.save(comment);
    }

    // 댓글 조회 - 게시글 아이디
    public List<Comment> findByPostId(Long postId) {
        return commentRepository.findByPostId(postId);
    }

    // 댓글 조회 - 아이디
    public Comment findOne(Long commentId) {
        return commentRepository.findOne(commentId);
    }

    // 댓글 수정
    @Transactional
    public void update(Long commentId, String content) {
        Comment comment = commentRepository.findOne(commentId);
        comment.updateComment(content);
    }

    // 댓글 삭제
    @Transactional
    public void delete(Comment comment) {
        commentRepository.delete(comment);
    }
}
