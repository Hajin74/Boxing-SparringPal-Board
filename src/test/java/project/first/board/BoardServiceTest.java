package project.first.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class BoardServiceTest {

    @Autowired
    BoardService boardService;

    @Autowired
    BoardRepository boardRepository;

    @Test
    public void createTest() throws Exception {
        // given
        Board board = new Board();
        board.setTitle("게시판 테스트");

        // when
        Long boardId = boardService.create(board);

        // then
        assertEquals(board, boardRepository.findById(boardId));
    }

}