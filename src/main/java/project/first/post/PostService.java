package project.first.post;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.first.board.Board;
import project.first.board.BoardRepository;
import project.first.board.BoardRequestDTO;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final BoardRepository boardRepository;

    // 게시글 생성
    @Transactional
    public void create(Post post, Long boardId) {
        Board board = boardRepository.findById(boardId);
        post.setBoard(board);
        postRepository.save(post);
    }

    // 게시글 전체 조회
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    // 게시글 부분 조회 - 유저 기반
    public List<Post> findByUser(String user) {
        return postRepository.findByUser(user);
    }

    // 게시글 수정 - 타이틀, 내용
    @Transactional
    public void modify(Post post) {
        postRepository.update(post);
    }

    // 게시글 삭제 - 아이디
    @Transactional
    public void delete(Long id) {
        postRepository.delete(id);
    }

}
