package project.first.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
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
    @Rollback(false)
    public void createBoardTest() throws Exception {
        // given
        Board board = new Board();
        board.setTitle("게시판 테스트");

        // when
        Long boardId = boardService.create(board);
        board.setId(boardId);

        // 엔티티끼리는 각자 생성해서 주기 때문에 다른것을 가리킴 -> 음.. 
        System.out.println(board);
        System.out.println(boardRepository.findById(boardId));

        // then
        assertEquals(boardId, boardRepository.findById(boardId).getId());
    }
}