package project.first.board;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardForm {

    @NotEmpty(message = "게시판 제목은 필수입니다.")
    private String title;
    @NotEmpty(message = "사용자 이름은 필수입니다.")
    private String login;

}
