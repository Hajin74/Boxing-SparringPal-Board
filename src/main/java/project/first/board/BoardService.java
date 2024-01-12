package project.first.board;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    // 게시판 생성
    @Transactional
    public Long create(Board board) {
        Long createdBoardID = boardRepository.save(board);
        return createdBoardID;
    }

    // 게시판 전체 조회
    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    // 게시판 개별 조회
    public Board findOne(Long boardId) {
        return boardRepository.findById(boardId);
    }

    // 게시판 수정
    @Transactional
    public void update(Board board) {
        boardRepository.update(board);
    }

    // 게시판 삭제
    @Transactional
    public void delete(Long id) {
        boardRepository.delete(id);
    }

}
