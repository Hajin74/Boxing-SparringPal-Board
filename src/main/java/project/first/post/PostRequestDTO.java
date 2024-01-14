package project.first.post;

import lombok.Data;

@Data
public class PostRequestDTO {

    private Long id;
    private Long boardId;
    private Long userId;
    private String title;
    private String content;
    private PostStatus status;

}
