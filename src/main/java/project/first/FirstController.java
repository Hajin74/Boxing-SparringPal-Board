package project.first;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import project.first.board.Board;
import project.first.board.BoardService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class FirstController {

    private final BoardService boardService;

    @RequestMapping("/")
    public String home(Model model) {
        List<Board> boards =boardService.findAll();
        model.addAttribute("boards", boards);
        log.info("<< FirstController >>");
        return "home";
    }

}
