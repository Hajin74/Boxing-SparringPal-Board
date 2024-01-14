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
@Slf4j
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

    @PostMapping("/create")
    public String createBoard(BoardForm boardForm) {
        Board board = new Board();
        board.setTitle(boardForm.getTitle());
        Long createdBoardId = boardService.create(board);
        log.info("<< createBoard 메소드 >> : " + createdBoardId);

        return "redirect:/";
    }

    @PostMapping("/modify")
    public ResponseEntity<Board> updateBoard(@RequestBody BoardRequestDTO boardRequestDTO) {
        Board board = new Board();
        board.setId(boardRequestDTO.getId());
        board.setTitle(boardRequestDTO.getTitle());
        boardService.update(board);

        return new ResponseEntity<>(board, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteBoard(@PathVariable("id") Long id) {
        boardService.delete(id);

        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    // todo: 실패할 경우 예외처리가 필요할 것 같은데..

    @GetMapping("/createForm")
    public String showCreateForm() {
        log.info("<< showCreateForm 메소드 >>");

        return "board/createForm";
    }

}