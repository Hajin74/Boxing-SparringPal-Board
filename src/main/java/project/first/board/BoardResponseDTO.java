package project.first.board;

import lombok.Data;

@Data
public class BoardResponseDTO {

    private Long id;
    private String title;

    public BoardResponseDTO(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
    }

}
