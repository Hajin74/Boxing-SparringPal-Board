package project.first.board;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    public List<Board> getAllBoard() {
        return boardService.findAll();
    }

    @GetMapping("/{id}")
    public Board getBoardById(@PathVariable("id") Long id) {
        return boardService.findOne(id);
    }

    @PostMapping
    public ResponseEntity<Long> createBoard(@RequestBody BoardRequestDTO boardRequestDTO) {
        // board 엔티티 생성, 저장
        Board board = new Board();
        board.setTitle(boardRequestDTO.getTitle());
        Long createdBoardId = boardService.create(board);

        return ResponseEntity.ok(createdBoardId);
    }
}