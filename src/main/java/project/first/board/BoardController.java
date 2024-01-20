package project.first.board;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.first.user.User;
import project.first.user.UserService;

import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final UserService userService;

    @GetMapping("/createForm")
    public String showCreateForm() {
        return "board/createForm";
    }

    @PostMapping("/create")
    public String createBoard(BoardForm boardForm, Model model) {

        User user = userService.findOne(boardForm.getLogin());
        if (user != null) {
            Board board = Board.builder()
                    .title(boardForm.getTitle())
                    .build();
            boardService.create(board);

            return "redirect:/";
        } else {
            model.addAttribute("errorMessage", "입력한 사용자가 없습니다. 정확한 사용자 이름을 입력해주세요.");
            return "board/createForm";
        }
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