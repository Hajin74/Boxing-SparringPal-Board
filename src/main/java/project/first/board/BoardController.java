package project.first.board;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/createForm")
    public String showCreateForm() {
        return "board/createForm";
    }

    @PostMapping("/create")
    public String createBoard(BoardForm boardForm) {
        Board board = Board.builder()
                .title(boardForm.getTitle())
                .build();

        boardService.create(board);

        return "redirect:/";
    }

    @GetMapping
    public List<Board> getAllBoard() {
        return boardService.findAll();
    }

    @GetMapping("/{id}")
    public Board getBoardById(@PathVariable("id") Long id) {
        return boardService.findOne(id);
    }

    @PostMapping("/modify")
    public ResponseEntity<Board> updateBoard(@RequestBody BoardRequestDTO boardRequestDTO) {
        Board board = boardService.findOne(boardRequestDTO.getId());
        board.updateBoard(boardRequestDTO.getTitle());
        boardService.update(board);

        return new ResponseEntity<>(board, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteBoard(@PathVariable("id") Long id) {
        boardService.delete(id);

        return new ResponseEntity<>(true, HttpStatus.OK);
    }

}