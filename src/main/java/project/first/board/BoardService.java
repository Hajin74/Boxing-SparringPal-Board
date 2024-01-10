package project.first.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    // 게시판 생성
    @Transactional
    public Long create(Board board) {
        boardRepository.save(board);
        return board.getId();
    }

    // 게시판 전체 조회
    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    // 게시판 개별 조회
    public Board findOne(Long boardId) {
        return boardRepository.findById(boardId);
    }

}
